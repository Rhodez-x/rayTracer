package core.math;

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
