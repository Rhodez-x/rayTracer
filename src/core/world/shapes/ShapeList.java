package core.world.shapes;

import core.world.ray.Ray;
import core.world.ray.RayInfo;

import java.util.ArrayList;

/**
 * Created by Gabriel Jadderson on 14-05-2017.
 */
public class ShapeList implements IShape
{

    ArrayList<RayInfo> rayInfos;
    ArrayList<IShape> shapes;

    public ShapeList(ArrayList<IShape> shapes)
    {
        this.shapes = shapes;
        rayInfos = new ArrayList<>();
    }


    @Override
    public RayInfo intersects(Ray ray)
    {
        return null;
    }

    @Override
    public RayInfo hit(Ray ray, double tMin, double tMax)
    {
        RayInfo rayInfo = new RayInfo();
        boolean hitAnything = false;
        double closestSoFar = tMax;
        for (int i = 0; i < shapes.size(); i++)
        {
            if ((rayInfo = shapes.get(i).hit(ray, tMin, closestSoFar)).didIntersect)
            {
                hitAnything = true;
                closestSoFar = rayInfo.t;
                rayInfos.add(rayInfo);
            }
        }

        return rayInfo;
    }
}
