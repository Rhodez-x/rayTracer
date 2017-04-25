/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.world;

import core.math.Vector3;

public class Camera {
    public Vector3 startPos;
    
    public Camera(Vector3 startPos) {
        this.startPos = startPos;
    }
    
    public Vector3 calcDir(Vector3 panePoint) {
        return Vector3.sub(panePoint, startPos);
    }
    
}
