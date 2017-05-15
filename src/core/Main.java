package core;

import core.gui.GUI;
import core.world.camera.Camera;
import core.world.math.VecMath;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import core.world.shading.MaterialType;
import core.world.shading.ScatterInfo;
import core.world.shapes.IShape;
import core.world.shapes.ShapeList;
import core.world.shapes.Sphere;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static core.Globals.*;

public class Main
{

    public static Camera camera;

    public static void main(String[] args)
    {
        long startTime = System.nanoTime();

        outputRenderedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        // createAndAddObjects(); //creation of all objects happens here


        double focusDistance = 10.0;
        double aperture = 0.1;
        double vfov = 50;
        int sampleCount = 1;

        Vector3D startPos = new Vector3D(10, 2, 8);
        Vector3D lookDirection = new Vector3D(0, 0, 0);

        camera = new Camera(startPos, lookDirection, new Vector3D(0, 1, 0), vfov, (((double) WIDTH) / ((double) HEIGHT)), aperture, focusDistance);

        shapeList = createAndAddObjects();

        rayTrace(camera, sampleCount, new ShapeList(shapeList));


        SwingUtilities.invokeLater(() -> Globals.gui = new GUI());
        long endTime = System.nanoTime();
        System.out.println("took " + ((endTime - startTime) / 1e06) + " ms.");
    }

    public static void rayTrace(Camera camera, int SampleCount, ShapeList shapeList)
    {

        /*
        ArrayList<IShape> shapes = new ArrayList<>();
        Sphere mySphere_3 = new Sphere(new Vector3D(0, -1.0, -1), 1.0, new Material(MaterialType.METAL, new Vector3D(0.4, 0.2, 0.9), 0.5, 0.0));
        Sphere mySphere_1 = new Sphere(new Vector3D(0.0, 0, 0), 1.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.8, 0.6, 0.2), 0.0, 1.0008));
        shapes.add(mySphere_1);
        shapes.add(mySphere_3);
        ShapeList shapeList = new ShapeList(shapes);
        */

        for (int y = HEIGHT - 1; y >= 0; y--) //this is reverted because the rendering of the image is affected. now we render from top to bottom, we used to render from 0, 0 and upwards.
        {
            for (int x = 0; x < WIDTH; x++)
            {

                Vector3D color = new Vector3D(0, 0, 0);
                for (int s = 0; s < SampleCount; s++)
                {
                    double u = (x + Math.random()) / ((double) (WIDTH));
                    double v = (y + Math.random()) / ((double) (HEIGHT));
                    Ray ray = camera.getRay(u, v);


                    color = VecMath.plusEqual(color, color(ray, shapeList, 0)); //intersection happens inside here.)

                }

                color = VecMath.divideEqual(color, SampleCount);
                //square root for gamma correction also known as gamma 2
                color = new Vector3D(Math.sqrt(color.getX()), Math.sqrt(color.getY()), Math.sqrt(color.getZ()));
                int ir = ((int) (255.99 * color.getX()));
                int ig = ((int) (255.99 * color.getY()));
                int ib = ((int) (255.99 * color.getZ()));
                int[] rgb = new int[3];
                rgb[0] = ir;
                rgb[1] = ig;
                rgb[2] = ib;
                Globals.outputRenderedImage.getRaster().setPixel(x, y, rgb);
            }
        }
    }

