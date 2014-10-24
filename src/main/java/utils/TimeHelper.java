package utils;

/**
 * Created by titaevskiy.s on 24.10.14
 */
public class TimeHelper {
    public static void sleep(int period){
        try{
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
