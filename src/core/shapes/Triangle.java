package core.shapes;

import core.math.Color;
import core.math.Material;
import core.math.Vector3;
import core.world.Ray;

/**
 * USES CLOCKWISE WINDING ORDER!
 */
public class Triangle implements Shape
{
    private Vector3 pos;
    private double height;
    private double width;
    private Color color;

    public Triangle()
    {
        pos = new Vector3();
        color = new Color();
        height = 0;
        width = 0;
    }

    @Override
    public boolean intersects(Ray ray, double dist)
    {
        return false;
    }

    @Override
    public double getDepth()
    {
        return 0;
    }

    @Override
    public Material getMaterial()
    {
        return null;
    }
}
