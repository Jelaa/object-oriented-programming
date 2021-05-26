import java.util.ArrayList;
import java.util.List;

public class Vector
{
    public static double getLength(List<Double> agent, List<Double> agentNeighbor)
    {
        double result = 0;
        int level = agent.size();

        for(int i = 0; i < level; i ++) {
            result += (agentNeighbor.get(i) - agent.get(i)) * (agentNeighbor.get(i) - agent.get(i));
        }
        result = Math.sqrt(result);

        return result;
    }

    public static List<Double> getTargetPosition(List<Double> agent, List<Double> agentNeighbor, double step)
    {
        ArrayList<Double> mid = new ArrayList<>();
        ArrayList<Double> targetPosition = new ArrayList<>();

        for (int i = 0; i < agent.size(); i++)
        {
            mid.add(agentNeighbor.get(i)-agent.get(i));
        }

        double lenght = getLength(agent, agentNeighbor);

        for (int i = 0; i < mid.size(); i++)
        {
            mid.set( i, (mid.get(i)/lenght) * step );
            targetPosition.add(i, agent.get(i) + mid.get(i));
        }
        return targetPosition;
    }
}
