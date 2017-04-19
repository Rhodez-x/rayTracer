package core.math;

import core.Main;

public class Material
{
    public final CColor color;

    public Material(CColor color)
    {
        this.color = color;
    }

    public CColor getColor()
    {
        return this.color;
    }

    public void paint(int x, int y, CColor color)
    {
        int[] rbg = new int[3];
        rbg[0] = color.r;
        rbg[1] = color.g;
        rbg[2] = color.b;
        Main.bufferedImage.getRaster().setPixel(x, y, rbg);
    }
}
