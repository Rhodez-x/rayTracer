package core.world.shading;

import core.world.math.VecMath;
import core.world.ray.Ray;
import core.world.ray.RayInfo;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Material
{
    public Vector3D albedo; //color of material

    MaterialType materialType;

    public Material(MaterialType materialType, Vector3D albedo)
    {
        this.materialType = materialType;
        this.albedo = albedo;
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
            return LambertianShading(ray, rayInfo);
        }
        return null;
    }


    private ScatterInfo LambertianShading(Ray ray, RayInfo rayInfo)
    {
        ScatterInfo scatterInfo = new ScatterInfo();
        Vector3D target = rayInfo.point.add(rayInfo.normal).add(VecMath.random_in_unit_sphere());
        scatterInfo.scattered = new Ray(rayInfo.point, target.subtract(rayInfo.point));
        scatterInfo.attenuation = albedo;
        scatterInfo.didScatter = true;
        return scatterInfo;
    }

    public double[] getRGBArray() //for painting the color to the bufferedImage
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

}