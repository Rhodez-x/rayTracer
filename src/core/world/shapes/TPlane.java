package core.world.shapes;


import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 17-05-2017.
 */
public class TPlane implements IShape
{

    Material material;
    double A;
    double B;
    double C;
    double D;

    public TPlane(Material material, double a, double b, double c, double d)
    {
        this.material = material;
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
    }


    @Override
    public RayInfo intersects(Ray ray, double min, double max)
    {
        RayInfo rayInfo = new RayInfo();

        Vector3D origin = ray.orig;
        Vector3D direction = ray.dir.normalize();

        Vector3D planeNormal = getNormal();

        double vd = planeNormal.dotProduct(direction);

        if (vd == 0)
        { //return false because the ray is parallel to the plane and no intersection occurs.
            rayInfo.didIntersect = false;
            return rayInfo;
        }

        double v0 = -(planeNormal.dotProduct(origin) + D);

        double t = v0 / vd;

        Vector3D interSectionPoint;

        if (t < 0)
        {
            rayInfo.didIntersect = false;
            return rayInfo;
        } else
        {
            interSectionPoint = new Vector3D(origin.getX() + direction.getX() * t, origin.getY() + direction.getY() * t, origin.getZ() + direction.getZ() * t);
        }

        if (vd < 0)
        {
            rayInfo.normal = planeNormal;
        } else
        {
            rayInfo.normal = planeNormal.negate();
        }

        rayInfo.t = t;
        rayInfo.point = interSectionPoint;
        rayInfo.didIntersect = true;
        rayInfo.material = material;

        return rayInfo;
    }

    public Vector3D getNormal()
    {
        return new Vector3D(A, B, C);
    }

}
