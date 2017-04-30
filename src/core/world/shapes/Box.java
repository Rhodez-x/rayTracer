package core.world.shapes;

import core.world.shading.Material;
import core.world.ray.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * A box is described by the following six plane equations:
 * x = 0   x = r_x
 * y = 0   y = r_y
 * z = 0   z = r_z
 * r_x,r_y,r_z are the dimensions of the box.
 * Created by Gabriel Jadderson on 29-04-2017.
 */
public class Box implements IShape
{

    Vector3D dimension;

    public Box(Vector3D position, Vector3D dimension, Material material)
    {
        this.dimension = dimension;
    }

    @Override
    public boolean intersects(Ray ray, double dist)
    {
        //t = (r_x - S_x) / V_x

        //if intersect is true then the following applies 0 <= [P(t)]_y <= r_y and 0 <= [P(t)]_z <= r_z
        return true;
    }

    @Override
    public void paint(int x, int y)
    {

    }
}
