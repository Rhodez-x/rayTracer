package core.world;

import core.Globals;
import core.math.Vector3;
import core.shapes.Shape;
import core.world.Ray;

import static core.Globals.*;

/**
 * Created by Fred on 25-04-2017.
 */
public class Light
{
    private Vector3 position;
    public float fov;

    public void Begin()
    {
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                Ray myRay = new Ray(new Vector3((float) x, (float) y, -30.0f), new Vector3(50, 50, 50));
                background.setPaint(x, y, background.color);
                for (Shape shape : shapeList)
                {
                    if (shape.intersects(myRay, 1))
                    {
                        System.out.println(shape.getDepth());
                        shape.getMaterial().setPaint(x, y, shape.getMaterial().getColor().calcDepth(shape.getDepth()));
                    }
                }
            }
        }
    }
}
