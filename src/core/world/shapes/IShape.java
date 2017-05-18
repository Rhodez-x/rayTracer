package core.world.shapes;

import core.world.ray.Ray;
import core.world.ray.RayInfo;

public interface IShape
{
    RayInfo intersects(Ray ray, double dist);

    void paint(int x, int y);

    RayInfo hit(Ray ray, double tmin, double tmax);

}
