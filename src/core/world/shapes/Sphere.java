package core.world.shapes;

import core.world.math.VecMath;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Sphere implements IShape
{

    public double radius;
    public Vector3D position;
    public Material material;

    public Sphere(Vector3D position, double radius, Material material)
    {
        this.position = position;
        this.material = material;
        this.radius = radius;
    }

    @Override
    public RayInfo intersects(Ray ray, double min, double max)
    {
        /*
        RayInfo rayInfo = new RayInfo();

        Vector3D origin = ray.orig;
        Vector3D direction = ray.dir;
        Vector3D OC = position.subtract(origin); //L
        double tca = OC.dotProduct(direction);

        if (tca < 0) //because cannot be negative.
            return rayInfo;

        double d2 = OC.dotProduct(OC) - (tca * tca);
        if (d2 > radius * radius)
            return rayInfo;


        double thc = Math.sqrt(radius * radius - d2);
        double t0 = tca - thc;
        double t1 = tca + thc;
        if (t0 > t1)
        {
            double temp = t0;
            t0 = t1;
            t1 = temp;
        }

        Vector3D OC2 = origin.subtract(position);
        double a = direction.dotProduct(direction);
        double b = 2 * OC2.dotProduct(direction);
        double c = OC2.dotProduct(OC2) - (radius * radius);
        if (!solveQuadratic(a, b, c, t0, t1))
            return rayInfo;


        if (t0 < 0)
        {
            t0 = t1;
            if (t0 < 0)
                return rayInfo;
        }

        if (!(t0 < max && t0 > min))
            return rayInfo;

        rayInfo.didIntersect = true;

        //Assignment. reset variables
        rayInfo.t = Math.min(t0, t1); //assign distance.
        rayInfo.point = getIntersectionPoint(ray.orig, ray.dir, rayInfo.t); //assign hit point
        rayInfo.normal = getIntersectionPointNormal(rayInfo.point); //assign hit point normal from center
        //material.shade(rayInfo.normal, ray.orig.normalize());
        rayInfo.material = material;

        return rayInfo;
        */

        return hit(ray, min, max);
    }

    public boolean solveQuadratic(double a, double b, double c, double x0, double x1)
    {
        //double distanceCenter = (b*b) - (4 * c);
        double discr = b * b - 4 * a * c;
        if (discr < 0)
            return false;
        else if (discr == 0)
            x0 = x1 = -0.5 * b / a;
        else
        {
            double q = 0;
            if (b > 0)
                q = (-0.5 * (b + Math.sqrt(discr)));
            else
                q = (-0.5 * (b - Math.sqrt(discr)));

            x0 = q / a;
            x1 = c / q;
        }
        if (x0 > x1)
        {
            double temp = x0;
            x0 = x1;
            x1 = temp;
        }
        return true;
    }

    public RayInfo hit(Ray ray, double tMin, double tMax) //intersection with min distance and max distance. useful for sorting objects by depth.
    {
        RayInfo rayInfo = new RayInfo();
        Vector3D origin = ray.orig;
        Vector3D direction = ray.dir;

        Vector3D OC = origin.subtract(position);
        double a = direction.dotProduct(direction);
        double b = OC.dotProduct(direction);
        double c = OC.dotProduct(OC) - (radius * radius);
        double descriminant = (b * b) - (a * c);

        if (descriminant > 0)
        {
            double temp = (-b - Math.sqrt(b * b - a * c)) / a;
            if (temp < tMax && temp > tMin)
            {
                rayInfo.t = temp;
                rayInfo.point = ray.getPointaAt(rayInfo.t);
                rayInfo.normal = VecMath.divide(rayInfo.point.subtract(position), radius);
                rayInfo.didIntersect = true;
            }
            temp = (-b + Math.sqrt(b * b - a * c)) / a;
            if (temp < tMax && temp > tMin)
            {
                rayInfo.t = temp;
                rayInfo.point = ray.getPointaAt(rayInfo.t);
                rayInfo.normal = VecMath.divide(rayInfo.point.subtract(position), radius);
                rayInfo.didIntersect = true;
            }
        } else
        {
            rayInfo.didIntersect = false;
        }

        rayInfo.material = material;

        return rayInfo;
    }


}
