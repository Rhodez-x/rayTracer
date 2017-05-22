package core.world.shapes;

import core.world.math.VecMath;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 17-05-2017.
 */
public class TTriangle implements IShape
{

    Vector3D p0;
    Vector3D p1;
    Vector3D p2;
    Material material;

    public TTriangle(Vector3D p0, Vector3D p1, Vector3D p2, Material material)
    {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.material = material;
    }


    //Möller-Trumbore algorithm ALSO https://www.scratchapixel.com/lessons/3d-basic-rendering/ray-tracing-rendering-a-triangle/moller-trumbore-ray-triangle-intersection

    @Override //based on https://en.wikipedia.org/wiki/M%C3%B6ller%E2%80%93Trumbore_intersection_algorithm
    public RayInfo intersects(Ray ray, double min, double max)
    {
        RayInfo rayInfo = new RayInfo();
        Vector3D origin = ray.orig;
        Vector3D direction = ray.dir.normalize(); //check normalization for debug.

        double EPSILON = 0.000001;

        Vector3D edge1, edge2;
        Vector3D P, Q, T;
        Vector3D pvec, tvec, qvec;

        double det, inv_det, u, v;
        double t;

        //Find vectors for two edges sharing V1
        edge1 = p1.subtract(p0);
        edge2 = p2.subtract(p0);

        pvec = direction.crossProduct(edge2);
        det = edge1.dotProduct(pvec);
        /*
        if(det < EPSILON)return;
	    sub(tvec, orig, vert0);
	    u = dot(tvec, pvec);
	    if(u < 0 || u > det)return;
	    cross(qvec,tvec,edge1);
	    v = dot(dir, qvec);
	    if(v < 0 || u + v > det)return;
	    t = dot(edge2, qvec);
	    inv_det = 1f / det;
	    t *= inv_det;
	    u *= inv_det;
	    v *= inv_det;
	    */

        if (det > -EPSILON && det < EPSILON)
        {
            rayInfo.didIntersect = false;
            return rayInfo;
        }

        inv_det = 1.0 / det;
        tvec = origin.subtract(p0);
        u = direction.dotProduct(pvec) * inv_det;

        if (u < 0 || u > 1)
        {
            rayInfo.didIntersect = false;
            return rayInfo;
        }

        qvec = tvec.crossProduct(edge1);

        v = direction.dotProduct(qvec) * inv_det;

        if (v < 0 || u + v > 1)
        {
            rayInfo.didIntersect = false;
            return rayInfo;
        }

        t = edge2.dotProduct(qvec) * inv_det;
        rayInfo.t = t;
        rayInfo.normal = getNormal().normalize();
        rayInfo.didIntersect = true;
        rayInfo.material = material;

        return rayInfo;
    }

    private Vector3D getNormal()
    {
        Vector3D N;

        Vector3D c1 = p1.subtract(p0);
        Vector3D c2 = p2.subtract(p0);
        Vector3D e = c1.crossProduct(c2);

        double l = VecMath.length(e);

        N = VecMath.divide(e, l);
        return N;
    }
}
