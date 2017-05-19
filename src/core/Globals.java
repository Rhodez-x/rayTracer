package core;

import core.gui.GUI;
import core.world.shapes.IShape;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Gabriel Jadderson on 19-04-2017.
 */
public class Globals
{
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int VIEW_WIDTH = 1;
    public static final int VIEW_HEIGHT = 1;
    public static final float FOV = 90;

    public static BufferedImage outputRenderedImage;

    public static ArrayList<IShape> shapeList = new ArrayList<>();

    public static Vector3D bkgColor = new Vector3D(0.5, 0.5, 0.5);

    public static GUI gui;
}
