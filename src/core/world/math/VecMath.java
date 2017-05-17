package core.world.math;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 13-05-2017.
 */
public class VecMath
{
    public static double length(Vector3D v)
    {
        return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ());
    }

    public static Vector3D random_in_unit_sphere()
    {
        Vector3D p;
        do
        {
            Vector3D randVec = new Vector3D(Math.random(), Math.random(), Math.random());
            Vector3D oneVec = new Vector3D(1, 1, 1);
            p = randVec.scalarMultiply(2.0).subtract(oneVec);
        } while (squared_length(p) >= 1.0);
        return p;
    }

    public static double squared_length(Vector3D e)
    {
        return e.getX() * e.getX() + e.getY() * e.getY() + e.getZ() * e.getZ();
    }


    //returns the unit vector.
    public static Vector3D unit_vector(Vector3D v)
    {
        double length = Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ());
        return divide(v, length);
    }


    //Scalar math
    public static Vector3D divide(Vector3D v, double a)
    {
        return new Vector3D(v.getX() / a, v.getY() / a, v.getZ() / a);
    }

    public static Vector3D add(Vector3D v, double a)
    {
        return new Vector3D(v.getX() + a, v.getY() + a, v.getZ() + a);
    }

    public static Vector3D minus(Vector3D v, double a)
    {
        return new Vector3D(v.getX() - a, v.getY() - a, v.getZ() - a);
    }


    //Two Vector math
    public static Vector3D add(Vector3D v, Vector3D v1)
    {
        return new Vector3D(v.getX() + v1.getX(), v.getY() + v1.getY(), v.getZ() + v1.getZ());
    }

    public static Vector3D minus(Vector3D v, Vector3D v1)
    {
        return new Vector3D(v.getX() - v1.getX(), v.getY() - v1.getY(), v.getZ() - v1.getZ());
    }

    public static Vector3D divide(Vector3D v, Vector3D v1)
    {
        return new Vector3D(v.getX() / v1.getX(), v.getY() / v1.getY(), v.getZ() / v1.getZ());
    }

    public static Vector3D multiply(Vector3D v, Vector3D v1)
    {
        return new Vector3D(v.getX() * v1.getX(), v.getY() * v1.getY(), v.getZ() * v1.getZ());
    }


    //assign math.
    public static Vector3D plusEqual(Vector3D v, Vector3D v1)
    {
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        x += v1.getX();
        y += v1.getY();
        z += v1.getZ();
        return new Vector3D(x, y, z);
    }

    public static Vector3D divideEqual(Vector3D v, Vector3D v1)
    {
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        x /= v1.getX();
        y /= v1.getY();
        z /= v1.getZ();
        return new Vector3D(x, y, z);
    }

    public static Vector3D multiplyEqual(Vector3D v, Vector3D v1)
    {
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        x *= v1.getX();
        y *= v1.getY();
        z *= v1.getZ();
        return new Vector3D(x, y, z);
    }

    public static Vector3D minusEqual(Vector3D v, Vector3D v1)
    {
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        x -= v1.getX();
        y -= v1.getY();
        z -= v1.getZ();
        return new Vector3D(x, y, z);
    }

    public static Vector3D divideEqual(Vector3D v, double l)
    {
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        x /= l;
        y /= l;
        z /= l;
        return new Vector3D(x, y, z);
    }

}
