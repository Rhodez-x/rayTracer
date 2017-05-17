package core.gui;

import core.Globals;
import core.util.Util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * used for debugging mostly
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
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            Globals.gui.ShutDown();
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
            Util.takeScreenShot();

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

}