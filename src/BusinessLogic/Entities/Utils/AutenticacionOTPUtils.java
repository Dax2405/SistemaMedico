package BusinessLogic.Entities.Utils;

public class AutenticacionOTPUtils {
    public static String generarOTP() {
        int randomPin = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(randomPin);
    }

}
