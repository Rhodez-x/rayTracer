
public interface Shape {
    
    public boolean intersects(Ray ray, double dist);
    public float getDepth();
    public Material getMaterial();
}
