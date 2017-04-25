package core.math;

public class Vector3
{

    public double x, y, z;

    public Vector3()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 add(Vector3 a, Vector3 b)
    {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3 sub(Vector3 a, Vector3 b)
    {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static double dot(Vector3 a, Vector3 b)
    {
        return (a.x * b.x) + (a.y * b.y) + (a.z * b.z);
    }

    public static Vector3 multiplydouble(Vector3 a, double b)
    {
        return new Vector3(a.x * b, a.y * b, a.z * b);
    }

    public static Vector3 divide(Vector3 vec, double b)
    {
        return new Vector3(vec.x / b, vec.y / b, vec.z / b);
    }

    public double magnitude()
    {
        return (double) Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public Vector3 normalize()
    {
        double mg = magnitude();
        return new Vector3(x / mg, y / mg, z / mg);
    }
}
