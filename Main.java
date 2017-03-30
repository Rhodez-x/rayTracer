public class Main {
    
    public static boolean intersect(Ray ray, float t, Sphere sphere) {
        if (t > 0) {
            Vector3 hitPoint = Vector3.add(ray.orig, Vector3.multiplyFloat(ray.dir, t));
            return sphere.intersect(ray.orig, hitPoint);
        } 
        else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        Ray myRay = new Ray(new Vector3(1.0f, 1.0f, 1.0f), new Vector3(0,0,1) );
        Sphere mySphere = new Sphere(1.0f, 1.0f, 1.0f, 50.0f, new CColor(0, 255, 0, 255));
        
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                
            }
        }  
    }
}
