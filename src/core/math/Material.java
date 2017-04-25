package core.math;

import core.Globals;

import static core.Globals.HEIGHT;
import static core.Globals.WIDTH;

public class Material
{
    public final Color color;
    private int[] x;
    private int[] y;
    private Color[][] world_color;

    public Material(Color color)
    {
        this.x = new int[HEIGHT];
        this.y = new int[WIDTH];
        this.world_color = new Color[HEIGHT][WIDTH];
        for(int x = 0; x < HEIGHT; x++)
        {
            for(int y = 0; y < WIDTH; y++)
            {
                world_color[x][y] = new Color(30, 30, 30);
            }
        }
        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }

    public void paint(int x, int y, Color color)
    {
        int[] rbg = new int[3];
        rbg[0] = (int)world_color[x][y].r;
        rbg[1] = (int)world_color[x][y].g;
        rbg[2] = (int)world_color[x][y].b;
        Globals.outputRenderedImage.getRaster().setPixel(x, y, rbg);
    }

    public void setPaint(int x, int y, Color color)
    {
        world_color[x][y] = color;
    }

    public Color getPaint(int x, int y)
    {
        if(world_color[x][y] == null)
        {
            System.out.println("world_color is null");
            return new Color(255, 255, 255);
        }

        return world_color[x][y];
    }
}
