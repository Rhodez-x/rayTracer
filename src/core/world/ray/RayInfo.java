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
    public boolean didIntersect = false; //default false;
    public Vector3D point; //hit position
    public Vector3D normal; //normal of the hit
    public double t;
    public Material material;

}
