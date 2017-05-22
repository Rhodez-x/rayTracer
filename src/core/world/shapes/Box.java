package core.world.shapes;

import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 17-05-2017.
 * BASED ON RAY/BOX INTERSECTION - INTRODUCTION TO RAYTRACING page 65.
 */
public class Box implements IShape
{

    Vector3D l;
    Vector3D h;
    Material material;

    /**
     * @param l box's minimum extent
     * @param h box's maximum extent
     */
    public Box(Vector3D l, Vector3D h, Material material)
    {
        this.l = l;
        this.h = h;
        this.material = material;
    }

    //doesn't work correctly missing PP planes and for loop
    @Override
    public RayInfo intersects(Ray ray, double min, double max)
    {
        RayInfo rayInfo = new RayInfo();

        Vector3D origin = ray.orig;
        Vector3D direction = ray.dir.normalize();


        double tnear = -Double.MAX_VALUE; //negative infinity
        double tfar = Double.MAX_VALUE;

        if (direction.getX() == 0 && direction.getY() == 0 && direction.getZ() == 0) //ray is parallel to the planes.
        {
            if (origin.getX() < l.getX() || origin.getX() > h.getX())
            {
                rayInfo.didIntersect = false;
                return rayInfo;
            }
        } else
        {
            double t1 = (l.getX() - origin.getX()) / direction.getX();
            double t2 = (h.getX() - origin.getX()) / direction.getX();

            if (t1 > t2)
                //swap t1 and t2

                if (t1 > tnear)
                    tnear = t1;

            if (t2 < tfar)
                tfar = t2;

            if (tnear > tfar) //box missed, return false
            {
                rayInfo.didIntersect = false;
                return rayInfo;
            }

            if (tfar < 0) //box is behind ray, return false
            {
                rayInfo.didIntersect = false;
                return rayInfo;
            }
        }

        rayInfo.didIntersect = true;
        rayInfo.material = material;

        return rayInfo;
    }

}
