package core;

import core.gui.GUI;
import core.world.camera.Camera;
import core.world.light.Light;
import core.world.math.VecMath;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import core.world.shading.MaterialType;
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

    public static Light globalLight;

    public static void main(String[] args)
    {
        outputRenderedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


        double focusDistance = 10.0;
        double aperture = 0.1;
        double vfov = 50;

        Vector3D startPos = new Vector3D(0, 4, 10);
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
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {

                Vector3D color = Vector3D.ZERO;


                double u = (x) / ((double) (WIDTH));
                double v = (y) / ((double) (HEIGHT));
                Ray ray = camera.getRay(u, v);

                color = doTrace(ray, oclusionObject, globalLight);

                color = VecMath.sqrt(color);
                int ir = ((int) (255.99 * color.getX()));
                int ig = ((int) (255.99 * color.getY()));
                int ib = ((int) (255.99 * color.getZ()));
                int[] rgb = new int[3];
                rgb[0] = ir;
                rgb[1] = ig;
                rgb[2] = ib;
                Globals.outputRenderedImage.getRaster().setPixel(x, 599 - y, rgb);

            }
        }

    }


    public static void createAndAddObjects()
    {

        globalLight = new Light();
        globalLight.position = new Vector3D(10, 10, -2);
        globalLight.ambience = 0.1;


        Sphere mySphere_1 = new Sphere(new Vector3D(-4, 1.0, 0), 1.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(Math.random(), Math.random(), Math.random())));
        Sphere mySphere_4 = new Sphere(new Vector3D(-2, 2, -4.0), 2.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0, 0)));
        Sphere mySphere_2 = new Sphere(new Vector3D(1.8, 2, -10.0), 2.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.6, 0.4, 0.4)));
        Sphere mySphere_3 = new Sphere(new Vector3D(4.0, 1.15, 1.5), 1.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(Math.random(), Math.random(), Math.random())));
        Vector3D[] list = new Vector3D[3];

        list[0] = new Vector3D(-1, 0, 7);
        list[1] = new Vector3D(3, 3, 8);
        list[2] = new Vector3D(6, 0, 8);

        Triangle tri = new Triangle(list, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));

        Vector3D[] list2 = new Vector3D[3];
        list2[0] = new Vector3D(4, 0, 3);
        list2[1] = new Vector3D(2, 2, 3);
        list2[2] = new Vector3D(7, 4, 0);

        Triangle tri2 = new Triangle(list2, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));



        Plane plane = new Plane(0, 1, -0.1, 0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.0, 0.2, 0.1)));

        //shapeList.add(tri);
        //shapeList.add(tri2);
        shapeList.add(plane);
        //shapeList.add(tri);
        shapeList.add(mySphere_1);
        shapeList.add(mySphere_4);
        shapeList.add(mySphere_2);
        shapeList.add(mySphere_3);

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


    public static Vector3D shade(RayInfo rayInfo, Light light)
    {
        Vector3D c;
        double normalDotLightpos = rayInfo.normal.dotProduct(light.position.normalize());
        double ambiance = light.ambience + ((1.0 - light.ambience) * Math.max(0.0, normalDotLightpos));
        c = rayInfo.material.albedo.scalarMultiply(ambiance);
        return c;
    }

    public static Vector3D doTrace(Ray ray, IShape mainShape, Light light)
    {
        double EPSILON = 0.000000000001;
        RayInfo rayInfo = mainShape.intersects(ray, EPSILON, Double.MAX_VALUE); //maybe implement a min and max intersection distance.
        if (rayInfo.didIntersect)
        {
            return shade(rayInfo, light);
        } else //else, lerp to create a gradient background.
        {
            Vector3D unitVectorDirection = VecMath.getUnitVector(ray.dir.normalize());
            double multiplier = 0.9;
            double t = multiplier * -unitVectorDirection.getY() + 1.0;
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
