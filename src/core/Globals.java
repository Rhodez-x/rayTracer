package core;

import core.gui.GUI;
import core.math.Color;
import core.math.Material;
import core.shapes.IShape;

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

    public static Material background = new Material(new Color(150, 70, 70)); //the material used for bkg

    public static GUI gui;
}
