package core.world.light;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 20-05-2017.
 */
public class Light
{
    public Vector3D position;
    public double ambience;

    public Light()
    {
        this(Vector3D.ZERO, 0);
    }

    public Light(Vector3D position, double ambience)
    {
        this.position = position;
        this.ambience = ambience;
    }
}
