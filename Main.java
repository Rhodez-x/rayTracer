
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    public static BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static File outfile = new File("out.png");
    
    public static void main(String[] args) {
        
        
        
        //Sphere mySphere = new Sphere(200.0f, 200.0f, 0.0f, 25.0f, new CColor(0, 255, 0, 255));
        Sphere mySphere2 = new Sphere(100.0f, 100.0f, 300.0f, 200.0f, new CColor(0, 255, 0, 255));
        Sphere mySphere3 = new Sphere(150.0f, 150.0f, -50.0f, 50.0f, new CColor(0, 255, 0, 255));
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Ray myRay = new Ray(new Vector3((float) y, (float) x, -110.0f), new Vector3(0,0,1) );
                
                setColor(y, x, 255, 255, 255);
                if(mySphere2.intersects(myRay, 1)){
                    int depth = (int) mySphere2.depth(myRay, 1)/2;
                    setColor(y, x, 0, 255 - depth, 0);
                }
                if(mySphere3.intersects(myRay, 1)){
                    int depth = (int) mySphere3.depth(myRay, 1);
                    setColor(y, x, 0 + depth, 0 + depth, 0 + depth);
                }
                //if(mySphere.intersects(myRay, 1)) {
                //    setColor(y, x, 255, 0, 0);
                //}
                //if(mySphere3.intersects(myRay, 1)) {
                //    setColor(y, x, 255, 0, 0);
                //}
            }
        }

        try {
            ImageIO.write(bufferedImage, "png", outfile);
        } catch (IOException ex) {
            System.out.println("Damn..!!");
        }
    }
    
    public static void setColor(int x, int y, int r, int g, int b) {
        int[] rbg = new int[3];
        rbg[0] = r;
        rbg[1] = g;
        rbg[2] = b;
        bufferedImage.getRaster().setPixel(x, y, rbg);
    }
}
