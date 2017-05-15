package core.world.light;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 30-04-2017.
 */
public class ExpensiveLightSource
{
    Vector3D position;
    double color = 1.0f;
    double intensity;

    Vector3D ambient;
    Vector3D diffuse;
    Vector3D specular;

    double constantAttenuation;
    double linearAttenuation;
    double quadraticAttenuation;

    public ExpensiveLightSource()
    {
        setAllColors(new Vector3D(255, 255, 255));
        ambient = new Vector3D(0, 0, 0);

        constantAttenuation = 1.0f;
        linearAttenuation = 0.0f;
        quadraticAttenuation = 0.0f;
    }

    public ExpensiveLightSource(Vector3D position, Vector3D ambient, Vector3D diffuse, Vector3D specular, double constantAttenuation, double linearAttenuation, double quadraticAttenuation)
    {
        this.position = position;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.constantAttenuation = constantAttenuation;
        this.linearAttenuation = linearAttenuation;
        this.quadraticAttenuation = quadraticAttenuation;
    }

    public void setAllColors(Vector3D color)
    {
        this.ambient = color;
        this.diffuse = color;
        this.specular = color;
    }

    public void calculateLight()
    {
        /*
        Vector3D diffuse = new Vector3D(0, 0, 0);
        Vector3D specular = new Vector3D(0, 0, 0);

        ArrayList<PointLight> lights = new ArrayList<>();

        for (int i = 0; i < lights.size(); i++)
        {
            Vector3D lightDir, lightIntensity;

            IsectInfo isectShad;

            lights[i]->illuminate(hitPoint, lightDir, lightIntensity, isectShad.tNear);

            bool vis = !trace(hitPoint + hitNormal * options.bias, -lightDir, objects, isectShad, kShadowRay);

            // compute the diffuse component
            diffuse += vis * isect.hitObject->albedo * lightIntensity * std::max (0.f, hitNormal.dotProduct(-lightDir));

            // compute the specular component
            // what would be the ideal reflection direction for this light ray
            Vec3f R = reflect(lightDir, hitNormal);
            specular += vis * lightIntensity * std::pow (std::max (0.f, R.dotProduct(-dir)),isect.hitObject->n);
        }
        hitColor = diffuse * isect.hitObject->Kd + specular * isect.hitObject->Ks;
        break;
        */

        double albedo = 0.18;
        double Kd = 0.8; // phong model diffuse weight
        double Ks = 0.2; // phong model specular weight
        double n = 10;   // phong specular exponent

    }

    void illuminate(Vector3D shadedPoint)
    {
        Vector3D lightDir = shadedPoint.subtract(position);
        double r2 = lightDir.getNorm();
        double distance = Math.sqrt(r2);
        double x = lightDir.getX() / distance;
        double y = lightDir.getY() / distance;
        double z = lightDir.getZ() / distance;
        // avoid division by 0

        double lightIntensity = color * intensity / (4 * Math.PI * r2);
    }

    /**
     * should return the reflection direction, but not sure haven't tested
     *
     * @param I
     * @param N
     * @return
     */
    Vector3D reflect(Vector3D I, Vector3D N)
    {
        double IN = I.dotProduct(N);
        double dIN = 2 * IN;
        Vector3D c1 = N.scalarMultiply(dIN);
        Vector3D c2 = I.subtract(c1);
        return c2;
    }


}
