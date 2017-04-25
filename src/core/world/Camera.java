/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.world;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Camera {
    public Vector3D startPos;
    
    public Camera(Vector3D startPos) {
        this.startPos = startPos;
    }
    
    public Vector3D calcDir(Vector3D panePoint) {
        return panePoint.subtract(startPos);
    }
    
}
