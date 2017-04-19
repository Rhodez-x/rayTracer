package core;

import core.math.CColor;
import core.math.Material;
import core.math.Vector3;
import core.shapes.Shape;
import core.shapes.Sphere;
import core.world.Ray;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    public static BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static File outfile = new File("out.png");
    public static ArrayList<Shape> shapeList = new ArrayList<>();
    public static Material background = new Material(new CColor(255,255,255,0));
    
    public static void main(String[] args) {
        
        Sphere mySphere2 = new Sphere(200.0f, 200.0f, 100.0f, 100.0f, new CColor(0, 255, 0, 255));
        //core.shapes.Sphere mySphere3 = new core.shapes.Sphere(150.0f, 150.0f, 50.0f, 50.0f, new core.math.CColor(0, 255, 0, 255));
        shapeList.add(mySphere2);
        //shapeList.add(mySphere3);
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Ray myRay = new Ray(new Vector3((float) 0, (float) 0, -30.0f), new Vector3(x,y,0) );
                background.paint(x, y, background.color);
                for (Shape shape : shapeList) {
                    if(shape.intersects(myRay, 1)){
                        System.out.println(shape.getDepth());
                        shape.getMaterial().paint(x, y, shape.getMaterial().color.calcDepth(shape.getDepth()));
                    }
                }
            }
        }

        try {
            ImageIO.write(bufferedImage, "png", outfile);
        } catch (IOException ex) {
            System.out.println("Damn..!!");
        }
    }
}
