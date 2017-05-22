package core.world.shapes;

import core.Globals;
import core.world.math.VecMath;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * USES CLOCKWISE WINDING ORDER!
 */
public class Triangle implements IShape
{

    public Vector3D[] points = new Vector3D[3];
    public int[] vertices = new int[2];
    public int[] faces = new int[2];
    public Material material;

    public Triangle(Vector3D[] position, Material material)
    {
        this.points = position;
        this.material = material;

    }


    @Override
    public RayInfo intersects(Ray ray, double min, double max)
    {
        RayInfo rayInfo = new RayInfo();
        Vector3D u, v, n = new Vector3D(0, 0, 0);
        double r;

        u = this.points[1].subtract(this.points[0]);
        v = this.points[2].subtract(this.points[0]);
        n = u.crossProduct(v); // cross product
        //System.out.println(VecMath.length(n));


        double b = n.dotProduct(ray.dir);

        if ((double) Math.abs(b) < 0.000000001)
        {
            rayInfo.didIntersect = false;
            return rayInfo;
        }

        double d = n.dotProduct(this.points[0]);

        r = - (n.dotProduct(ray.orig) + d) / b;
        if (r < 0.0)
        {
            rayInfo.didIntersect = false;
            return rayInfo; // triangle is beheind 
        }
        
        Vector3D intersectPoint = new Vector3D((ray.orig.getX() + (ray.dir.getX() * r)), ray.orig.getY() + (ray.dir.getY() * r), - (ray.orig.getZ() + (ray.dir.getZ() * r)));

        Vector3D controlVector;

        Vector3D edge0 = this.points[1].subtract(this.points[0]);
        Vector3D vp0 = intersectPoint.subtract(this.points[0]);

        controlVector = edge0.crossProduct(vp0);

        if (n.dotProduct(controlVector) < 0)
        {
            rayInfo.didIntersect = false;
            return rayInfo; // ray is on right side
        }

        Vector3D edge1 = this.points[2].subtract(this.points[1]);
        Vector3D vp1 = intersectPoint.subtract(this.points[1]);

        controlVector = edge1.crossProduct(vp1);

        if (n.dotProduct(controlVector) < 0)
        {
            rayInfo.didIntersect = false;
            return rayInfo; // ray is on right side
        }

        Vector3D edge2 = this.points[0].subtract(this.points[2]);
        Vector3D vp2 = intersectPoint.subtract(this.points[2]);

        controlVector = edge2.crossProduct(vp2);

        if (n.dotProduct(controlVector) < 0)
        {
            rayInfo.didIntersect = false;
            return rayInfo; // ray is on right side
        }

        rayInfo.t = r;
        rayInfo.point = intersectPoint; //assign hit point
        rayInfo.normal = n.normalize(); //assign hit point normal from center
        rayInfo.didIntersect = true;
        rayInfo.material = material;
        
        System.out.println(r + " - " + intersectPoint.toString() + " - " + n.normalize());

        return rayInfo;
    }

    public void paint(int x, int y)
    {
        Globals.outputRenderedImage.getRaster().setPixel(x, y, material.getRGBArray());

    }


    public RayInfo hit(Ray ray, double tmin, double tmax)
    {
        return null;
    }

    public double calculateArea()
    {
        //A = 1/2*(||(v2 -v1) cross (v3 - v1)||)
        return 0.5 * normalize().getNorm();
    }

    public Vector3D normalize()
    {
        //N = (P_1 - P_0) X (P_2 - P_0) getting the plane in which the triangle lies within.
        return ((points[1].subtract(points[0])).crossProduct(points[2].subtract(points[0])));
    }
}