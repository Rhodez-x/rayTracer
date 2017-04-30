package core.world.shapes;

import core.world.ray.Ray;

/**
 * Created by Gabriel Jadderson on 30-04-2017.
 */
public class TriangulatedMesh implements IShape
{
    @Override
    public boolean intersects(Ray ray, double dist)
    {
        return false;
    }

    @Override
    public void paint(int x, int y)
    {

    }
}
