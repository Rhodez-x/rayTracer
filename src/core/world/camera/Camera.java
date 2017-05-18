/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.world.camera;

import core.world.math.VecMath;
import core.world.ray.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import static core.Globals.*;

public class Camera
{

    public Vector3D origin;
    public Vector3D lower_left_corner;
    public Vector3D horizontal;
    public Vector3D vertical;
    public Vector3D u, v, w;
    public double lens_radius;


    public Camera(Vector3D lookfrom, Vector3D lookat, Vector3D vup, double vfov, double aspect, double aperture, double focus_dist)
    {

        lens_radius = aperture / 2;
        double theta = vfov * Math.PI / 180;
        double half_height = Math.tan(theta / 2);
        double half_width = aspect * half_height;

        origin = lookfrom;
        w = VecMath.getUnitVector(lookfrom.subtract(lookat));
        u = VecMath.getUnitVector(vup.crossProduct(w));
        v = w.crossProduct(u);

        lower_left_corner = origin.subtract(u.scalarMultiply(half_width * focus_dist)).subtract(v.scalarMultiply(half_width * focus_dist)).subtract(w.scalarMultiply(focus_dist));

        horizontal = u.scalarMultiply((2 * half_width) * focus_dist);
        vertical = v.scalarMultiply((2 * half_height) * focus_dist);

    }

    public static Vector3D projectToView(Vector3D position)
    {
        return new Vector3D(position.getX() / WIDTH * VIEW_WIDTH - VIEW_WIDTH / 2.0, position.getY() / HEIGHT * VIEW_HEIGHT - VIEW_HEIGHT / 2.0, position.getZ());
    }

    public Ray getRay(double s, double t)
    {
        /*/
        Vector3D rd = random_in_unit_disk().scalarMultiply(lens_radius);

        Vector3D offset = u.scalarMultiply(rd.getX()).add(v.scalarMultiply(rd.getY()));

        return new Ray(origin.add(offset), lower_left_corner.add(horizontal.scalarMultiply(s)).add(vertical.scalarMultiply(t)).subtract(origin).subtract(offset));
    */


        return new Ray(origin, (lower_left_corner.add(horizontal.scalarMultiply(s)).add(vertical.scalarMultiply(t)).subtract(origin)));

    }

    public Vector3D random_in_unit_disk()
    {
        Vector3D p;
        do
        {
            Vector3D v = new Vector3D(Math.random(), Math.random(), 0);
            Vector3D s = new Vector3D(1, 1, 0);
            p = v.scalarMultiply(2.0).subtract(s);
        } while (p.dotProduct(p) >= 1.0);
        return p;
    }


}