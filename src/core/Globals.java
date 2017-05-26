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
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final double EPSILON = 0.0000000001;

    public static BufferedImage outputRenderedImage;

    public static ArrayList<IShape> shapeList = new ArrayList<>();

    public static ArrayList<BoundingVol> boundingList = new ArrayList<>();

    //public static Vector3D bkgColor = new Vector3D(0.5, 0.5, 0.5);
    public static Vector3D bkgColor = new Vector3D(0.8, 0.8, 0.4);

    public static GUI gui;
}
