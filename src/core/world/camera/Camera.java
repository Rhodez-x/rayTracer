/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.world.camera;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import static core.Globals.*;

public class Camera
{
    public Vector3D startPos;

    public Camera(Vector3D startPos)
    {
        this.startPos = startPos;
    }

    public Vector3D calcDir(Vector3D panePoint)
    {
        return (Vector3D) panePoint.subtract(startPos);
    }

    public static Vector3D WorldToScreenPoint(Vector3D position)
    {
        //TODO: implement this
        return null;
    }

    public static Vector3D ScreenToWorldPoint(Vector3D position)
    {
        //TODO: implement this
        return null;
    }

    public static Vector3D projectToView(Vector3D position)
    {
        return new Vector3D(position.getX() / WIDTH * VIEW_WIDTH - VIEW_WIDTH / 2.0, position.getY() / HEIGHT * VIEW_HEIGHT - VIEW_HEIGHT / 2.0, position.getZ());
    }


}
