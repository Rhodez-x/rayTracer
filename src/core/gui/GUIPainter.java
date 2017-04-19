package core.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gabriel Jadderson on 19-04-2017.
 */
public class GUIPainter extends JComponent
{

    Graphics2D g2;

    public GUIPainter()
    {

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g2 = (Graphics2D) g.create();
        super.paintComponent(g2);

        g2.drawImage(core.Main.bufferedImage, 0, 0, null);
    }
}
