package include;

public class IKT {

    double[] ProbabilityVektor;
    private static IKT ikt = new IKT();

    public static boolean isValidProbabilityVektor(double[] ProbabilityVektor) {
        double sum = 0;
        for (double x : ProbabilityVektor) {
            sum = sum + ikt.round(x, 2);
        }
        if (sum == 1) return true;
        return false;
    }

    /**
     * @param value
     * @param places
     * @return
     */
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
