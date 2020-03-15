package mmmlpmsw.comp_math.lab2.Riemann_sum.functions;


public class SimpleSquareRoot implements Function {

    double a;

    public SimpleSquareRoot() {
        a = 1;
    }
    public SimpleSquareRoot(double a) {
        this.a = a;
    }

    @Override
    public double getY(double x) {
        return Math.sqrt(a * x);
    }

    @Override
    public String toString() {
        return "f(x) = √x";
    }
}
