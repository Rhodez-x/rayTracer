package core.gui;

import core.Globals;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Gabriel Jadderson on 29-04-2017.
 */
public class GUIKeyListener implements KeyListener
{
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_Q)
        {
            Globals.shapeList.forEach(x ->
            {

            });
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            Globals.gui.ShutDown();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
