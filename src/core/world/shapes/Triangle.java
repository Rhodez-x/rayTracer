package core.world.shapes;

import com.sun.swing.internal.plaf.metal.resources.metal;
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
    public RayInfo intersects(Ray ray, double dist)
    {
        RayInfo rayInfo = new RayInfo();
        Vector3D feedback = new Vector3D(0,0,0);
        Vector3D    u, v, n = new Vector3D(0,0,0);
        Vector3D    dir, w0, w;
        double     r, a, b;
        
        u = this.points[1];
        u.subtract(this.points[0]);
        v = this.points[2];
        v.subtract(this.points[0]);
        n = n.crossProduct(u, v); // cross product
        
        if (VecMath.length(n) == 0) {
            rayInfo.didIntersect = false;
            return rayInfo;
        }

        ray.orig.subtract(this.points[0]);
        a = -(n.dotProduct(ray.orig));
        b = n.dotProduct(ray.dir);
        
        if ((double)Math.abs(b) < 0.000000001) {
            rayInfo.didIntersect = false;
            return rayInfo;
        }
        
        r = a / b;
        if (r < 0.0) {
            rayInfo.didIntersect = false;
            return rayInfo;
        }
        
        
        rayInfo.distance = r;
        rayInfo.phit = new Vector3D(ray.orig.getX() + ray.dir.getX() * r, ray.orig.getY() + ray.dir.getY() * r, ray.orig.getY() + ray.dir.getY() * r); //assign hit point
        rayInfo.nhit = rayInfo.phit.subtract(n).normalize(); //assign hit point normal from center
        rayInfo.didIntersect = true;
        
        
        return rayInfo;
    }

    @Override
    public void paint(int x, int y)
    {
        Globals.outputRenderedImage.getRaster().setPixel(x, y, material.getRGBArray());

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

    public Vector3D barycentricCoordinates()
    {
        //P = w_0*P_0 + w_1*P_1 + w_2*P_2 where w_0 + w_1 + w_2 = 1.
        return null; //for now
    }


}
