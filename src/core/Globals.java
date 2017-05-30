package core;

import core.gui.GUI;
import core.world.shapes.BoundingVol;
import core.world.shapes.IShape;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Gabriel Jadderson on 19-04-2017.
 */
public class Globals
{

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    public static final double EPSILON = 0.0000000001;

    public static BufferedImage outputRenderedImage;

    public static ArrayList<IShape> shapeList = new ArrayList<>();

    public static ArrayList<BoundingVol> boundingList = new ArrayList<>();

    //public static Vector3D bkgColor = new Vector3D(0.5, 0.5, 0.5);
    //public static Vector3D bkgColor = new Vector3D(0.5, 0.7, 1.0);
    public static Vector3D bkgColor = new Vector3D(0.8, 0.8, 0.4);
    //public static Vector3D bkgColor = new Vector3D(219 / 255.999, 112 / 255.999, 147 / 255.999); //too red
    //public static Vector3D bkgColor = new Vector3D(255 / 255.999, 182 / 255.999, 193 / 255.999);
    //public static Vector3D bkgColor = new Vector3D(240 / 255.999, 180 / 255.999, 180 / 255.999);

    public static GUI gui;
}
