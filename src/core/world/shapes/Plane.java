package core.world.shapes;


import core.Globals;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 17-05-2017.
 */
public class Plane implements IShape
{

    Material material;
    double A;
    double B;
    double C;
    double D;
    Vector3D planeNormal;

    public Plane(double a, double b, double c, double d, Material material)
    {
        this.material = material;
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
        this.planeNormal = new Vector3D(A, B, C);
    }


    //KILDE: BOGEN - INTRODUCTION TO RAYTRACING side 50.
    public RayInfo intersects(Ray ray, double min, double max)
    {

        RayInfo rayInfo = new RayInfo();
        rayInfo.material = material;

        Vector3D origin = ray.orig;
        Vector3D direction = ray.dir.normalize();


        double vd = planeNormal.dotProduct(direction);

        if (vd == Globals.EPSILON)//return false because the ray is parallel to the plane and no intersection occurs.
            return rayInfo;


        double v0 = -(planeNormal.dotProduct(origin) + D);

        double t = v0 / vd;

        Vector3D interSectionPoint;

        if (t < 0)
            return rayInfo;
        else
            interSectionPoint = new Vector3D(origin.getX() + direction.getX() * t, origin.getY() + direction.getY() * t, origin.getZ() + direction.getZ() * t);


        if (!(t < max && t > min))
            return rayInfo;


        if (vd < 0)
            rayInfo.normal = planeNormal;
        else
            rayInfo.normal = planeNormal.negate();


        rayInfo.t = t;
        rayInfo.point = interSectionPoint;
        rayInfo.normal = rayInfo.normal.normalize();
        rayInfo.didIntersect = true;

        return rayInfo;
    }

}
