package core.world;

import core.math.Color;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 30-04-2017.
 */
public class ExpensiveLightSource
{
    Vector3D position;

    Color ambient;
    Color diffuse;
    Color specular;

    double constantAttenuation;
    double linearAttenuation;
    double quadraticAttenuation;

    public ExpensiveLightSource()
    {
        setAllColors(new Color(255, 255, 255));
        ambient = new Color(0, 0, 0);

        constantAttenuation = 1.0f;
        linearAttenuation = 0.0f;
        quadraticAttenuation = 0.0f;
    }

    public ExpensiveLightSource(Vector3D position, Color ambient, Color diffuse, Color specular, double constantAttenuation, double linearAttenuation, double quadraticAttenuation)
    {
        this.position = position;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.constantAttenuation = constantAttenuation;
        this.linearAttenuation = linearAttenuation;
        this.quadraticAttenuation = quadraticAttenuation;
    }

    public void setAllColors(Color color)
    {
        this.ambient = color;
        this.diffuse = color;
        this.specular = color;
    }


}
