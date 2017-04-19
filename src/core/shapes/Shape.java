package core.shapes;

import core.math.Material;
import core.world.Ray;

public interface Shape
{

    public boolean intersects(Ray ray, double dist);

    public float getDepth();

    public Material getMaterial();
}
