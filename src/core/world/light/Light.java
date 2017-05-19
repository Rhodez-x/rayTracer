package core.world.light;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 19-05-2017.
 */
public class Light
{
    public Vector3D position;
    public double ambience;
    public Vector3D specular;
    public Vector3D diffuse;

    public Light()
    {
        this(Vector3D.ZERO, 0, Vector3D.ZERO, Vector3D.ZERO);
    }


    public Light(Vector3D position, double ambience, Vector3D specular, Vector3D diffuse)
    {
        this.position = position;
        this.ambience = ambience;
        this.specular = specular;
        this.diffuse = diffuse;
    }
}
