package core.shapes;

import core.Globals;
import core.math.Color;
import core.math.Material;
import core.world.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Sphere implements IShape
{

    public double radius;
    public double distance;

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
    public boolean intersects(Ray ray, double dist)
    {
        Vector3D origin = ray.orig;
        Vector3D direction = ray.dir;
        Vector3D OC = position.subtract(origin);
        double tca = OC.dotProduct(direction);
        //System.out.println(tca);
        if (tca < 0)
            return false;

        double d2 = OC.dotProduct(OC) - (tca * tca);
        if (d2 > radius * radius)
            return false;


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
            return false;


        if (t0 < 0)
        {
            t0 = t1;
            if (t0 < 0)
            {
                return false;
            }
        }
        this.distance = Math.min(t0, t1); // this is correct.
        System.out.println(this.distance);
        return true;
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

    public void paint(int x, int y)
    {
        material.color.setDepth(distance);
        Globals.outputRenderedImage.getRaster().setPixel(x, y, material.getRGBArray());
    }

    /**
     * gets the intersection point on the sphere.
     *
     * @return
     */
    public Vector3D getIntersectionPoint()
    {
        return null;
    }

    public double getDistance()
    {
        return this.distance;
    }

}
