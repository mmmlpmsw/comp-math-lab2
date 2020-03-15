package mmmlpmsw.comp_math.lab2.Riemann_sum.functions;

import mmmlpmsw.comp_math.lab2.Riemann_sum.Interval;

import java.util.ArrayList;

public class SimpleHyperbola implements Function {
    double a;

    public SimpleHyperbola() {
        a = 1;
    }
    public SimpleHyperbola(double a) {
        this.a = a;
    }

    @Override
    public double getY(double x) {
        return (a / x);
    }

    public String toString() {
        return "f(x) = 1/x";
    }
}
