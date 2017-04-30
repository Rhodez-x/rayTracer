package core.math;

public class Material
{
    public final Color color;

    public Material(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }

    public double[] getRGBArray()
    {
        double[] rgb = new double[3];
        rgb[0] = color.r;
        rgb[1] = color.g;
        rgb[2] = color.b;
        return rgb;
    }

}
