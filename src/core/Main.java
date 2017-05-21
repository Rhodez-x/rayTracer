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
import java.util.ArrayList;
import javafx.geometry.BoundingBox;

public class Main
{

    public static Camera camera;

    public static OclusionObject oclusionObject;

    public static Light globalLight;
    
    public static int conuter = 0;
    
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
                int[] rgb = new int[3];
                boolean someIntersection = false;
                for (BoundingVol bound : boundingList) {
                    if (bound.shape.intersects(ray, -8, 100).didIntersect) {
                        someIntersection = true;
                        conuter++;
                        oclusionObject = new OclusionObject(bound.listOfShapes);
                        color = doTrace(ray, oclusionObject, globalLight);

                        color = VecMath.sqrt(color);
                        int ir = ((int) (255.99 * color.getX()));
                        int ig = ((int) (255.99 * color.getY()));
                        int ib = ((int) (255.99 * color.getZ()));
                        
                        rgb[0] = ir;
                        rgb[1] = ig;
                        rgb[2] = ib;
                    }
                }
                if (!someIntersection)  {
                    rgb[0] = 255;
                    rgb[1] = 0;
                    rgb[2] = 0;
                }
                    Globals.outputRenderedImage.getRaster().setPixel(x, Globals.HEIGHT - 1 - y, rgb);
            }
        }
        System.out.println(conuter);
    }

    public static void createAndAddObjects()
    {

        globalLight = new Light();
        globalLight.position = new Vector3D(10, 10, -2);
        globalLight.ambience = 0.1;
        
        // Bonding volume and objects for the first bounding box
        Sphere boundingSphere_top_left = new Sphere(new Vector3D(-2, 1.4, -8), 2.7, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0, 0)));
        ArrayList<IShape> listForBoxOne = new ArrayList<>();
        
        Sphere mySphere_1 = new Sphere(new Vector3D(-3, 2, -8), 1.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(Math.random(), Math.random(), Math.random())));
        Sphere mySphere_1_2 = new Sphere(new Vector3D(-2, 3, -7), 1.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(Math.random(), Math.random(), Math.random())));
        Sphere mySphere_1_3 = new Sphere(new Vector3D(-2, 0, -7), 1.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(Math.random(), Math.random(), Math.random())));


        listForBoxOne.add(mySphere_1);
        listForBoxOne.add(mySphere_1_2);
        listForBoxOne.add(mySphere_1_3);

        BoundingVol boxOne = new BoundingVol(boundingSphere_top_left, listForBoxOne);
        boundingList.add(boxOne);
        
        // Bonding volume and objects for the second bounding box
        Sphere boundingSphere_buttom_right = new Sphere(new Vector3D(3, -4, -8), 2.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0, 0)));
        ArrayList<IShape> listForBoxTwo = new ArrayList<>();
        Sphere mySphere_2 = new Sphere(new Vector3D(3, -4, -8), 2.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.6, 0.4, 0.4)));
        listForBoxTwo.add(mySphere_2);
        BoundingVol boxTwo = new BoundingVol(boundingSphere_buttom_right, listForBoxTwo);
        boundingList.add(boxTwo);
        
        // For shapes theres is not in a bounding box (Checking for intersection every time.)
        // a problem with depth if these objects are in the area of objects in a bounding box.
        
        
        
//        Vector3D[] list = new Vector3D[3];
//
//        list[0] = new Vector3D(-1, 0, 0);
//        list[1] = new Vector3D(3, 3, 2);
//        list[2] = new Vector3D(6, 0, 0);
//
//        Triangle tri = new Triangle(list, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));
//
//        Vector3D[] list2 = new Vector3D[3];
//        list2[0] = new Vector3D(3, 3, 2);
//        list2[1] = new Vector3D(-1, 0, 0);
//        list2[2] = new Vector3D(-4, 3, 0);
//
//        Triangle tri2 = new Triangle(list2, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));

        
                
        

        Plane plane = new Plane(0, 1, -0.1, 0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.0, 0.2, 0.1)));

        Disk disk = new Disk(4, 0, -5,1, 3, 1, 2, 3, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.2, 0.4, 0.1)));
        //shapeList.add(tri);
        //shapeList.add(tri2);
        shapeList.add(plane);
        shapeList.add(disk);
//        shapeList.add(tri);
//        shapeList.add(tri2);
//        
//        
//        shapeList.add(mySphere_1);
//        shapeList.add(mySphere_2);

        //oclusionObject = new OclusionObject(shapeList);

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
