package core.world;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Ray
{
    public Vector3D orig;
    public Vector3D dir;

    public Ray(Vector3D shotpos, Vector3D dir)
    {
        this.orig = shotpos;
        this.dir = dir;
    }
}
