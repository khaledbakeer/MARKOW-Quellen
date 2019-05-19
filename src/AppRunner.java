import include.Markow;

public class AppRunner {

    public static void main(String[] args) {

        double[] stateProbability = {0.3, 0.2, 0.25, 0.2, 0.05};

        double[][] transitionProbability =
                {
                        {0.0, 0.2, 0.5, 0.1, 0.2},
                        {0.1, 0.4, 0.0, 0.3, 0.2},
                        {0.2, 0.2, 0.2, 0.2, 0.2},
                        {0.2, 0.3, 0.1, 0.3, 0.1},
                        {0.2, 0.3, 0.1, 0.1, 0.3}
                };

        Markow c = new Markow(transitionProbability, stateProbability);
        c.PrintTransitionProbability();

        System.out.println();

        c.PrintDetailedStateProbability(20);

        c.PrintStateProbabilityTable();

        c.WhenChangedStateProbability();
    }

}
