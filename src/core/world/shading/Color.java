package core.world.shading;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * this class is fucked, needs to be fixed.
 */
public class Color
{
    public double r = 0;
    public double g = 0;
    public double b = 0;

    public Color()
    {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public Color(double r, double g, double b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public double getB()
    {
        return b;
    }

    public double getG()
    {
        return g;
    }

    public double getR()
    {
        return r;
    }

    public void setB(double b)
    {
        this.b = b;
    }

    public void setG(double g)
    {
        this.g = g;
    }

    public void setR(double r)
    {
        this.r = r;
    }


    public void setDepth(double depth)
    {
        /*   virker ikke?
        int newDepth = (int) depth;
        Color calColor = new Color(0, 0, 0);
        double x = this.r - newDepth;
        if (x >= 0)
        {
            calColor.r = x;
        }
        x = this.g - newDepth;
        if (x >= 0)
        {
            calColor.g = x;
        }
        x = this.b - newDepth;
        if (x >= 0)
        {
            calColor.b = x;
        }
        calColor.r = 0;
        calColor.g = 0;
        calColor.b = 0;
        */
    }

    public void shade(Vector3D light_vector, Vector3D normal_vector, double ambient_coefficient, double diffuse_coefficient)
    {
        double shade = light_vector.dotProduct(normal_vector);
        if (shade < 0)
            shade = 0;
        multiply(ambient_coefficient + diffuse_coefficient * shade);
    }


    double cubicCurve(double a)
    {
        return a * a * (3.0f - 2.0f * a);
    }


    double lerp(double p1, double p2, double a)
    {
        return p1 + a * (p2 - p1);
    }

    public void interpolate(Color a, Color b, double t)
    {
        this.r = a.r * (1 - t) + b.r * t;
        this.g = a.g * (1 - t) + b.g * t;
        this.b = a.b * (1 - t) + b.b * t;
    }


    public void clamp(double min, double max)
    {

        r = Math.max(Math.min(r, max), min);
        g = Math.max(Math.min(g, max), min);
        b = Math.max(Math.min(b, max), min);
    }

    public void calculateGammaInterpolation(double gamma)
    {
        double inverseGamma = 1.0 / gamma;
        this.r = Math.pow(r, inverseGamma);
        this.g = Math.pow(g, inverseGamma);
        this.b = Math.pow(b, inverseGamma);
    }

    public void clamp()
    {
        r = r / 256;
        g = g / 256;
        b = b / 256;
    }

    public void unclamp()
    {
        r = (double) ((int) (r * 255));
        g = (double) ((int) (g * 255));
        b = (double) ((int) (b * 255));
    }


    public void multiply(double t)
    {
        r *= t;
        g *= t;
        b *= t;
    }

    public void divide(double t)
    {
        r /= t;
        g /= t;
        b /= t;
    }

    public void add(double t)
    {
        r += t;
        g += t;
        b += t;
    }

    public void add(Color a)
    {

        this.r += a.r;
        this.g += a.g;
        this.b += a.b;
    }

    public void sub(double t)
    {
        r -= t;
        g -= t;
        b -= t;
    }

    public void sub(Color a)
    {

        this.r -= a.r;
        this.g -= a.g;
        this.b -= a.b;
    }


    public void scale(Color a)
    {

        this.r *= a.r;
        this.g *= a.g;
        this.b *= a.b;
    }

    public void scale(double a)
    {

        this.r *= a;
        this.g *= a;
        this.b *= a;
    }

    public void scaleAndAdd(double scale, Color a)
    {

        this.r += scale * a.r;
        this.g += scale * a.g;
        this.b += scale * a.b;
    }


}
