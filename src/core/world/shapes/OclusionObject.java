package core.world.shapes;

import core.world.ray.Ray;
import core.world.ray.RayInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Gabriel Jadderson on 18-05-2017.
 */
public class OclusionObject implements IShape
{

    ArrayList<IShape> shapes;

    public OclusionObject(ArrayList<IShape> shapes)
    {
        this.shapes = shapes;
    }


    @Override
    public RayInfo intersects(Ray ray, double dist)
    {

        HashMap<Double, RayInfo> rayInfoDoubleHashMap = new HashMap<>();
        RayInfo rayInfo = new RayInfo();
        for (IShape shape : shapes)
        {
            if ((rayInfo = shape.intersects(ray, dist)).didIntersect)
            {
                rayInfoDoubleHashMap.put(rayInfo.t, rayInfo);
            }
        }

        ArrayList<Double> mins = new ArrayList<>();
        rayInfoDoubleHashMap.forEach((k, v) -> mins.add(k));

        Collections.sort(mins);

        if (rayInfoDoubleHashMap.size() > 0)
        {
            return rayInfoDoubleHashMap.get(mins.get(0));
        } else
        {
            return rayInfo;
        }
    }

    @Override
    public void paint(int x, int y)
    {

    }

    @Override
    public RayInfo hit(Ray ray, double tmin, double tmax)
    {
        RayInfo rayInfo = new RayInfo();
        double min = Double.MAX_VALUE;
        RayInfo shapeInfo = new RayInfo();

        for (IShape shape : shapes)
        {
            if ((shapeInfo = shape.hit(ray, tmin, tmax)).didIntersect)
            {
                if (shapeInfo.t < min)
                {
                    min = shapeInfo.t;
                    rayInfo.point = shapeInfo.point;
                    rayInfo.normal = shapeInfo.normal;
                    rayInfo.material = shapeInfo.material;
                    rayInfo.didIntersect = true;
                }
            }
        }
        return hit(ray, tmin, min);
    }

}
