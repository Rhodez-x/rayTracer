package core.shapes;

import core.math.Material;
import core.world.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * USES CLOCKWISE WINDING ORDER!
 */
public class Triangle implements IShape
{

    public Vector3D[] points = new Vector3D[3];
    public int[] vertices = new int[2];
    public int[] faces = new int[2];

    public Triangle(Vector3D position, Material material)
    {


    }


    @Override
    public boolean intersects(Ray ray, double dist)
    {
        return false;
    }

    @Override
    public void paint(int x, int y)
    {

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
