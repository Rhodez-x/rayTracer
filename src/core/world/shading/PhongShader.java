package core.world.shading;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Created by Gabriel Jadderson on 30-04-2017.
 */
public class PhongShader
{

    public double ior = 1.3;
    public double Kd = 0.8;
    public double Ks = 0.2;
    public double diffuseColor = 0.2;
    public double specularExponent = 25;

    public Vector3D reflect(Vector3D I, Vector3D N)
    {
        return I.subtract(N.scalarMultiply(2 * I.dotProduct(N)));
    }

      public Vector3D refract(Vector3D I, Vector3D N, double ior)
    {
        double cosi = clamp(-1, 1, I.dotProduct(N));
        double etai = 1;
        double etat = ior;

        Vector3D n = N;
        if (cosi < 0)
        {
            cosi = -cosi;
        } else
        {
            double temp = etat;
            etat = etai;
            etai = temp;
            n.negate();
        }
        double eta = etai / etat;
        double k = 1 - eta * eta * (1 - cosi * cosi);

        if (k < 0)
        {
            return new Vector3D(0, 0, 0);
        } else
        {
            double dx = eta * cosi - Math.sqrt(k);
            I.scalarMultiply(eta);
            n.scalarMultiply(dx);
            return new Vector3D(I.getX() * n.getX(), I.getY() * n.getY(), I.getZ() * n.getZ());
        }
    }

    public double clamp(double low, double high, double v)
    {
        return Math.max(low, Math.min(high, v));
    }

    public Vector3D mix(Vector3D a, Vector3D b, double mixValue)
    {
        a.scalarMultiply(1 - mixValue);
        b.scalarMultiply(mixValue);
        return new Vector3D(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    public double deg2rad(double deg)
    {
        return deg * Math.PI / 180;
    }

    public void fresnel(Vector3D I, Vector3D N, double ior, double kr)
    {
        double cosi = clamp(-1, 1, I.dotProduct(N));
        double etai = 1;
        double etat = ior;
        if (cosi > 0)
        {
            cosi = -cosi;
        } else
        {
            double temp = etat;
            etat = etai;
            etai = temp;
        }

        double sint = etai / etat * Math.sqrt(Math.max(0.0, 1 - cosi * cosi));
        if (sint >= 1)
        {
            kr = 1;
        } else
        {
            double cost = Math.sqrt(Math.max(0.0, 1 - sint * sint));
            cosi = Math.abs(cosi);
            double Rs = ((etat * cosi) - (etai * cost)) / ((etat * cosi) + (etai * cost));
            double Rp = ((etai * cosi) - (etat * cost)) / ((etai * cosi) + (etat * cost));
            kr = (Rs * Rs + Rp * Rp) / 2;
        }
    }

}
