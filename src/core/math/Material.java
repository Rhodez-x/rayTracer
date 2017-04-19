package core.math;

import core.Globals;

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

    public void paint(int x, int y, Color color)
    {
        int[] rbg = new int[3];
        rbg[0] = (int) color.r;
        rbg[1] = (int) color.g;
        rbg[2] = (int) color.b;
        Globals.outputRenderedImage.getRaster().setPixel(x, y, rbg);
    }
}
