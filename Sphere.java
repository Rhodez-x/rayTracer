
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
        if (d2 > radius * radius) {
            return false;
        }
        
        float thc = (float) Math.sqrt(radius * radius - d2);
        float t0 = tca - thc;
        float t1 = tca + thc;
        if (t0 > t1) {
            float temp = t0;
            t0 = t1;
            t1 = temp;
        }
        
        Vector3 OC2 = Vector3.sub(origin, pos);
        float a = Vector3.dot(direction, direction);
        float b = 2 * Vector3.dot(OC2, direction);
        float c = Vector3.dot(OC2, OC2) - (radius * radius);
        if (!solveQuadratic(a, b, c, t0, t1)) {
            return false;
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
    
    public boolean solveQuadratic(float a, float b, float c, float x0, float x1) {
        //float distanceCenter = (b*b) - (4 * c);
        float discr = b * b - 4 * a * c ;
        if (discr < 0) {
            return false;
        }
        else if (discr == 0) {
            x0 = x1 = (float) (-0.5 * b / a);
        }
        else {
            float q = 0;
            if (b > 0) {
                q = (float) (-0.5 * (b + (float) Math.sqrt(discr)));
            }
            else {
                q = (float) (-0.5 * (b - (float) Math.sqrt(discr)));
            }
            x0 = q / a;
            x1 = c / q;
        }
        if (x0 > x1) {
            float temp = x0;
            x0 = x1;
            x1 = temp;
        }
        return true;
    }
    
}
