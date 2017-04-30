package core.math;

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

        //I have no idea what i'm doing. using a curve function to generate the alpha of a lerp, stupid..
        Color color = new Color(r, g, b);
        Color color1 = new Color(r - depth * depth, g - depth * depth, b - depth * depth);
        double lerpR = lerp(color.r, color1.r, cubicCurve(depth));
        double lerpG = lerp(color.g, color1.g, cubicCurve(depth));
        double lerpB = lerp(color.b, color1.b, cubicCurve(depth));


        this.r = lerpR;
        this.g = lerpG;
        this.b = lerpB;


        //but it works, no more boring colors.
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

    public void add(Color a)
    {

        this.r += a.r;
        this.g += a.g;
        this.b += a.b;
    }

    public void scaleAndAdd(double scale, Color a)
    {

        this.r += scale * a.r;
        this.g += scale * a.g;
        this.b += scale * a.b;
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


    /* STATIC */
    public static Color multiply(Color a, double b)
    {
        return new Color(a.r * b, a.g * b, a.b * b);
    }

    public static Color divide(Color a, double b)
    {
        return new Color(a.r / b, a.g / b, a.b / b);
    }

    public static Color add(Color a, Color b)
    {
        return new Color((a.r + b.r) / 2, (a.g + b.g) / 2, (a.b + b.b) / 2);
    }

    public static Color sub(Color a, Color b)
    {
        return new Color(a.r - b.r, a.g - b.g, a.b - b.b);
    }


}
