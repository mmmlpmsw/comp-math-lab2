package mmmlpmsw.comp_math.lab2.Riemann_sum;

public class Interval {
    private double left, right;
    private boolean isLeftIncluded, isRightIncluded, isPoint;
    private static final double EPS = 1e-11d;

    public Interval (double left, boolean isLeftIncluded, double right, boolean isRightIncluded) {
        setLeft(left);
        setRight(right);
        setLeftIncluded(isLeftIncluded);
        setRightIncluded(isRightIncluded);
        isPoint = false;
        checkInterval();
    }

    public Interval (double left) {
        this(left, true, left, true);
        isPoint = true;
    }

    private void checkInterval() {
        if (left > right + EPS) {
            System.out.println("Wrong borders of the interval");
        }
    }

    public boolean isIntersect(Interval that) {
        if (this.left /*+ EPS */< that.left) {
            if (this.right /*+ EPS*/ < that.left) {
                return false;
            }

            if (Math.abs(this.right - that.left) < EPS  && that.isLeftIncluded && this.isRightIncluded) {
                return true;
            }

            return this.right /*+ EPS */> that.left;
        } else if (Math.abs(this.left - that.left) < EPS) {
            if (this.isLeftIncluded && that.isLeftIncluded) {
                return true;
            }

            return (!this.isPoint() || that.isLeftIncluded) && (!that.isPoint() || that.isRightIncluded);
        } else {
            if (that.right /*+ EPS*/ < this.left) {
                return false;
            }

            if (Math.abs(that.right - this.left) < EPS  && this.isLeftIncluded && that.isRightIncluded) {
                return true;
            }

            return that.right /*+ EPS */> this.left;
        }
    }

    public boolean isPoint() {
        return isPoint;
    }

    public double getLeft() {
        return left;
    }

    private void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    private void setRight(double right) {
        this.right = right;
    }

    private boolean isLeftIncluded() {
        return isLeftIncluded;
    }

    private void setLeftIncluded(boolean leftIncluded) {
        isLeftIncluded = leftIncluded;
    }

    private boolean isRightIncluded() {
        return isRightIncluded;
    }

    private void setRightIncluded(boolean rightIncluded) {
        isRightIncluded = rightIncluded;
    }
}
