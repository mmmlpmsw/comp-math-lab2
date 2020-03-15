package mmmlpmsw.comp_math.lab2;

import mmmlpmsw.comp_math.lab2.utils.Utilities;

public class OutputCombiner {

    public void report (int errorCode, String s) {

        switch (String.valueOf(errorCode)){
            case "0":
                System.out.println("Incorrect value of the accuracy.");
                break;
            case "1":
                System.out.println("The integral is 0, the integration limits are equal.");
                break;
            case "2":
                System.out.println(Utilities.colorize("[[RED]]Target accuracy not achieved. [[RESET]]"));
                break;
            case "3":
                System.out.println(Utilities.colorize("[[RED]]Target accuracy not achieved by this method: [[RESET]]" + s));
                break;

        }
    }

    public void reportOnSuccess(String method, double value, int stepCounter, double error) {
//        if (value < 1e-10d) value = 0;
        System.out.println("Value of the integral by the method of " + method +  " rectangles is " + value +
                " count of steps: " + stepCounter + ", error: " + error);
    }
}
