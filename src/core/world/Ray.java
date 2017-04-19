package core.world;

import core.math.Vector3;

public class Ray
{
    public Vector3 orig;
    public Vector3 dir;

    public Ray()
    {
        this.orig = new Vector3(0, 0, 0);
        this.dir = new Vector3(0, 0, 0);
    }

    public Ray(Vector3 shotpos, Vector3 dir)
    {
        this.orig = shotpos;
        this.dir = dir;
    }
}
