
public class Sphere implements Shape {
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
    
    @Override
    public boolean intersects(Ray ray, double dist) {
        Vector3 origin = ray.orig;
        Vector3 direction = ray.dir;
        Vector3 OC = Vector3.sub(pos, origin);
        float tca = Vector3.dot(OC, direction);
        System.out.println(tca);
        if (tca < 0) {
            return false;
        }
        float d2 = Vector3.dot(OC, OC) - (tca * tca);
        if (d2 > radius ) {
            return false;
        }
        
        float thc = (float) Math.sqrt(radius - d2);
        float t0 = tca - thc;
        float t1 = tca + thc;
        if (t0 > t1) {
            float temp = t0;
            t0 = t1;
            t1 = temp;
        }
        
        if (t0 < 0) {
            t0 = t1;
            if (t0 < 0) {
                return false;
            }
        }
        System.out.println(t0);
        return true;
    }
}
