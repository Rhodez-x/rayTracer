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


    public Color calcDepth(float depth)
    {
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
        return calColor;
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
