package interfaces;

import java.util.List;

public interface OptimizationProblem {
    /**
     * Przedział liczbowy.
     */
    public interface Interval {
        /**
         * Wartość minimalna przedziału liczbowego
         *
         * @return wartość minimalna
         */
        public double getMin();

        /**
         * Wartość maksymalna przedziału liczbowego
         *
         * @return wartość maksymalna
         */
        public double getMax();
    }

    /**
     * Liczba wymiarów
     *
     * @return liczba wymiarów
     */
    public int getNumberOfDimensions();

    /**
     * Obszar, w którym poszukowane jest rozwiząznie. Jest nim hipersześcian.
     *
     * @return przeszukiwany obszar
     */
    public List<Interval> getSearchArea();

    /**
     * Wartość funkcji w N-wymiarowym punkcie position. W przypadku błędnego
     * wywołania (błędna liczba wymiarów, punkt spoza obszaru przeszukowania wartość
     * funkcji może być dowolna).
     *
     * @param position położenie, w którym wyliczana jest funkcja
     * @return wartość funkcji.
     */
    public double getValue(List<Double> position);
}