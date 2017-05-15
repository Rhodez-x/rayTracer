package core.world.shapes;

import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 12-05-2017.
 */
public class Plane implements IShape
{

    public double a, b, c, d;
    public Vector3D normal;
    public Material material;

    public Plane(double a, double b, double c, double d)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.normal = new Vector3D(a, b, c).normalize();
    }

    @Override
    public RayInfo hit(Ray ray, double tMin, double tMax)
    {
        return null;
    }

    @Override
    public RayInfo intersects(Ray ray)
    {
        RayInfo rayInfo = new RayInfo();

        double denominator = (a * ray.dir.getX() + b * ray.dir.getY() + c * ray.dir.getZ());
        if (denominator == 0.0) rayInfo.didIntersect = false;

        double t = -(a * ray.orig.getX() + b * ray.orig.getY() + c * ray.orig.getZ() + d) / denominator;

        if (t < 0) rayInfo.didIntersect = false;

        rayInfo.normal = normal;
        rayInfo.t = t;
        rayInfo.didIntersect = true;

        rayInfo.material = material;
        return rayInfo;
    }
}
