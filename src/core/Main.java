package core;

import core.gui.GUI;
import core.math.Color;
import core.math.Vector3;
import core.shapes.Shape;
import core.shapes.Sphere;
import core.world.Light;
import core.world.Ray;

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
        
        Sphere mySphere2 = new Sphere(200.0f, 200.0f, 100.0f, 100.0f, new Color(0, 255, 0));
        Sphere mySphere3 = new Sphere(100.0f, 100.0f, 200.0f, 100.0f, new Color(0, 255, 0));
        Sphere mySphere4 = new Sphere(400.0f, 400.0f, 100.0f, 30.0f, new Color(0, 255, 0));

        shapeList.add(mySphere3);
        shapeList.add(mySphere2);
        shapeList.add(mySphere4);

        Light light = new Light();
        light.Begin();

        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Ray myRay = new Ray(new Vector3((float) 200, (float) 200, -300.0f), Vector3.sub(new Vector3(x, y, -10), new Vector3(200, 200, -300)));

                //Ray myRay = new Ray(new Vector3((double) x, (double) y, -30.0f), new Vector3(0, 0, 1));
                for(Shape shape : shapeList)
                {
                    if(shape.intersects(myRay, 1))
                    {
                        System.out.println(shape.getDepth());
                        shape.getMaterial().paint(x, y, shape.getMaterial().getPaint(x, y));
                    }
                }
                //background.paint(x, y, background.color);
                //for (Shape shape : shapeList)
                //{
                //    if (shape.intersects(myRay, 1))
                //    {
                //        System.out.println(shape.getDepth());
                //        shape.getMaterial().paint(x, y, shape.getMaterial().color.calcDepth(shape.getDepth()));
                //    }
                //}
            }
        }

        SwingUtilities.invokeLater(() -> new GUI());
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
