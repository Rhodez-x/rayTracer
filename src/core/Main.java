package core;

import core.gui.GUI;
import core.world.camera.Camera;
import core.world.ray.Ray;
import core.world.shading.Color;
import core.world.shapes.IShape;
import core.world.shapes.Sphere;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static core.Globals.*;
import core.world.shading.Material;
import core.world.shapes.Triangle;

public class Main
{
    public static void main(String[] args)
    {
        outputRenderedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        createAndAddObjects(); //creation of all objects happens here


        Vector3D startPos = new Vector3D(0, 0, -8);
        Ray myRay = new Ray(startPos, new Vector3D(0, 0, 0));
        rayTrace(myRay);

        SwingUtilities.invokeLater(() -> Globals.gui = new GUI());
    }

    public static void rayTrace(Ray ray)
    {
        // Light light = new Light();
        // light.Begin();
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {

                ray.dir = Camera.projectToView(new Vector3D(x, y, 1));
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
        //Sphere mySphere_1 = new Sphere(0.0, 0.0, 2.5, 1.0, new Color(0, 255, 0));
        //Sphere mySphere_2 = new Sphere(-3.0, -1.0, 2.5, 2.0, new Color(200, 150, 150));
        //Sphere mySphere_3 = new Sphere(-4.0, 0.0, 1.5, 1.0, new Color(50, 100, 250));
        Vector3D[] list = new Vector3D[3];
        list[0] = new Vector3D(0,0.1,-100);
        list[1] = new Vector3D(0.1,0.2,-100);
        list[2] = new Vector3D(0.2,0,-100);
        Triangle tri = new Triangle(list, new Material(new Color(50, 100, 250)));

        //shapeList.add(mySphere_1);
        //shapeList.add(mySphere_2);
        //shapeList.add(mySphere_3);
        shapeList.add(tri);
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
