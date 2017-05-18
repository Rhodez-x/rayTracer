package core.world.shading;

import core.world.ray.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 13-05-2017.
 */
public class ScatterInfo
{
    public Ray scattered;
    public Vector3D attenuation;
    public boolean didScatter = false;

    public Vector3D refracted;
    public boolean didRefract = false;


}