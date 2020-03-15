package mmmlpmsw.comp_math.lab2.Riemann_sum;

import mmmlpmsw.comp_math.lab2.OutputCombiner;
import mmmlpmsw.comp_math.lab2.Riemann_sum.functions.Function;

public class Algorithm {

    private static final double EPSILON = 1e-5;

    private OutputCombiner combiner;

    public Algorithm(OutputCombiner combiner) {
        this.combiner = combiner;
    }

    public void calculate(Function function, double low, double high, double userAccuracy) {
        if (userAccuracy == 0) {
            combiner.report(0, "");
            return;
        }

        if (high == low) {
            combiner.report(1, "");
            return;
        }

        for (String method : new String[] {"left", "middle", "right"}) {
            int stepCounter = 4;

            double curValue = calculateByMethod(method, function, low, high, stepCounter);
            double prevValue;

            do {
                stepCounter <<= 1;

                if (stepCounter > 1000000000) {
                    combiner.report(2, method);
                    return;
                }

                prevValue = curValue;
                curValue = calculateByMethod(method, function, low, high, stepCounter);

                if (!Double.isFinite(curValue) || !Double.isFinite(prevValue)) {
                    combiner.report(2, "");
                    return;
                }
            } while (calculateError(prevValue, curValue, 3.0) > userAccuracy);

            combiner.reportOnSuccess(method, curValue, stepCounter, calculateError(prevValue, curValue, 3.0));
        }
    }

    private double calculateByMethod(String method, Function function, double low, double high, int stepCounter) {
        double step = calculateStep(low, high, stepCounter);
        double x;
        switch (method) {
            case "left" :
                x = low;
                return calculateIntegral(function, stepCounter, step, x);
            case "middle":
                x = low + step/2;
                return calculateIntegral(function, stepCounter, step, x);
            case "right":
                x = low + step;
                return calculateIntegral(function, stepCounter, step, x);
            default:
                return Double.NaN;
        }
    }


    private double calculateIntegral(Function function, int stepCounter, double step, double x) {
        double result = 0;

        for (int i = 0; i < stepCounter; i++) {
            double fx = function.getY(x);
            if (!Double.isFinite(fx)) {
                if (i == 0) {
                    fx = function.getY(x + EPSILON);
                } else if (i == stepCounter - 1) {
                    fx = function.getY(x - EPSILON);
                } else {
                    fx = (function.getY(x - EPSILON) + function.getY(x + EPSILON)) / 2;
                }
            }
            x += step;
            result += fx;
        }
        return result * step;
    }

    private double calculateError(double integralN, double integral2N, double coefficient) {
        return (Math.abs(integral2N - integralN)) / coefficient;
    }
    private double calculateStep(double low, double high, double stepCounter) {
        return (high - low) * 1.0 / (stepCounter * 1.0);
    }
}
