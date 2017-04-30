package core.gui;

import core.Globals;
import core.Main;
import core.world.ray.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * used for debugging mostly
 * Created by Gabriel Jadderson on 29-04-2017.
 */
public class GUIKeyListener implements KeyListener
{

    Vector3D startPos = new Vector3D(0, 0, -8);


    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_A)
        {
            startPos = startPos.add(new Vector3D(-1, 0, 0));
            Main.rayTrace(new Ray(startPos, new Vector3D(0, 0, 0)));
            Globals.gui.repaint();
            Globals.gui.getContentPane().repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_D)
        {
            startPos = startPos.add(new Vector3D(1, 0, 0));
            Main.rayTrace(new Ray(startPos, new Vector3D(0, 0, 0)));
            Globals.gui.repaint();
            Globals.gui.getContentPane().repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_W)
        {
            startPos = startPos.add(new Vector3D(0, 1, 0));
            Main.rayTrace(new Ray(startPos, new Vector3D(0, 0, 0)));
            Globals.gui.repaint();
            Globals.gui.getContentPane().repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_S)
        {
            startPos = startPos.add(new Vector3D(0, -1, 0));
            Main.rayTrace(new Ray(startPos, new Vector3D(0, 0, 0)));
            Globals.gui.repaint();
            Globals.gui.getContentPane().repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_Q)
        {
            startPos = startPos.add(new Vector3D(0, 0, 1));
            Main.rayTrace(new Ray(startPos, new Vector3D(0, 0, 0)));
            Globals.gui.repaint();
            Globals.gui.getContentPane().repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_E)
        {
            startPos = startPos.add(new Vector3D(0, 0, -1));
            Main.rayTrace(new Ray(startPos, new Vector3D(0, 0, 0)));
            Globals.gui.repaint();
            Globals.gui.getContentPane().repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            Globals.gui.ShutDown();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

}
