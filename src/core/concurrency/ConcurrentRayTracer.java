package core.concurrency;

import core.Main;
import core.world.camera.Camera;
import core.world.math.VecMath;
import core.world.ray.Ray;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static core.Globals.HEIGHT;
import static core.Globals.WIDTH;

/**
 * Created by Gabriel Jadderson on 26-05-2017.
 */
public class ConcurrentRayTracer
{

    public static ConcurrentHashMap<Integer, int[]> pixelMap; //this will hold x and y coords
    public static ConcurrentHashMap<Integer, Ray> rayMap; //this will hold a ray

    public static ExecutorService executorService = Executors.newFixedThreadPool(5); //fix thread pool number
    public static CountDownLatch latch = new CountDownLatch((HEIGHT * WIDTH) - 1);

    public static AtomicInteger counter = new AtomicInteger(0); //TODO: DELETE ME. used for debug can be deleted

    public ConcurrentRayTracer(Camera camera, Main main)
    {
        pixelMap = new ConcurrentHashMap<>();
        rayMap = new ConcurrentHashMap<>();

        generateAndStoreRayPerPixel(camera);

        // System.out.println(pixelMap.size()); //TODO: DELETE ME. used for debug can be deleted
        //System.out.println(rayMap.size()); //TODO: DELETE ME. used for debug can be deleted


        IntStream.range(0, (HEIGHT * WIDTH) - 1).parallel().forEach(x -> //parallel is a good idea, but doesn't affect run time too much.
        {
            executorService.submit(() -> rayTraceAsync(main, latch, x, counter));
        });

        System.out.println("concurrency started...");

        try
        {
            latch.await();
            executorService.shutdownNow();
            executorService.awaitTermination(1L, TimeUnit.DAYS);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("raytracer done");

    }


    /**
     * this runs sequentially
     * populated two hashmaps with pixel coordinates
     *
     * @param camera
     */
    private void generateAndStoreRayPerPixel(Camera camera)
    {
        int pixelNumber = 0;
        for (int y = 0; y < HEIGHT; y++)
        {
            for (int x = 0; x < WIDTH; x++)
            {
                //  System.out.println(pixelNumber); //TODO: DELETE ME. used for debug can be deleted

                int[] arr = new int[2];
                arr[0] = x;
                arr[1] = y;

                Ray ray = camera.calculateRayAt(x / (double) WIDTH, y / (double) HEIGHT);

                pixelMap.put(pixelNumber, arr);
                rayMap.put(pixelNumber, ray);
                pixelNumber++;
            }
        }
        System.out.println("population done");
    }

    //this runs asynchronously
    private void rayTraceAsync(Main main, CountDownLatch countDownLatch, int pixelNumber, AtomicInteger c)
    {

        Vector3D color = Vector3D.ZERO;

        Ray ray = rayMap.get(pixelNumber);
        int[] arr = pixelMap.get(pixelNumber);

        color = VecMath.plusEqual(color, main.traceBoundingVolumes(ray));

        main.renderColor(color, arr[0], arr[1]);

        countDownLatch.countDown();
        //System.out.println("done pixel: " + pixelNumber + " at " + counter.incrementAndGet() + " max "); //removed for quicker run time. //TODO: DELETE ME. used for debug can be deleted

    }
}
