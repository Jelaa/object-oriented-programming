package interfaces;

/**
 * Stałe kontrolujące pracę modelu.
 */
public interface ModelConstants {
    /**
     * Początkowy poziom lucyferyny.
     *
     * @return początkowy poziom lucyferyny
     */
    public double getInitialLuciferinValue();

    /**
     * Stała odpowiedzialna za podążanie wartości lucyferyny za wartością badanej
     * funkcji.
     *
     * @return przelicznik wartości funkcji na lucyferynę
     */
    public double getLuciferinEnhancementConstant(); // gamma

    /**
     * Stała kontrolująca zanik lucyferyny. Odpowiada ona za zapominanie
     * wcześniejszych wartości lucyferyny.
     *
     * @return stała odpowiedzialna za szybkość zaniku lucyferyny
     */
    public double getLuciferinDecayConstant(); // ro

    /**
     * Rozmiar pojedynczego kroku agenta.
     *
     * @return długość kroku
     */
    public double getStepSize(); // s

    /**
     * Maksymalny zasięg sensora.
     *
     * @return ograniczenie na zasięgu sensora.
     */
    public double getMaximalSensorRange(); // r_s

    /**
     * Stała kontrolująca szybkość zmiany zasięgu sensora.
     *
     * @return stała odpowiedzialna za szybkość zaminy zasięgu sensora
     */
    public double getBeta();

    /**
     * Początkowy zasięg sensora
     *
     * @return początkowa wartość zasięgu sensora
     */
    public double getInitialSensorRange();

    /**
     * Stała odpowiedzialna za podział grupy agentów na podgrupy.
     *
     * @return pożądana liczba agentów, z którymi pozostaje się w kontakcie.
     */
    public double getDesiredNumberOfNeighbors();
}