import interfaces.ModelConstants;
import interfaces.OptimizationProblem;
import interfaces.SwarmOptimization;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Swarm implements SwarmOptimization {

    private ModelConstants constants;
    private OptimizationProblem problem;
    private final List<Agent> agents = new ArrayList<>();

    public Swarm() {}

    @Override
    public void setInitialPositions(List<List<Double>> positions) {
        for (List<Double> pos : positions) {
            agents.add(new Agent(pos, problem,
                    constants.getInitialLuciferinValue(),
                    constants.getInitialSensorRange()));
        }
    }

    @Override
    public List<List<Double>> getFinalPositions() {
        List<List<Double>> finalPositions = new ArrayList<>();
        agents.forEach(agent -> finalPositions.add(agent.getCurrentPosition()));
        return finalPositions;
    }

    @Override
    public void optimize(long workingTime, TimeUnit unit) {
        long current = 0;
        long start = System.currentTimeMillis();

        while (current - start <= unit.toMillis(workingTime)) {

            updateLuciferinForEachAgent(agents);
            findTragetPositionForEachAgent(agents);
            moveAgents(agents);
            updateSensorRangeForEachAgent(agents);
            current = System.currentTimeMillis();
        }

        //this is only for testing purposes (accuracy)
        /*int suma = 0;
        for(Agent agent: agents) {
            double val = agent.getCurrentValue();
            if(Math.abs(val) <0.05) {
                suma++;
            }
        }
        double acc = (double) suma/agents.size()*100;
        System.out.println(acc);*/
    }

    @Override
    public void setOptimizationProblem(interfaces.OptimizationProblem problem) {
        this.problem = problem;
    }

    @Override
    public void setModelCostants(interfaces.ModelConstants constants) {
        this.constants = constants;
    }

    private void updateSensorRangeForEachAgent(List<Agent> agents) {
      /*  r_i (t + 1) = min{ MaximalSensorRange,
        max{0, r_i (t) + Beta ( DesiredNumberOfNeighbors − liczba sąsiadów) } } */
       agents.forEach(agent -> agent.setSensorRange(Double.min(constants.getMaximalSensorRange(),
               Double.max(0, agent.getSensorRange() + constants.getBeta()
                       * constants.getDesiredNumberOfNeighbors() - agent.getNeighborsNumber()))));
    }

    private void moveAgents(List<Agent> agents) {
        agents.forEach(agent -> agent.setCurrentPosition(agent.getTargetPosition()));
    }

    private void findTragetPositionForEachAgent(List<Agent> agents) {
        findNeighbors(agents);
        agents.forEach(agent -> agent.findTargetPosition(constants.getStepSize()));
    }

    private void findNeighbors(List<Agent> agents) {
        //for each agent find neighbors
        agents.forEach(agent -> agent.setNeighbors(agents
                .parallelStream()
                .filter(neighbor -> Vector.getLength(agent.getCurrentPosition(),
                        neighbor.getCurrentPosition()) < agent.getSensorRange() &&
                        agent.getLuciferin() < neighbor.getLuciferin())
                .collect(Collectors.toList())));
    }

    private void updateLuciferinForEachAgent(List<Agent> agents) {
        //for each agent get value at N-dimensional position point
        agents.forEach(agent -> agent.setLuciferin((1 - constants.getLuciferinDecayConstant())
                * agent.getLuciferin() + constants.getLuciferinEnhancementConstant()
                * agent.getCurrentValue()));
    }
}
