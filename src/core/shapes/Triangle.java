package core.shapes;

import core.math.Color;
import core.math.Vector3;

/**
 * USES CLOCKWISE WINDING ORDER!
 */
public class Triangle
{
    private Vector3 pos;
    private float height;
    private float width;
    private Color color;

    public Triangle()
    {
        pos = new Vector3();
        color = new Color();
        height = 0;
        width = 0;
    }
}
