import include.Calculate;

public class AppRunner {

    public static void main(String[] args) {

        double[] stateProbability = {1, 0, 0};

        double[][] transitionProbability =
                {
                        {0.0, 0.2, 0.8},
                        {0.1, 0.9, 0.0},
                        {0.2, 0.4, 0.4}
                };

        Calculate c = new Calculate(transitionProbability, stateProbability);
        c.PrintTransitionProbability();

        System.out.println();

        c.PrintDetailedStateProbability(20);

        c.PrintStateProbabilityTable();

        c.WhenChangedStateProbability();
    }

}
