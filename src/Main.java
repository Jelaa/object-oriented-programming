import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//this is only for testing purposes
public class Main {

    static List<List<Double>> agents = new ArrayList<>();
    static List<List<Double>> finalAgentsPosition = new ArrayList<>();
    static int agentNumber = 400;

    public static void main(String[] args) {

        Random rnd = new Random();
        TestOptimizationProblem problem = new TestOptimizationProblem();
        TestConstants constants = new TestConstants();

        for (int i = 0; i < agentNumber; i++) {
            double min_x = problem.getSearchArea().get(0).getMin();
            double max_x = problem.getSearchArea().get(0).getMax();
            double min_y = problem.getSearchArea().get(1).getMin();
            double max_y = problem.getSearchArea().get(1).getMax();
            List<Double> agentPosition = new ArrayList<>();
            double x = rnd.nextDouble()*(max_x-min_x)+min_x;
            double y = rnd.nextDouble()*(max_y-min_y)+min_y;
            agentPosition.add(x);
            agentPosition.add(y);
            agents.add(agentPosition);
        }

        Swarm swarm = new Swarm();
        swarm.setOptimizationProblem(problem);
        swarm.setModelCostants(constants);
        swarm.setInitialPositions(agents);
        swarm.optimize(10000, TimeUnit.MILLISECONDS);
        finalAgentsPosition = swarm.getFinalPositions();
        System.out.println(finalAgentsPosition);
    }


}