    /**
     * we want a function where we pass in a ray and get a vector of color so we can paint it on the screen.
     * <p>
     * <p>
     * performs a linear interpolation - lerp
     * for the background which gives a nice gradient.
     *
     * @param ray
     * @return
     */
    public static Vector3D color(Ray ray, ShapeList shapeListl, int depth)
    {
        // for (IShape shape : shapeList)
        // {
        RayInfo rayInfo = shapeListl.hit(ray, 0.001, Double.MAX_VALUE); //tmin was set to 0.0 but because of floating point precion errors i have set it to 0.001
        if (rayInfo.didIntersect)
        {
            ScatterInfo scatterInfo;
            if (depth < 50 && (scatterInfo = rayInfo.material.scatter(ray, rayInfo)).didScatter)
            {
                return VecMath.multiply(scatterInfo.attenuation, color(scatterInfo.scattered, shapeListl, depth + 1));
            } else
            {
                return new Vector3D(0, 0, 0);
            }


            //Vector3D target = rayInfo.point.add(rayInfo.normal).add(VecMath.random_in_unit_sphere());
            //return color(new Ray(rayInfo.point, target.subtract(rayInfo.point)), shapeList).scalarMultiply(0.5);
            //check normalization, normalizing the vector here might improve shading?
            //return new Vector3D(rayInfo.normal.getX() + 1.0, rayInfo.normal.getY() + 1.0, rayInfo.normal.getZ() + 1.0).scalarMultiply(0.5);

        } else //else, lerp to create a gradient background.
        {
            Vector3D unit_direction = VecMath.unit_vector(ray.dir);
            double t = 0.5 * unit_direction.getY() + 1.0;
            Vector3D startValue = new Vector3D(1.0, 1.0, 1.0); //start white
            Vector3D endValue = new Vector3D(0.8, 0.6, 0.6); //actual background color
            return startValue.scalarMultiply(1.0 - t).add(endValue.scalarMultiply(t));
        }
        // }
        //return new Vector3D(0.0, 0.0, 0.0);
    }


    public static ArrayList<IShape> createAndAddObjects()
    {
        /*
        Sphere mySphere_1 = new Sphere(new Vector3D(0.0, 0, -1), 1.0, new Material(MaterialType.DIELECTRIC, new Vector3D(0.8, 0.6, 0.2), 0.0, 1.0008));
        Sphere mySphere_2 = new Sphere(new Vector3D(0.0, -100.5, -1), 100.0, new Material(MaterialType.METAL, new Vector3D(0.2, 0.6, 0.2), 0.0, 0.0));
        Sphere mySphere_3 = new Sphere(new Vector3D(0.2, 0.0, 1.5), 1.0, new Material(MaterialType.METAL, new Vector3D(0.4, 0.2, 0.9), 0.5, 0.0));

        //shapeList.add(mySphere_1);
        //shapeList.add(mySphere_2);
        //shapeList.add(mySphere_3);

        */

        ArrayList<IShape> shapes = new ArrayList<>();
        shapes.add(new Sphere(new Vector3D(0, -1000, 0), 1000, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.2, 0.2, 0.2), 0.0, 0.0)));

        /*for (int a = -11; a < 11; a++)
        {
            for (int b = -11; b < 11; b++)
            {
                double randomMatProb = Math.random();
                Vector3D center = new Vector3D(a + 0.9 * Math.random(), 0.2, b + 0.9 * Math.random());
                if (VecMath.length(center.subtract(new Vector3D(4, 0.2, 0))) > 0.9)
                {
                    if (randomMatProb < 0.8)
                    {
                        shapes.add(new Sphere(center, 0.2, new Material(MaterialType.LAMBERTIAN, new Vector3D(Math.random() * Math.random(), Math.random() * Math.random(), Math.random() * Math.random()), 0.0, 0.0)));

                    } else if (randomMatProb < 0.95)
                    {
                        shapes.add(new Sphere(center, 0.2, new Material(MaterialType.METAL, new Vector3D(0.5 * (1 + Math.random()), 0.5 * (1 + Math.random()), 0.5 * Math.random() * Math.random()), 0.0, 0.0)));
                    } else
                    {
                        shapes.add(new Sphere(center, 0.2, new Material(MaterialType.DIELECTRIC, new Vector3D(0.5 * (1 + Math.random()), 0.5 * (1 + Math.random()), 0.5 * Math.random() * Math.random()), 0.0, Math.random() + 1)));

                    }
                }
            }
        }
        */

        //shapes.add(new Sphere(new Vector3D(0, 1, 0), 1.0, new Material(MaterialType.DIELECTRIC, new Vector3D(0.5, 0.5, 0.5), 0.0, 1.0005)));
        //shapes.add(new Sphere(new Vector3D(-4, 1, 0), 1.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.4, 0.2, 0.1), 0.0, 0.0)));
        //shapes.add(new Sphere(new Vector3D(4, 1, 0), 1.0, new Material(MaterialType.METAL, new Vector3D(0.7, 0.6, 0.5), 0.0, 0.0)));

        return shapes;
    }


    public static void renderFile()
    {
        try
        {
            ImageIO.write(Globals.outputRenderedImage, "png", new File("out.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
