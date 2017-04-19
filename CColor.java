
public class CColor {
    public int r,g,b,a;
    
    public CColor() {
        r = 0;
        g = 0;
        b = 0;
        a = 0;
    }
    
    public CColor(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b; 
        this.a = a;
    }
    
    public void add(CColor color) {
        this.r = (this.r + color.r) / 2;
        if (this.g < 255 && this.g >= 0) {
            
        }
        if (this.b < 255 && this.b >= 0) {
            
        }
    }
    
    public CColor calcDepth(float depth) {
        int newDepth = (int) depth;
        CColor calColor = new CColor(0,0,0,0);
        int x = this.r - newDepth;
        if (x >= 0) {
            calColor.r = x;
        }
        x = this.g - newDepth;
        if (x >= 0) {
            calColor.g = x;
        }
        x = this.b - newDepth;
        if (x >= 0) {
            calColor.b = x;
        }
        return calColor;
    }
    
    
}
