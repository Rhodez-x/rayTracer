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
    public RayInfo intersects(Ray ray, double min, double max)
    {
        HashMap<Double, RayInfo> rayInfoDoubleHashMap = new HashMap<>();
        RayInfo rayInfo = new RayInfo();
        for (IShape shape : shapes)
        {
            if ((rayInfo = shape.intersects(ray, min, max)).didIntersect)
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


}
