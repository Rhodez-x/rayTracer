package core.world.shapes;

import core.Globals;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Color;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Sphere implements IShape
{

    public double radius;
    public Vector3D position;

    public Material material;

    public Sphere(double x, double y, double z, double r, Color color)
    {
        this(new Vector3D(x, y, z), new Material(color));
        this.radius = r;
    }

    public Sphere(Vector3D position, Material material)
    {
        this.position = position;
        this.material = material;
        radius = 0;
    }

    @Override
    public RayInfo intersects(Ray ray, double dist)
    {
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
            {
                return rayInfo;
            }
        }
        rayInfo.didIntersect = true;

        //Assignment. reset variables
        rayInfo.distance = Math.min(t0, t1); //assign distance.
        rayInfo.phit = getIntersectionPoint(ray.orig, ray.dir, rayInfo.distance); //assign hit point
        rayInfo.nhit = getIntersectionPointNormal(rayInfo.phit); //assign hit point normal from center
        //material.color.shade(rayInfo.nhit, ray.orig.normalize());

        return rayInfo;
    }

    public Vector3D getIntersectionPoint(Vector3D rayOrgin, Vector3D rayDirection, double t)
    {
        return rayOrgin.add(rayDirection.scalarMultiply(t));
    }

    public Vector3D getIntersectionPointNormal(Vector3D hitPoint)
    {
        return hitPoint.subtract(position).normalize();
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

    //TODO: UNDERSTAND THEN REFACTOR
    public void paint(int x, int y)
    {
        material.color.shade(new Vector3D(-5, -1, 1.5), new Vector3D(-1, 6, 1.00), 0.126, 1.7473);
        Globals.outputRenderedImage.getRaster().setPixel(x, y, material.getRGBArray());
    }

}
