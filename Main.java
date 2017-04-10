public class Main {
    
    public static void main(String[] args) {
        Ray myRay = new Ray(new Vector3(6.0f, 2.0f, 0.0f), new Vector3(-1,1,0) );
        Sphere mySphere = new Sphere(4.0f, 3.0f, 0.0f, 1.0f, new CColor(0, 255, 0, 255));
        Sphere mySphere2 = new Sphere(-4.0f, 3.0f, 0.0f, 1.0f, new CColor(0, 255, 0, 255));
        
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.println(mySphere.intersects(myRay, 223424));
                System.out.println(mySphere2.intersects(myRay, 223424));
            }
        }  
    }
}
