import interfaces.OptimizationProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Agent {

    private final OptimizationProblem problem;
    private double luciferin;
    private double sensorRange;
    private List<Double> currentPosition;
    private List<Double> targetPosition;
    private List<Agent> neighbors;
    private double currentValue;
    private int currentNeighborNumber;

    public Agent(List<Double> initialPosition, OptimizationProblem problem, double luciferin, double sensorRange)
    {
        this.problem = problem;
        this.currentPosition = initialPosition;
        this.luciferin = luciferin;
        this.sensorRange = sensorRange;
        this.currentValue = problem.getValue(currentPosition);
    }

    public void setLuciferin(double luciferin) {
        this.luciferin = luciferin;
    }

    public double getLuciferin() {
        return luciferin;
    }

    public void setSensorRange(double sensorRange) {
        this.sensorRange = sensorRange;
    }

    public double getSensorRange() {
        return sensorRange;
    }

    public void setCurrentPosition(List<Double> currentPosition) { this.currentPosition = currentPosition; }

    public List<Double> getCurrentPosition() { return currentPosition; }

    public void setTargetPosition(List<Double> targetPosition) { this.targetPosition = targetPosition; }

    public List<Double> getTargetPosition() { return targetPosition; }

    public void setNeighbors(List<Agent> neighbors) {
        this.neighbors = neighbors;
        this.currentNeighborNumber = neighbors.size();
    }

    public int getNeighborsNumber() { return currentNeighborNumber; }

    public double getCurrentValue() { return currentValue; }

    public void findTargetPosition(double stepSize) {
        if (currentNeighborNumber > 0) {
            //count probability of choosing neighbor
            ArrayList<Double> probability = new ArrayList<>();
            double sum = neighbors.stream().mapToDouble(neighbor ->
                    (neighbor.getLuciferin() - getLuciferin())).sum();
            double finalSum = sum;
            neighbors.forEach(neighbor -> probability.add((neighbor.getLuciferin() - getLuciferin())/finalSum));

            Random random = new Random();

            double rndTarget = random.nextDouble();
            sum = 0;
            for (int i = 0; i < neighbors.size(); i++) {
                if (rndTarget >= sum && rndTarget <= sum + probability.get(i)) {
                    setTargetPosition(Vector.getTargetPosition(getCurrentPosition(),
                            neighbors.get(i).getCurrentPosition(), stepSize));
                    currentValue = problem.getValue(getTargetPosition());
                    break;
                }
                sum = sum + probability.get(i);
            }

        } else {
            generateRandomTargetPosition(stepSize);
        }
    }

    private void generateRandomTargetPosition(double stepSize) {
        ArrayList<Double> randomTargetPosition = new ArrayList<>();
        Random random = new Random();
        double newPos;

        int count = 0;
        do {
            int i = 0;
            for (Double dim : currentPosition) {
                boolean finish = false;
                do {
                    //if agent does not have neighbors make bigger step
                    newPos = dim + (2.0 * random.nextDouble() - 1.0) * stepSize * 5;
                    if (newPos <= problem.getSearchArea().get(i).getMax()
                            && newPos >= problem.getSearchArea().get(i).getMin()) {
                        finish = true;
                    }
                } while (!finish);
                randomTargetPosition.add(newPos);
                i++;
            }

            double targetValue = problem.getValue(randomTargetPosition);
            if (targetValue > currentValue) {
                setTargetPosition(randomTargetPosition);
                currentValue = targetValue;
                return;
            }
            count++;
        }
        while (count < 3);
        setTargetPosition(currentPosition);
    }
}
