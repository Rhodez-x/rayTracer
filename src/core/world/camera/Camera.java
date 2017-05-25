/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.world.camera;

import core.world.math.VecMath;
import core.world.ray.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Based on Peter Shirley's book, Ray Tracing in one weekend (Ray Tracing Minibooks Book 1)
 * https://www.amazon.com/Ray-Tracing-Weekend-Minibooks-Book-ebook/dp/B01B5AODD8
 */
public class Camera
{
    public Vector3D origin;
    public Vector3D lc;
    public Vector3D projectedWidth;
    public Vector3D projectedHeight;
    public Vector3D dx, dy;
    public Vector3D OD;

    public Camera(Vector3D origin, Vector3D dir, double viewWidth, double width, double height, double zoom)
    {
        this.origin = origin;
        this.OD = VecMath.getUnitVector(origin.subtract(dir));

        double hh = Math.tan(Math.toRadians(viewWidth) / 2);
        double hw = (width / height) * hh;

        dx = VecMath.getUnitVector(VecMath.up().crossProduct(OD));
        dy = OD.crossProduct(dx);
        projectedWidth = dx.scalarMultiply((2 * hw) * zoom);
        projectedHeight = dy.scalarMultiply((2 * hh) * zoom);

        lc = origin.subtract(dx.scalarMultiply(hw * zoom)).subtract(dy.scalarMultiply(hw * zoom)).subtract(OD.scalarMultiply(zoom));
    }

    public Ray calculateRayAt(double x, double y)
    {
        return new Ray(origin, (lc.add(projectedWidth.scalarMultiply(x)).add(projectedHeight.scalarMultiply(y)).subtract(origin)));
    }


}