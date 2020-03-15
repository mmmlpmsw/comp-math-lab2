package mmmlpmsw.comp_math.lab2.Riemann_sum.functions;

public class LinearFractionalFunction implements Function {
    @Override
    public double getY(double x) {
        return x * (x + 1 ) / x;
    }

    public String toString() {
        return "f(x) = x * (x + 1 ) / x";
    }
}
