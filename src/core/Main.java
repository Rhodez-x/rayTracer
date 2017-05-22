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
import org.w3c.dom.css.Counter;

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

        doAndMeasure("Uden boundig volumes: ", () -> {rayTrace(camera);});

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
                        oclusionObject = new OclusionObject(bound.listOfShapes);
                        color = doTrace(ray, oclusionObject, globalLight);
                    }
                }
                if (!someIntersection)  {
                    Vector3D unitVectorDirection = VecMath.getUnitVector(ray.dir.normalize());
                    double multiplier = 0.9;
                    double t = multiplier * -unitVectorDirection.getY() + 1.0;
                    color = lerp(new Vector3D(1.0, 1.0, 1.0), Globals.bkgColor, t);
                    color = Vector3D.ZERO;
                }
                    color = VecMath.sqrt(color);
                    int ir = ((int) (255.99 * color.getX()));
                    int ig = ((int) (255.99 * color.getY()));
                    int ib = ((int) (255.99 * color.getZ()));

                    rgb[0] = ir;
                    rgb[1] = ig;
                    rgb[2] = ib;
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
        ArrayList<IShape> listForGlobalBox = new ArrayList<>();

        // Bonding volume and objects for the first bounding box
        Sphere boundingSphere_top_left = new Sphere(new Vector3D(-2.5, 1.5, -8), 4, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0, 0)));
        ArrayList<IShape> listForBoxOne = new ArrayList<>();
        for (int i = -5; i < 1; i++) {
            for (int j = -1; j < 5; j++) {
                Sphere mySphere_1 = new Sphere(new Vector3D(i, j, -8), 0.1, new Material(MaterialType.LAMBERTIAN, new Vector3D(Math.random(), Math.random(), Math.random())));
                listForGlobalBox.add(mySphere_1);
                listForBoxOne.add(mySphere_1);
            }
        }

        BoundingVol boxOne = new BoundingVol(boundingSphere_top_left, listForBoxOne);
        
        // Bonding volume and objects for the second bounding box
        Sphere boundingSphere_buttom_right = new Sphere(new Vector3D(3.5, -6.5, -8), 4, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0, 0)));
        ArrayList<IShape> listForBoxTwo = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            for (int j = -9; j < -3; j++) {
                Sphere mySphere_1 = new Sphere(new Vector3D(i, j, -8), 0.1, new Material(MaterialType.LAMBERTIAN, new Vector3D(Math.random(), Math.random(), Math.random())));
                listForGlobalBox.add(mySphere_1);
                listForBoxTwo.add(mySphere_1);
            }
        }

        BoundingVol boxTwo = new BoundingVol(boundingSphere_buttom_right, listForBoxTwo);

        
        // For shapes theres is not in a bounding box (Checking for intersection every time.)
        // a problem with depth if these objects are in the area of objects in a bounding box.
        
        
        
        Vector3D[] list = new Vector3D[3];

        list[0] = new Vector3D(0, 0, 5);
        list[1] = new Vector3D(1, 0, 5);
        list[2] = new Vector3D(1, 3, 5);
        list[0] = new Vector3D(-2.43, -2.34, 5.46);
        list[1] = new Vector3D(2.56, 2, 3.56);
        list[2] = new Vector3D(1.78, 3.65, 2);

        Triangle tri = new Triangle(list, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));

        Vector3D[] list2 = new Vector3D[3];
        list2[0] = new Vector3D(0, 1, -0);
        list2[1] = new Vector3D(0.5, 0.5, -1.6);
        list2[2] = new Vector3D(1, 1, -0);
        
        Triangle tri2 = new Triangle(list2, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));

        
        Vector3D[] list3 = new Vector3D[3];

        list3[0] = new Vector3D(0, 0, -0);
        list3[1] = new Vector3D(1, 1, -0);
        list3[2] = new Vector3D(1, 0, -0);

        Triangle tri3 = new Triangle(list3, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));

        Vector3D[] list4 = new Vector3D[3];
        list4[0] = new Vector3D(0.5, 0.5, -1.6);
        list4[1] = new Vector3D(0, 0, -0);
        list4[2] = new Vector3D(1, 0, -0);
        
        Triangle tri4 = new Triangle(list4, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));

        Vector3D[] list5 = new Vector3D[3];

        list5[0] = new Vector3D(0, 1, -0);
        list5[1] = new Vector3D(0, 0, -0);
        list5[2] = new Vector3D(0.5, 0.5, -1.6);

        Triangle tri5 = new Triangle(list5, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));

        Vector3D[] list6 = new Vector3D[3];
        list6[0] = new Vector3D(0, 0, -0);
        list6[1] = new Vector3D(0, 1, -0);
        list6[2] = new Vector3D(1, 1, -0);

        Triangle tri6 = new Triangle(list6, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.5, 0.5, 0)));
        
        Sphere globalSphere = new Sphere(new Vector3D(3, -4, -8), 100.0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.6, 0.4, 0.4)));
        
        Disk disk = new Disk(4, 0, -5,1, 3, 1, 2, 3, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.2, 0.4, 0.1)));
//        for (int i = -5; i < 5; i++) {
//            for (int j = -5; j < 5; j++) {
//                if (i == 0 && j == 0) {
//                    
//                }else {
//                Sphere onecirkle = new Sphere(new Vector3D(i, j, -5), 0.1, new Material(MaterialType.LAMBERTIAN, new Vector3D(Math.random(), Math.random(), Math.random())));
//                listForGlobalBox.add(onecirkle);
//                    
//                }
//            }
//        }
        

        //listForGlobalBox.add(tri);
//        listForGlobalBox.add(tri2);
//        listForGlobalBox.add(tri3);
//        listForGlobalBox.add(tri4);
//        listForGlobalBox.add(tri5);
//        listForGlobalBox.add(tri6);

        //listForGlobalBox.add(disk);

        BoundingVol globalBox = new BoundingVol(globalSphere, listForGlobalBox);
        //boundingList.add(globalBox);        
        boundingList.add(boxOne);
        boundingList.add(boxTwo);
        
        

        Plane plane = new Plane(0, 1, -0.1, 0, new Material(MaterialType.LAMBERTIAN, new Vector3D(0.0, 0.2, 0.1)));

        //shapeList.add(tri);
        //shapeList.add(tri2);
        shapeList.add(plane);
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
        conuter++;
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
            public static void doAndMeasure( String caption, Runnable runnable ) {
            long tStart = System.currentTimeMillis();
            runnable.run();
            System.out.println( caption + " took " + (System.currentTimeMillis() - tStart) + "ms" );
}

}
