package core.shapes;

import core.math.Color;
import core.math.Material;
import core.math.Vector3;
import core.world.Ray;

public class Sphere implements Shape
{
    private Vector3 pos;
    private double radius;
    public Material mat;
    public double distance;

    public Sphere(double x, double y, double z, double r, Color color)
    {
        this.pos = new Vector3(x, y, z);
        this.mat = new Material(color);
        this.radius = r;
        this.distance = 0;
    }

    public Sphere()
    {
        pos = new Vector3();
        mat = new Material(new Color());
        radius = 0;
    }

    @Override
    public boolean intersects(Ray ray, double dist)
    {
        Vector3 origin = ray.orig;
        Vector3 direction = ray.dir;
        Vector3 OC = Vector3.sub(pos, origin);
        double tca = Vector3.dot(OC, direction);
        //System.out.println(tca);
        if (tca < 0)
        {
            return false;
        }
        double d2 = Vector3.dot(OC, OC) - (tca * tca);
        if (d2 > radius * radius)
        {
            return false;
        }

        double thc = Math.sqrt(radius * radius - d2);
        double t0 = tca - thc;
        double t1 = tca + thc;
        if (t0 > t1)
        {
            double temp = t0;
            t0 = t1;
            t1 = temp;
        }

        Vector3 OC2 = Vector3.sub(origin, pos);
        double a = Vector3.dot(direction, direction);
        double b = 2 * Vector3.dot(OC2, direction);
        double c = Vector3.dot(OC2, OC2) - (radius * radius);
        if (!solveQuadratic(a, b, c, t0, t1))
        {
            return false;
        }

        if (t0 < 0)
        {
            t0 = t1;
            if (t0 < 0)
            {
                return false;
            }
        }
        this.distance = t0;
        System.out.println(t1);
        //System.out.println(t0); burde være distance??
        return true;
    }

    public boolean solveQuadratic(double a, double b, double c, double x0, double x1)
    {
        //double distanceCenter = (b*b) - (4 * c);
        double discr = b * b - 4 * a * c;
        if (discr < 0)
        {
            return false;
        } else if (discr == 0)
        {
            x0 = x1 = (double) (-0.5 * b / a);
        } else
        {
            double q = 0;
            if (b > 0)
            {
                q = (-0.5 * (b + Math.sqrt(discr)));
            } else
            {
                q = (-0.5 * (b - Math.sqrt(discr)));
            }
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

    @Override
    public double getDepth()
    {
        return this.distance;
    }

    @Override
    public Material getMaterial()
    {
        return this.mat;
    }

}
