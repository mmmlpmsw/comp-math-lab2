package mmmlpmsw.comp_math.lab2.utils;

public class OutputFormatter {

    public static String format(double residual) {
        String result = String.valueOf(residual);
        int cutIndex = result.indexOf("E");
        if (cutIndex != -1) {
        String number = result.substring(0, cutIndex);
        String order = result.substring(cutIndex + 1);
        result = number + " * 10 ^ (" + order + ")";
        return result;
        } else
            return String.valueOf(residual);
    }

    public static String format(double residual, int accuracy) {
        String result = String.valueOf(residual);
        int cutIndex = result.indexOf("E");
        if (cutIndex != -1) {
            String number = result.substring(0, cutIndex);
            String order = result.substring(cutIndex + 1);
            if (residual > 0) {
                result = number.substring(0, accuracy + 2) + " * 10 ^ (" + order + ")";
                return result;
            } else {
                result = number.substring(0, accuracy + 3) + " * 10 ^ (" + order + ")";
                return result;
            }
        } else
            return String.valueOf(residual);
    }

}
