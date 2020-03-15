package mmmlpmsw.comp_math.lab2.Riemann_sum.functions;

public class SimpleStraightLine implements Function {
    private double a, b;
    public SimpleStraightLine() {
        this(1, 0);
    }
    public SimpleStraightLine(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getY(double x) {
        return a*x + b;
    }

    @Override
    public String toString() {
        return "f(x) = x";
    }
}
