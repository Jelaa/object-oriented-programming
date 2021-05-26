import interfaces.OptimizationProblem;

//this is only for testing purposes
public class TestInterval implements OptimizationProblem.Interval {

    double min;
    double max;

    public TestInterval(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public double getMin() {
        return min;
    }

    @Override
    public double getMax() {
        return max;
    }
}
