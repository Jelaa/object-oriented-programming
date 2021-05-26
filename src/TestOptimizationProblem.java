import interfaces.OptimizationProblem;
import java.util.List;

//this is only for testing purposes
public class TestOptimizationProblem implements OptimizationProblem {

    @Override
    public int getNumberOfDimensions() {
        return 2;
    }

    @Override
    public List<Interval> getSearchArea() {
        return List.of(new TestInterval(-5.0, 5.0), new TestInterval(-5.0, 5.0));
    }

    @Override
    public double getValue(List<Double> position) {
        double x = position.get(0);
        double y = position.get(1);
        double p1 = x * x + y - 11.0;
        double p2 = x + y * y - 7.0;
        return -p1 * p1 - p2 * p2;
    }
}
