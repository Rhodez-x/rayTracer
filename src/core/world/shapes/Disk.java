package core.world.shapes;

import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Fred on 17-05-2017.
 */
public class Disk implements IShape
{
    public double radius;
    Material material;
    double x;
    double y;
    double z;
    double A;
    double B;
    double C;
    double D;
    Vector3D planeNormal;

    public Disk(double x, double y, double z, double a, double b, double c, double d, double rad, Material material)
    {
        this.radius = rad;
        this.material = material;
        this.x = x;
        this.y = y;
        this.z = z;
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
        this.planeNormal = new Vector3D(A, B, C);
    }

    private Vector3D get_center()
    {
        // A + (B/2)
        // C + (D/2)

        // Assuming:
        //
        // A ------- B
        // |         |
        // |         |
        // |         |
        // C ------- D
        //
        //Vector3D center =
        return new Vector3D(x, y, z);
    }

    public RayInfo intersects(Ray ray, double min, double max)
    {
        RayInfo info = new RayInfo();

        Plane p = new Plane(A, B, C, D, material);
        info = p.intersects(ray, min, max);
        if (info.didIntersect)
        {
            //System.out.println("hit plane");
            if (info.point.distance(get_center()) > radius)
            {
                //System.out.println("dst " + info.point.distance(get_center()));
                info.didIntersect = false;
                info.material = new Material(new Vector3D(0, 0, 0));
                info.normal = new Vector3D(0, 0, 0);
                info.point = new Vector3D(0, 0, 0);
                info.t = 0.f;
                return info;
            }
            //System.out.println("dst " + info.point.distance(get_center()));
            return info;
        }

        return info;
    }

}
