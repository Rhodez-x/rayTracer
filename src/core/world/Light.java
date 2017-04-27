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
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                //Vector3D startPos = new Vector3D(0, 200, 100);
                //Vector3D direction = startPos.subtract(new Vector3D((double) x , (double) y , -1));
      
                Vector3D startPos = new Vector3D(0, 2, 0);
                Vector3D direction = new Vector3D((double) x/WIDTH*VIEW_WIDTH-VIEW_WIDTH/2.0 , (double) y/HEIGHT*VIEW_HEIGHT-VIEW_HEIGHT/2.0  , 1);

                Ray myRay = new Ray(startPos, direction);
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
