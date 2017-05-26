package core.world.light;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 20-05-2017.
 */
public class Light
{
    public Vector3D shadowRayDirection;
    public double ambience;

    public Light()
    {
        this(Vector3D.ZERO, 0);
    }

    public Light(Vector3D shadowRayDirection, double ambience)
    {
        this.shadowRayDirection = shadowRayDirection;
        this.ambience = ambience;
    }
}
