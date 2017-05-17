package core;

import core.gui.GUI;
import core.world.camera.Camera;
import core.world.ray.Ray;
import core.world.shading.Color;
import core.world.shading.Material;
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

    public static void main(String[] args)
    {
        outputRenderedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


        double focusDistance = 10.0;
        double aperture = 0.1;
        double vfov = 50;
        int sampleCount = 1; //for supersampling don't use for now.

        Vector3D startPos = new Vector3D(10, 2, 8);
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

                double u = (x) / ((double) (WIDTH));
                double v = (y) / ((double) (HEIGHT));

                Ray ray = camera.getRay(u, v);

                //ray.dir = Camera.projectToView(new Vector3D(x, y, 1));
                //System.out.println(myRay.dir);
                //System.out.println(myRay.orig);
                Globals.outputRenderedImage.getRaster().setPixel(x, y, background.getRGBArray());

                for (IShape shape : shapeList)
                {
                    if (shape.intersects(ray, 1).didIntersect)
                    {
                        //System.out.println(shape.getDepth());
                        shape.paint(x, y);
                    }
                }
            }
        }
    }


    public static void createAndAddObjects()
    {
        Sphere mySphere_1 = new Sphere(0.0, 2.5, 2.5, 1.0, new Color(0, 255, 0));
        Sphere mySphere_2 = new Sphere(-3.0, -1.0, 2.5, 2.0, new Color(200, 150, 150));
        Sphere mySphere_3 = new Sphere(-4.0, 0.0, 1.5, 1.0, new Color(50, 100, 250));
        Vector3D[] list = new Vector3D[3];

        list[0] = new Vector3D(0, 0, 3);
        list[1] = new Vector3D(1, 1, 3);
        list[2] = new Vector3D(2, 0, 3);

        Triangle tri = new Triangle(list, new Material(new Color(255, 255, 250)));


        //shapeList.add(mySphere_1);
        //shapeList.add(mySphere_2);
        //shapeList.add(mySphere_3);

        //lane p = new Plane(new Vector3D(0, 0, -5), new Material(new Color(0, 255, 0)));

        Plane plane = new Plane(new Material(new Color(0, 255, 0)), 0, 1, 0, -3);
        TTriangle tTriangle = new TTriangle(new Vector3D(0, 0, -1), new Vector3D(1, 1, 3), new Vector3D(2, 0, -1), new Material(new Color(0, 0, 255)));

        shapeList.add(plane);
        shapeList.add(mySphere_1);
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
