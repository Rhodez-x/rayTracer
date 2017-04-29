package core.shapes;

import core.world.Ray;

public interface IShape
{
    boolean intersects(Ray ray, double dist);

    void paint(int x, int y);

}
