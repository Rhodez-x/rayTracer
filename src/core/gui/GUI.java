package core.gui;

import javax.swing.*;

/**
 * Created by Gabriel Jadderson on 19-04-2017.
 */
public class GUI extends JFrame
{

    public GUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GUIPainter());
        setLocationRelativeTo(null);
        setSize(1000, 1000);
        setLayout(null);
        repaint();
        validate();
        setVisible(true);
    }
}
