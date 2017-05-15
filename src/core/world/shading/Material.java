package core.world.shading;

import core.world.math.VecMath;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Material
{
    public Vector3D albedo; //color of material
    public double fuzz;
    public double refractionIndex;

    MaterialType materialType;

    public Material(MaterialType materialType, Vector3D albedo, double fuzz, double refractionIndex)
    {
        this.materialType = materialType;
        this.albedo = albedo;
        this.refractionIndex = refractionIndex;

        if (materialType.equals(MaterialType.METAL))
        {
            if (fuzz < 1)
                this.fuzz = fuzz;
            else
                this.fuzz = 1;
        }
    }

    public double[] getRGBArray()
    {
        double[] rgb = new double[3];
        double r = Math.sqrt(albedo.getX()) * 255.99;
        double g = Math.sqrt(albedo.getY()) * 255.99;
        double b = Math.sqrt(albedo.getZ()) * 255.99;
        rgb[0] = (int) r;
        rgb[1] = (int) g;
        rgb[2] = (int) b;
        return rgb;
    }

    public Vector3D reflect(Vector3D V, Vector3D N)
    {
        //return V.subtract(N.scalarMultiply(2 * V.dotProduct(N)));
        double dx = 2 * V.dotProduct(N);
        return V.subtract(N.scalarMultiply(dx));
    }


    public ScatterInfo scatter(Ray ray, RayInfo rayInfo)
    {
        if (rayInfo.material.materialType.equals(MaterialType.LAMBERTIAN))
        {
            return scatterLambertian(ray, rayInfo);
        } else if (rayInfo.material.materialType.equals(MaterialType.METAL))
        {
            return scatterMetal(ray, rayInfo);
        } else if (rayInfo.material.materialType.equals(MaterialType.DIELECTRIC))
        {
            return scatterDielectric(ray, rayInfo);
        }
        return null;
    }


    private ScatterInfo scatterLambertian(Ray ray, RayInfo rayInfo)
    {
        ScatterInfo scatterInfo = new ScatterInfo();
        Vector3D target = rayInfo.point.add(rayInfo.normal).add(VecMath.random_in_unit_sphere());
        scatterInfo.scattered = new Ray(rayInfo.point, target.subtract(rayInfo.point));
        scatterInfo.attenuation = albedo;
        scatterInfo.didScatter = true;
        return scatterInfo;
    }

    private ScatterInfo scatterMetal(Ray ray, RayInfo rayInfo)
    {
        ScatterInfo scatterInfo = new ScatterInfo();
        Vector3D reflected = reflect(VecMath.unit_vector(ray.dir), rayInfo.normal);
        scatterInfo.scattered = new Ray(rayInfo.point, VecMath.multiply(VecMath.add(reflected, rayInfo.material.fuzz), VecMath.random_in_unit_sphere()));
        scatterInfo.attenuation = albedo;
        if (scatterInfo.scattered.dir.dotProduct(rayInfo.normal) > 0)
        {
            scatterInfo.didScatter = true;
        }
        return scatterInfo;
    }

    private ScatterInfo scatterDielectric(Ray ray, RayInfo rayInfo)
    {
        ScatterInfo scatterInfo = new ScatterInfo();
        Vector3D outwardNormal;
        Vector3D reflected = reflect(ray.dir, rayInfo.normal);
        double ni_over_nt;
        scatterInfo.attenuation = new Vector3D(1.0, 1.0, 1.0);
        ScatterInfo refractionInfo;
        double reflectProp;
        double cosine;

        if (ray.dir.dotProduct(rayInfo.normal) > 0)
        {
            outwardNormal = rayInfo.normal.negate();
            ni_over_nt = refractionIndex;
            cosine = ray.dir.dotProduct(rayInfo.normal) / VecMath.length(ray.dir);
            cosine = Math.sqrt(1 - refractionIndex * refractionIndex * (1 - cosine * cosine));
        } else
        {
            outwardNormal = rayInfo.normal;
            ni_over_nt = 1.0 / refractionIndex;
            cosine = -ray.dir.dotProduct(rayInfo.normal) / VecMath.length(ray.dir);
        }
        if ((refractionInfo = refract(ray.dir, outwardNormal, ni_over_nt)).didRefract)
        {
            reflectProp = schlick(cosine, refractionIndex);
        } else
        {
            scatterInfo.scattered = new Ray(rayInfo.point, reflected);
            reflectProp = 1.0;
        }
        if (Math.random() < reflectProp)
        {
            scatterInfo.scattered = new Ray(rayInfo.point, reflected);
        } else
        {
            scatterInfo.scattered = new Ray(rayInfo.point, refractionInfo.refracted);
        }
        scatterInfo.didScatter = true;

        return scatterInfo;
    }

    public ScatterInfo refract(Vector3D v, Vector3D n, double ni_over_nt)
    {
        ScatterInfo scatterInfo = new ScatterInfo();
        Vector3D uv = VecMath.unit_vector(v);
        double dt = uv.dotProduct(n);
        double discriminant = 1.0 - ni_over_nt * ni_over_nt * (1 - dt * dt);
        if (discriminant > 0)
        {
            scatterInfo.refracted = uv.subtract(n.scalarMultiply(dt)).scalarMultiply(ni_over_nt).subtract(n.scalarMultiply(Math.sqrt(discriminant)));
            scatterInfo.didRefract = true;
        } else
        {
            scatterInfo.didRefract = false;
        }
        return scatterInfo;
    }


    public double schlick(double cosine, double refractionIndex)
    {
        double r0 = (1 - refractionIndex) / (1 + refractionIndex);
        r0 = r0 * r0;
        return r0 + (1 - r0) * Math.pow((1 - cosine), 5);
    }

}
