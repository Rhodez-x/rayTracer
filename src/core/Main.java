package core;

import core.gui.GUI;
import core.world.camera.Camera;
import core.world.math.VecMath;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import core.world.shading.MaterialType;
import core.world.shading.ScatterInfo;
import core.world.shapes.*;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static core.Globals.*;

public class Main
{

    public static Camera camera;

    public static OclusionObject oclusionObject;

    public static void main(String[] args)
    {
        outputRenderedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


        double focusDistance = 10.0;
        double aperture = 0.1;
        double vfov = 50;
        int sampleCount = 1; //for supersampling don't use for now.

        Vector3D startPos = new Vector3D(0, 0, 10);
        Vector3D lookDirection = new Vector3D(0, 0, 0);

        camera = new Camera(startPos, lookDirection, new Vector3D(0, 1, 0), vfov, (((double) WIDTH) / ((double) HEIGHT)), aperture, focusDistance);


        createAndAddObjects(); //creation of all objects happens here


        //Vector3D startPos = new Vector3D(0, 0, -8);
        //Ray myRay = new Ray(startPos, new Vector3D(0, 0, 0));

        rayTrace(camera);

        SwingUtilities.invokeLater(() -> Globals.gui = new GUI());
    }

    public static void rayTrace(Camera camera)
    {
        // Light light = new Light();
        // light.Begin();
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {

                Vector3D color = new Vector3D(0, 0, 0);

                double u = (x) / ((double) (WIDTH));
                double v = (y) / ((double) (HEIGHT));

                Ray ray = camera.getRay(u, v);

                //ray.dir = Camera.projectToView(new Vector3D(x, y, 1));
                //System.out.println(myRay.dir);
                //System.out.println(myRay.orig);

                /*
                for (IShape shape : shapeList)
                {
                    if (shape.intersects(ray, 1).didIntersect)
                    {

                        color = VecMath.plusEqual(color, Material.initShading(ray, shape, 0)); //intersection happens inside here.)
                        //shape.paint(x, y);
                    }
                }
                */


                color = VecMath.plusEqual(color, color(ray, oclusionObject, 0)); //intersection happens inside here.)


                color = VecMath.divideEqual(color, 1);
                //square root for gamma correction also known as gamma 2
                color = new Vector3D(Math.sqrt(color.getX()), Math.sqrt(color.getY()), Math.sqrt(color.getZ()));
                int ir = ((int) (255.99 * color.getX()));
                int ig = ((int) (255.99 * color.getY()));
                int ib = ((int) (255.99 * color.getZ()));
                int[] rgb = new int[3];
                rgb[0] = ir;
                rgb[1] = ig;
                rgb[2] = ib;
                Globals.outputRenderedImage.getRaster().setPixel(x, 599 - y , rgb);

            }
        }
    }


    public static void createAndAddObjects()
    {
        Sphere mySphere_1 = new Sphere(new Vector3D(-3, 3, -3), 1.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0, 0.0, 0.3)));
        Sphere mySphere_4 = new Sphere(new Vector3D(-2, 2, -4.0), 2.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0, 0)));
        Sphere mySphere_2 = new Sphere(new Vector3D(-3.0, -1.0, -2.5), 2.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.6, 0.4, 0.4)));
        Sphere mySphere_3 = new Sphere(new Vector3D(-4.0, 0.0, -1.5), 1.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.1, 0.2, 0.6)));
        Vector3D[] list = new Vector3D[3];

        list[0] = new Vector3D(0, 0, 7);
        list[1] = new Vector3D(3, 3, 8);
        list[2] = new Vector3D(6, 0, 8);

        Triangle tri = new Triangle(list, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));
        
        Vector3D[] list2 = new Vector3D[3];
        list2[0] = new Vector3D(4, 0, 3);
        list2[1] = new Vector3D(2, 2, 3);
        list2[2] = new Vector3D(7, 4, 0);

        Triangle tri2 = new Triangle(list2, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));
        

        //shapeList.add(mySphere_1);
        //shapeList.add(mySphere_2);
        //shapeList.add(mySphere_3);

        //lane p = new Plane(new Vector3D(0, 0, -5), new Material(new Color(0, 255, 0)));

        Plane plane = new Plane(0, 1, 0, -3, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.0, 1, 0.2)));

        TTriangle tTriangle = new TTriangle(new Vector3D(0, 0, -1), new Vector3D(1, 1, 3), new Vector3D(2, 0, -1), new Material(MaterialType.LAMBERTIAN, new Vector3D(0.0, 0.0, 1)));

        //shapeList.add(tri);
        //shapeList.add(tri2);
        //shapeList.add(plane);
        shapeList.add(tri);
        shapeList.add(mySphere_1);
        shapeList.add(mySphere_4);

        oclusionObject = new OclusionObject(shapeList);


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


    public static Vector3D color(Ray ray, IShape mainShape, int recursionDepth)
    {
        double EPSILON = 0.000000000001;
        RayInfo rayInfo = mainShape.intersects(ray, 0); //maybe implement a min and max intersection distance.
        if (rayInfo.didIntersect)
        {

            ScatterInfo scatterInfo;
            if (recursionDepth < 100 && (scatterInfo = rayInfo.material.scatter(ray, rayInfo)).didScatter)
            {
                return VecMath.multiply(scatterInfo.attenuation, color(scatterInfo.scattered, mainShape, recursionDepth + 1));
            } else
            {
                return new Vector3D(0, 0, 0);
            }

            /*
            if (recursionDepth < 100)
            {
                Vector3D target = rayInfo.point.add(rayInfo.normal).add(VecMath.random_in_unit_sphere());
                return color(new Ray(rayInfo.point, target.subtract(rayInfo.point)), shapeListl, recursionDepth + 1).scalarMultiply(0.5);
            } else
            {
                return new Vector3D(0, 0, 0);
            }
            */

            //check normalization, normalizing the vector here might improve shading?
            //return new Vector3D(rayInfo.normal.getX() + 1.0, rayInfo.normal.getY() + 1.0, rayInfo.normal.getZ() + 1.0).scalarMultiply(0.5);

        } else //else, lerp to create a gradient background.
        {
            Vector3D unitVectorDirection = VecMath.getUnitVector(ray.dir.normalize());
            double multiplier = 0.9;
            double t = multiplier * unitVectorDirection.getY() + 1.0;
            return lerp(new Vector3D(1.0, 1.0, 1.0), Globals.bkgColor, t);
        }
    }

    /**
     * linear interpolation between two vectors.
     *
     * @param startValue
     * @param endValue
     * @param t
     * @return
     */
    public static Vector3D lerp(Vector3D startValue, Vector3D endValue, double t)
    {
        return startValue.scalarMultiply(1.0 - t).add(endValue.scalarMultiply(t));
    }

}
