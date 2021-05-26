import interfaces.ModelConstants;

//this is only for testing purposes
public class TestConstants implements ModelConstants {

    double STEP_SIZE = 0.01;
    double beta = 0.08;
    double luciferinDecayConstant = 0.4;
    double luciferinEnhancementConstant = 0.6;
    double desiredNumberOfNeighbors = 5;
    double initialSensorRange = 0.2;
    double maximalSensorRange = 1.0;
    double initialLuciferinValue = 1.0;

    @Override
    public double getInitialLuciferinValue() {
        return initialLuciferinValue;
    }

    @Override
    public double getLuciferinEnhancementConstant() {
        return luciferinEnhancementConstant;
    }

    @Override
    public double getLuciferinDecayConstant() {
        return luciferinDecayConstant;
    }

    @Override
    public double getStepSize() {
        return STEP_SIZE;
    }

    @Override
    public double getMaximalSensorRange() {
        return maximalSensorRange;
    }

    @Override
    public double getBeta() {
        return beta;
    }

    @Override
    public double getInitialSensorRange() {
        return initialSensorRange;
    }

    @Override
    public double getDesiredNumberOfNeighbors() {
        return desiredNumberOfNeighbors;
    }
}
