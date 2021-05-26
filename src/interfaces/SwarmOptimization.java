package interfaces;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface SwarmOptimization {
    /**
     * Początkowe położenia agentów.
     *
     * @param positions - położenia początkowe
     */
    public void setInitialPositions(List<List<Double>> positions);

    /**
     * Końcowe położenia agentów
     *
     * @return położenia końcowe
     */
    public List<List<Double>> getFinalPositions();

    /**
     * Zlecenie optymalizacji przez czas workingTime wg. jednostek unit.
     *
     * @param workingTime - czas pracy
     * @param unit - jednostka czasu pracy
     */
    public void optimize(long workingTime, TimeUnit unit);

    /**
     * Metoda przekazuje informacje o problemie optymalizacyjnym do rozwiązania.
     *
     * @param problem - zadanie do rozwiązania
     */
    public void setOptimizationProblem(interfaces.OptimizationProblem problem);

    /**
     * Metoda przekazuje obiekt, z którego możliwe jest pobranie informacji o
     * wartościach parametrów kontrolujących pracę modelu.
     *
     * @param constants - stałe odpowiedzialne za działanie modelu
     */
    public void setModelCostants(interfaces.ModelConstants constants);
}
