package core;

import core.concurrency.ConcurrentRayTracer;
import core.gui.GUI;
import core.world.camera.Camera;
import core.world.light.Light;
import core.world.math.VecMath;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import core.world.shapes.*;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import static core.Globals.*;

public class Main
{

    public static Camera camera;

    public static OclusionObject oclusionObject;

    public static Light globalLight;

    public static int conuter = 0;
    public static ArrayList<IShape> listForGlobalBox = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        long startTime = System.nanoTime();
        outputRenderedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        double zoom = 10.0;
        double viewWidth = 45;

        Vector3D origin = new Vector3D(0, 0, 0);
        Vector3D direction = new Vector3D(0, 0, -10);

        camera = new Camera(origin, direction, viewWidth, WIDTH, HEIGHT, zoom);

        createAndAddObjects(); //creation of all objects happens here

        Main main = new Main(); //i don't like this but i was not allowed to use "this"
        ConcurrentRayTracer concurrentRayTracer = new ConcurrentRayTracer(camera, main); //HOLY SHIT
        //rayTrace(camera);

        long endTime = System.nanoTime();

        System.out.println("Took: " + (endTime - startTime) / 1e9 + " seconds.");

        SwingUtilities.invokeLater(() -> Globals.gui = new GUI());
    }

    public static void rayTrace(Camera camera)
    {

        for (int y = 0; y < HEIGHT; y++)
        {
            System.out.println("pixelrow: " + conuter);
            for (int x = 0; x < WIDTH; x++)
            {

                Vector3D color = Vector3D.ZERO;

                Ray ray = camera.calculateRayAt(x / (double) WIDTH, y / (double) HEIGHT);


                color = VecMath.plusEqual(color, traceBoundingVolumes(ray));


                renderColor(color, x, y);
            }
            conuter++;

        }
    }


    public static void createAndAddObjects() throws FileNotFoundException, IOException
    {

        globalLight = new Light();
        globalLight.position = new Vector3D(10, 10, 22);
        globalLight.ambience = 0.25;


        //createObjObject("Wolf_xxsmall.obj", -0.2, -0.8, -4);
        createObjObject("Nefertiti_small_moved_10k.obj", -0.2, -2.2, -8);


        // Bonding volume and objects for the first bounding box
        Sphere boundingSphere_top_left = new Sphere(new Vector3D(-2.5, 4.5, -8), 4, new Material(new Vector3D(0.5, 0, 0)));
        ArrayList<IShape> listForBoxOne = new ArrayList<>();
        for (int i = -5; i < 1; i++)
        {
            for (int j = 2; j < 8; j++)
            {
                Sphere mySphere_1 = new Sphere(new Vector3D(i, j, -8), 0.1, new Material(new Vector3D(Math.random(), Math.random(), Math.random())));
                //listForGlobalBox.add(mySphere_1);
                listForBoxOne.add(mySphere_1);
            }
        }

        BoundingVol boxOne = new BoundingVol(boundingSphere_top_left, listForBoxOne);

        // Bonding volume and objects for the second bounding box
        Sphere boundingSphere_buttom_right = new Sphere(new Vector3D(3.5, -4.5, -8), 4, new Material(new Vector3D(0.5, 0, 0)));
        ArrayList<IShape> listForBoxTwo = new ArrayList<>();
        for (int i = 1; i < 7; i++)
        {
            for (int j = -7; j < -1; j++)
            {
                Sphere mySphere_1 = new Sphere(new Vector3D(i, j, -8), 0.1, new Material(new Vector3D(Math.random(), Math.random(), Math.random())));
                //listForGlobalBox.add(mySphere_1);
                listForBoxTwo.add(mySphere_1);
            }
        }

        BoundingVol boxTwo = new BoundingVol(boundingSphere_buttom_right, listForBoxTwo);


        // For shapes theres is not in a bounding box (Checking for intersection every time.)
        // a problem with depth if these objects are in the area of objects in a bounding box.


        Vector3D[] list = new Vector3D[3];

        list[0] = new Vector3D(0, 0, 15);
        list[1] = new Vector3D(0, 1, 15);
        list[2] = new Vector3D(1, 0, 15);

        Triangle tri = new Triangle(list, new Material(new Vector3D(0.5, 0.5, 0)));

        Vector3D[] list2 = new Vector3D[3];
        list2[0] = new Vector3D(1, 1, -5);
        list2[1] = new Vector3D(1, 1, -5);
        list2[2] = new Vector3D(1, 2, -5);

        Triangle tri2 = new Triangle(list2, new Material(new Vector3D(0.5, 0.5, 0)));


        Vector3D[] list3 = new Vector3D[3];

        list3[0] = new Vector3D(1, 1, -5);
        list3[1] = new Vector3D(2, 1, -5);
        list3[2] = new Vector3D(1, 1, -6);

        Triangle tri3 = new Triangle(list3, new Material(new Vector3D(0.5, 0.5, 0)));

        Vector3D[] list4 = new Vector3D[3];
        list4[0] = new Vector3D(2, 1, -5);
        list4[1] = new Vector3D(1, 2, -5);
        list4[2] = new Vector3D(1, 1, -6);

        Triangle tri4 = new Triangle(list4, new Material(new Vector3D(0.5, 0.5, 0)));

        Vector3D[] list5 = new Vector3D[3];

        list5[0] = new Vector3D(0, 1, -5);
        list5[1] = new Vector3D(0, 0, -5);
        list5[2] = new Vector3D(0.5, 0.5, -6.6);

        Triangle tri5 = new Triangle(list5, new Material(new Vector3D(0.5, 0.5, 0)));

        Vector3D[] list6 = new Vector3D[3];
        list6[0] = new Vector3D(0, 0, -5);
        list6[1] = new Vector3D(0, 1, -5);
        list6[2] = new Vector3D(1, 1, -5);

        Triangle tri6 = new Triangle(list6, new Material(new Vector3D(0.5, 0.5, 0)));

        Sphere globalSphere = new Sphere(new Vector3D(3, -4, -8), 100.0, new Material(new Vector3D(0.6, 0.4, 0.4)));

        Disk disk = new Disk(4, 0, -5, 1, 3, 1, 2, 3, new Material(new Vector3D(0.2, 0.4, 0.1)));
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


        listForGlobalBox.add(tri);
        //listForGlobalBox.add(tri2);
        //listForGlobalBox.add(tri3);
        //listForGlobalBox.add(tri4);
        //listForGlobalBox.add(tri5);
        //listForGlobalBox.add(tri6);

        //listForGlobalBox.add(disk);
        Sphere onecirkle = new Sphere(new Vector3D(0, 0, 15), 1, new Material(new Vector3D(Math.random(), Math.random(), Math.random())));
        listForGlobalBox.add(onecirkle);
        BoundingVol globalBox = new BoundingVol(globalSphere, listForGlobalBox);
        boundingList.add(globalBox);
        //boundingList.add(boxOne);
        //boundingList.add(boxTwo);


        Plane plane = new Plane(0, 1, -0.1, 0, new Material(new Vector3D(0.0, 0.2, 0.1)));

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


    public static void createObjObject(String filename, double x, double y, double z) throws FileNotFoundException, IOException
    {
        ArrayList<Vector3D> pointList = new ArrayList<>(600);
        ArrayList<IShape> localList = new ArrayList<>();

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double minZ = Double.MAX_VALUE;

        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        double maxZ = -100000;

        BufferedReader br = new BufferedReader(new FileReader(filename));
        try
        {
            String line = br.readLine();

            while (line != null)
            {
                String[] strSplit = line.split("\\s+");

                if (strSplit[0].equals("v"))
                {

                    int spaceIndexOne = strSplit[1].indexOf("/");
                    if (spaceIndexOne != -1)
                    {
                        strSplit[1] = strSplit[1].substring(0, spaceIndexOne);
                    }
                    double checkDob = Double.parseDouble(strSplit[1]);

                    if (minX > checkDob)
                    {
                        minX = checkDob;
                    }
                    if (maxX < checkDob)
                    {
                        maxX = checkDob;
                    }
                    int spaceIndexTwo = strSplit[2].indexOf("/");
                    if (spaceIndexOne != -1)
                    {
                        strSplit[2] = strSplit[2].substring(0, spaceIndexTwo);
                    }
                    checkDob = Double.parseDouble(strSplit[2]);
                    if (minY > checkDob)
                    {
                        minY = checkDob;
                    }
                    if (maxY < checkDob)
                    {
                        maxY = checkDob;
                    }
                    int spaceIndexThree = strSplit[3].indexOf("/");
                    if (spaceIndexOne != -1)
                    {
                        strSplit[3] = strSplit[3].substring(0, spaceIndexThree);
                    }
                    checkDob = Double.parseDouble(strSplit[3]);
                    if (maxZ < checkDob)
                    {
                        maxZ = checkDob;
                    }
                    if (minZ > checkDob)
                    {
                        minZ = checkDob;
                    }
                    pointList.add(new Vector3D(Double.parseDouble(strSplit[1]) + x, Double.parseDouble(strSplit[2]) + y, Double.parseDouble(strSplit[3]) + z)); // have to be a minus z axe.
                } else if (strSplit[0].equals("f"))
                {
                    int spaceIndexOne = strSplit[1].indexOf("/");
                    if (spaceIndexOne != -1)
                    {
                        strSplit[1] = strSplit[1].substring(0, spaceIndexOne);
                    }
                    int spaceIndexTwo = strSplit[2].indexOf("/");
                    if (spaceIndexTwo != -1)
                    {
                        strSplit[2] = strSplit[2].substring(0, spaceIndexTwo);
                    }
                    int spaceIndexThree = strSplit[3].indexOf("/");
                    if (spaceIndexOne != -1)
                    {
                        strSplit[3] = strSplit[3].substring(0, spaceIndexThree);
                    }

                    Vector3D[] newTriangle = new Vector3D[3];
                    newTriangle[0] = pointList.get((Integer.parseInt(strSplit[1]) - 1));
                    newTriangle[1] = pointList.get((Integer.parseInt(strSplit[2]) - 1));
                    newTriangle[2] = pointList.get((Integer.parseInt(strSplit[3]) - 1));

                    localList.add(new Triangle(newTriangle, new Material(new Vector3D(0.5, 0, 0))));
                }
                line = br.readLine();
            }
        } finally
        {
            br.close();
            System.out.println(filename + " is loaded");
        }
        double centerX = ((minX + maxX) / 2);
        double centerY = ((minY + maxY) / 2);
        double centerZ = ((minZ + maxZ) / 2);
        Vector3D tempVec = new Vector3D(centerX - maxX, centerY - maxY, centerZ - maxZ);
        Sphere localBoundingSphere = new Sphere(new Vector3D(centerX + x, centerY + y, centerZ + z), VecMath.length(tempVec) + 0.01, new Material(new Vector3D(0.0, 0.2, 0.1)));
        BoundingVol localBounding = new BoundingVol(localBoundingSphere, localList);
        boundingList.add(localBounding);
    }

    public static Vector3D traceBoundingVolumes(Ray ray)
    {
        //boolean someIntersection = false;
        for (BoundingVol bound : boundingList)
        {
            if (bound.shape.intersects(ray, 0.000000000001, Double.MAX_VALUE).didIntersect)
            {
                // someIntersection = true;
                oclusionObject = new OclusionObject(bound.listOfShapes);
                return doTrace(ray, oclusionObject, globalLight);
            }
        }
        // if (!someIntersection)
        return applyBackground(ray.dir);
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
            return applyBackground(ray.dir);
        }
    }


    public static Vector3D applyBackground(Vector3D rayDir)
    {
        Vector3D unitVectorDirection = VecMath.getUnitVector(rayDir.normalize());
        double multiplier = 0.9;
        double t = multiplier * -unitVectorDirection.getY() + 1.0;
        return lerp(new Vector3D(1.0, 1.0, 1.0), Globals.bkgColor, t);
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

    public static void renderColor(Vector3D color, int x, int y)
    {
        color = VecMath.sqrt(color); //color correction gamma2
        int r = ((int) (255.99 * color.getX()));
        int g = ((int) (255.99 * color.getY()));
        int b = ((int) (255.99 * color.getZ()));
        int[] rgb = new int[3];
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;
        Globals.outputRenderedImage.getRaster().setPixel(x, HEIGHT - 1 - y, rgb);
    }

    /**
     * easy shading
     *
     * @param rayInfo
     * @param light
     * @return
     */
    public static Vector3D shade(RayInfo rayInfo, Light light)
    {
        Vector3D c;
        double normalDotLightpos = rayInfo.normal.dotProduct(light.position.normalize());
        double ambiance = light.ambience + ((1.0 - light.ambience) * Math.max(0.0, normalDotLightpos));
        c = rayInfo.material.albedo.scalarMultiply(ambiance);
        return c;
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
