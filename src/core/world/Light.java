package core.world;

import core.shapes.Shape;

import static core.Globals.*;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Fred on 25-04-2017.
 */
public class Light
{
    private Vector3D position;
    public double fov;

    public void Begin()
    {
        for (int y = 0; y < 360; y++)
        {
            for (int x = 0; x < 360; x++)
            {
                if (x > 180) {
                    x -= 360;
                }
                if (y > 180) {
                    y -= 360;
                }
                Vector3D startPos = new Vector3D(0, 200, 100);
                Vector3D direction = startPos.subtract(new Vector3D((double) x , (double) y , -1));
                
                Ray myRay = new Ray(startPos, direction);
                //Ray myRay = new Ray(new Vector3D((double) x, (double) y, -30.0f), new Vector3D(50, 50, 50));
                background.setPaint(x, y, background.color);
                for (Shape shape : shapeList)
                {
                    if (shape.intersects(myRay, 1))
                    {
                        //System.out.println(shape.getDepth());
                        shape.getMaterial().setPaint(x, y, shape.getMaterial().getColor().calcDepth(shape.getDepth()));
                    }
                }
            }
        }
    }
}
