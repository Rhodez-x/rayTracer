package core;

import core.gui.GUI;
import core.math.Color;
import core.math.Vector3;
import core.shapes.Shape;
import core.shapes.Sphere;
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
        //core.shapes.Sphere mySphere3 = new core.shapes.Sphere(150.0f, 150.0f, 50.0f, 50.0f, new core.math.CColor(0, 255, 0, 255));
        shapeList.add(mySphere2);
        //shapeList.add(mySphere3);

        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Ray myRay = new Ray(new Vector3((float) x, (float) y, -30.0f), new Vector3(0, 0, 1));
                background.paint(x, y, background.color);
                for (Shape shape : shapeList)
                {
                    if (shape.intersects(myRay, 1))
                    {
                        System.out.println(shape.getDepth());
                        shape.getMaterial().paint(x, y, shape.getMaterial().color.calcDepth(shape.getDepth()));
                    }
                }
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
