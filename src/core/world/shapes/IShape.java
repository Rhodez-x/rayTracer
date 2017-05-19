package core.world.shapes;

import core.world.ray.Ray;
import core.world.ray.RayInfo;

public interface IShape
{
    RayInfo intersects(Ray ray, double min, double max);
}
