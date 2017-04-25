package core.shapes;

import core.math.Material;
import core.world.Ray;

public interface Shape
{
    boolean intersects(Ray ray, double dist);

    double getDepth();

    Material getMaterial();
}
