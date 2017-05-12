package core.world.ray;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * The purpose of this class is to hold all the objects generated from a intersection, distance, direction, normal, and intersection point.
 * currently the intersection returns a boolean, this class extends that functionality to return a RayInfo object with multiple return values.
 * Created by Gabriel Jadderson on 30-04-2017.
 */
public class RayInfo
{
    public boolean didIntersect = false; //default false;
    public Vector3D phit; //hit position
    public Vector3D nhit; //normal of the hit
    public double distance;

}
