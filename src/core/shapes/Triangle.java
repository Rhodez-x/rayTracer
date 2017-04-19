package core.shapes;

import core.math.CColor;
import core.math.Vector3;

public class Triangle
{
    private Vector3 pos;
    private float height;
    private float width;
    private CColor color;

    public Triangle()
    {
        pos = new Vector3();
        color = new CColor();
        height = 0;
        width = 0;
    }
}
