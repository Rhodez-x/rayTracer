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

    public int[] getRGBArray()
    {
        int[] rgb = new int[3];
        rgb[0] = (int) color.r;
        rgb[1] = (int) color.g;
        rgb[2] = (int) color.b;
        return rgb;
    }

}
