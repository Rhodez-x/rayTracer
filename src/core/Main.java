package core;

import core.gui.GUI;
import core.math.Color;
import core.shapes.IShape;
import core.shapes.Sphere;
import core.world.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static core.Globals.*;

public class Main
{
    public static void main(String[] args)
    {
        outputRenderedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        createAndAddObjects();
        rayTrace();

        SwingUtilities.invokeLater(() -> Globals.gui = new GUI());
    }

    public static void rayTrace()
    {
        // Light light = new Light();
        // light.Begin();
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Vector3D startPos = new Vector3D(0, 0, -8);
                Vector3D direction = new Vector3D((double) x / WIDTH * VIEW_WIDTH - VIEW_WIDTH / 2.0, (double) y / HEIGHT * VIEW_HEIGHT - VIEW_HEIGHT / 2.0, 1);

                Ray myRay = new Ray(startPos, direction);
                //System.out.println(myRay.dir);
                //System.out.println(myRay.orig);


                for (IShape shape : shapeList)
                {
                    if (shape.intersects(myRay, 1))
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
        Sphere mySphere_1 = new Sphere(0.0, 0.0, 2.5, 1.0, new Color(0, 255, 0));
        Sphere mySphere_2 = new Sphere(-3.0, -1.0, 2.5, 1.0, new Color(200, 150, 150));
        Sphere mySphere_3 = new Sphere(-4.0, 0.0, 2.5, 1.0, new Color(50, 100, 250));

        shapeList.add(mySphere_1);
        shapeList.add(mySphere_2);
        shapeList.add(mySphere_3);
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
