package core.world.shapes;

import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shapes.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Fred on 17-05-2017.
 */
public class Disk implements IShape
{

    public double radius;
    public Vector3D position;
    Plane plane;

    @Override
    public RayInfo intersects(Ray ray, double dist)
    {
        float t = 0;
        RayInfo info = new RayInfo();
        info.didIntersect = false;
        Vector3D l0 = ray.orig;
        Vector3D l = ray.dir;
        Vector3D p0 = position;
        Vector3D n = new Vector3D(0, 0, 0); // TODO: Implement n as the plane normal
       /* if (plane.intersectPlane(n, position, l0, l, t)) {
            Vector3D p = l0.add(10, l.scalarMultiply(t)); //l0 + l.scalarMultiply(t);//l * t;
            Vector3D v = p.subtract(p0);//p - p0;
            float d2 = (float)v.dotProduct(v);//dot(v, v);
            info.didIntersect = (Math.sqrt(d2) <= radius);
        }*/

        return info;
    }

    @Override
    public void paint(int x, int y) {

    }
}
