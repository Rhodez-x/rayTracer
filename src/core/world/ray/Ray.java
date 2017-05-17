package core.world.ray;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Ray
{
    public Vector3D orig;
    public Vector3D dir;

    RayInfo rayInfo; //TODO: implement this.

    public Ray(Vector3D shotpos, Vector3D dir)
    {
        this.orig = shotpos;
        this.dir = dir;
    }

    public Vector3D getPointaAt(double t)
    {
        return orig.add(dir.scalarMultiply(t));
    }

}
