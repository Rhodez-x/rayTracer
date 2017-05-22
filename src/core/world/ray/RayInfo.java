package core.world.ray;

import core.world.shading.Material;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * The purpose of this class is to hold all the objects generated from a intersection, distance, direction, normal, and intersection point.
 * currently the intersection returns a boolean, this class extends that functionality to return a RayInfo object with multiple return values.
 * Created by Gabriel Jadderson on 30-04-2017.
 */
public class RayInfo
{

    public boolean didIntersect; //intersection occurred
    public Vector3D point; //hit position
    public Vector3D normal; //normal of the hit
    public double t; //distance
    public Material material; //mat of shape

    public RayInfo()
    {
        didIntersect = false;
        point = Vector3D.ZERO;
        normal = Vector3D.ZERO;
        t = 0;
        material = new Material(new Vector3D(0, 0, 0));
    }

}
