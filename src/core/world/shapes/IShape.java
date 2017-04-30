package core.world.shapes;

import core.world.ray.Ray;

public interface IShape
{
    boolean intersects(Ray ray, double dist);

    void paint(int x, int y);

}
