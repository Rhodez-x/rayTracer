package core.world.shapes;

import com.sun.org.apache.bcel.internal.generic.ISHL;
import core.Globals;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
/**
 * Created by Gabriel Jadderson on 12-05-2017.
 */
public class Plane implements IShape
{
    //http://stackoverflow.com/questions/23975555/how-to-do-ray-plane-intersection
    //https://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-plane-and-ray-disk-intersection

    Vector3D n;
    Vector3D position;
    Material mat;

    public Plane(Vector3D position, Material mat)
    {
        this.position = position;
        this.n = position.normalize();
        this.mat = mat;
    }

    @Override
    public RayInfo intersects(Ray ray, double dist)
    {
        RayInfo info = new RayInfo();

        info.nhit = new Vector3D(0, 0, 0);
        info.phit = new Vector3D(0, 0, 0);
        info.didIntersect = false;
        info.distance = 0;

        double denom = n.dotProduct(ray.dir);//dotProduct(n, l);
        System.out.println(denom);
        if (denom > 1e-6) {
            Vector3D p0l0 = position.subtract(ray.orig);//p0 - l0;//Vector3D(p0 - l0);
            dist = p0l0.dotProduct(n) / denom;//dotProduct(p0l0, n) / denom;
            info.phit = p0l0;
            info.didIntersect = (dist >= 0);
            info.nhit = n;
            info.distance = dist;
            System.out.println(denom);
            if(dist >= 0)
            {
                System.out.println("hit: " + denom);
            }
        }

        return info;
    }

    @Override
    public void paint(int x, int y)
    {
        Globals.outputRenderedImage.getRaster().setPixel(x, y, mat.getRGBArray());
    }
}
