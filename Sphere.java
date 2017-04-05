
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
        Vector3 OC = Vector3.sub(origin, pos);
        float b = 2 * Vector3.dot(OC, direction);
        float c = Vector3.dot(OC, OC) - (radius * radius);
        float distanceCenter = (b*b) - (4 * c);
        if (distanceCenter < 0) {
            return false;
        }
        else {
            // måle distance eller sige om den rammer kanten
            distanceCenter = (float) Math.sqrt(distanceCenter);
            float t0 = -b - distanceCenter;
            float t1 = -b + distanceCenter;
            return (t0 < t1) ? true : false;
        }
    }
}
