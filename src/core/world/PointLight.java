package core.world;

import core.math.Color;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 30-04-2017.
 */
public class PointLight
{

    public Vector3D position;

    public Color intensity;

    public PointLight()
    {
        this.position = new Vector3D(0, 0, 0);
        this.intensity = new Color(1, 1, 1);
    }

    public void setPosition(Vector3D position)
    {
        this.position = position;
    }


    public void setIntensity(Color intensity)
    {
        this.intensity = intensity;
    }

}
