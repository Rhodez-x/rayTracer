package core.world.shading;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Material
{
    public Vector3D albedo;

    public Material(Vector3D albedo)
    {
        this.albedo = albedo;
    }

    public Vector3D reflect(Vector3D V, Vector3D N)
    {
        //return V.subtract(N.scalarMultiply(2 * V.dotProduct(N)));
        double dx = 2 * V.dotProduct(N);
        return V.subtract(N.scalarMultiply(dx));
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