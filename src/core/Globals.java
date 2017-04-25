package core;

import core.math.Color;
import core.math.Material;
import core.shapes.Shape;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Gabriel Jadderson on 19-04-2017.
 */
public class Globals
{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    public static final float  FOV = 90;

    public static BufferedImage outputRenderedImage;

    public static ArrayList<Shape> shapeList = new ArrayList<>();

    public static Material background = new Material(new Color(255, 255, 255)); //the material used for bkg
}
