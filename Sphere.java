
public class Sphere {
    private Vector3 pos;
    private float radius;
    private CColor color;
    
    public Sphere(float x, float y, float z, float r, CColor color) {
        this.pos = new Vector3(x, y, z);
        this.color = color;
        this.radius = r;
    }
    
    public Sphere() {
        pos = new Vector3();
        color = new CColor();
        radius = 0;
    }
    
    private boolean intersect(Vector3 orig, Vector3 hitPoint) {
        float d = 0;
        a
        
        
        if (d < 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
