package core.shapes;

import core.math.Color;
import core.math.Material;
import core.world.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * USES CLOCKWISE WINDING ORDER!
 */
public class Triangle implements Shape
{
    private Vector3D pos;
    private double height;
    private double width;
    private Color color;

    public Triangle()
    {
        pos = new Vector3D(0,0,0);
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
