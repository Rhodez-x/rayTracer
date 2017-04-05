public class Main {
    
    public static void main(String[] args) {
        Ray myRay = new Ray(new Vector3(5.0f, 0.0f, 0.0f), new Vector3(1,0,0) );
        Sphere mySphere = new Sphere(0.0f, 0.0f, 0.0f, 2.0f, new CColor(0, 255, 0, 255));
        
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.println(mySphere.intersects(myRay, 10));
            }
        }  
    }
}
