import include.Markow;

public class AppRunner {

    public static void main(String[] args) {

        double[] stateProbability = {0.25, 0.25, 0.25, 0.25};

        double[][] transitionProbability =
                {
                        {0.0, 0.2, 0.7, 0.1},
                        {0.1, 0.6, 0.0, 0.3},
                        {0.2, 0.4, 0.2, 0.2},
                        {0.2, 0.3, 0.1, 0.4}
                };

        Markow c = new Markow(transitionProbability, stateProbability);
        c.PrintTransitionProbability();

        System.out.println();

        c.PrintDetailedStateProbability(20);

        c.PrintStateProbabilityTable();

        c.WhenChangedStateProbability();
    }

}
