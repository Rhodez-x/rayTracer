
public class Vector3 {
    
    private float x,y,z;
    
    public Vector3() {
        x = 0;
        y = 0;
        z = 0; 
    }
    
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z; 
    }
    
    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }
    
    public static Vector3 multiplyFloat(Vector3 a, float b) {
        return new Vector3(a.x * b, a.y * b, a.z * b);
    }
}
