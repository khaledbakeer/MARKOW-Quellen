import include.Markow;

public class AppRunner {

    public static void main(String[] args) {

        double[] stateProbability = {0.5, 0.5};

        double[][] transitionProbability =
                {
                        {0.5, 0.5},
                        {0.5, 0.5}
                };

        Markow c = new Markow(transitionProbability, stateProbability);
        c.PrintTransitionProbability();

        System.out.println();

        c.PrintDetailedStateProbability(20);

        c.PrintStateProbabilityTable();

        c.WhenChangedStateProbability();
    }

}
