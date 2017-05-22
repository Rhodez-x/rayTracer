/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.world.shapes;

import java.util.ArrayList;

/**
 * @author rhodez-x
 */
public class BoundingVol
{

    public IShape shape;
    public ArrayList<IShape> listOfShapes;

    public BoundingVol(IShape shape, ArrayList<IShape> listInside)
    {
        this.shape = shape;
        this.listOfShapes = listInside;
    }





}
