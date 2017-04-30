package core.gui;

import core.Globals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gabriel Jadderson on 19-04-2017.
 */
public class GUIPainter extends JComponent implements ActionListener
{
    Graphics2D g2;

    public GUIPainter()
    {
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g2 = (Graphics2D) g.create();
        super.paintComponent(g2);

        if (Globals.outputRenderedImage != null)
            g2.drawImage(Globals.outputRenderedImage, 0, 0, null);

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }
}
