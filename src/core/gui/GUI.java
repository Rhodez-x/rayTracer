package core.gui;


import core.Globals;
import core.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Gabriel Jadderson on 19-04-2017.
 */
public class GUI extends JFrame
{

    DisplayMode displayMode;

    public GUI()
    {
        if (isReallyHeadless())
            ShutDown();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GUIPainter());
        setPreferredSize(new Dimension(Globals.WIDTH, Globals.HEIGHT));
        setLocation(displayMode.getWidth() / 2 - (Globals.WIDTH / 2), displayMode.getHeight() / 2 - (Globals.HEIGHT / 2));
        setSize(Globals.WIDTH, Globals.HEIGHT);
        setLayout(null);
        setResizable(false);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent windowEvent)
            {
                ShutDown();
            }
        });
        addKeyListener(new GUIKeyListener());

        repaint();
        validate();
        setVisible(true);
    }

    public void ShutDown()
    {
        System.out.println("Shut down by gui");
        Main.renderFile();
        System.exit(0);
    }

    private boolean isReallyHeadless()
    {
        if (GraphicsEnvironment.isHeadless())
        {
            return true;
        }
        try
        {
            displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode();
            return displayMode == null;
        } catch (HeadlessException e)
        {
            e.printStackTrace();
            return true;
        }
    }
}
