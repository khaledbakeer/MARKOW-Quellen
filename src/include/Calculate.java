package include;

public class Calculate {

    private int _t = 0;
    private int _j;
    private int _i;
    private double[][] _transitionProbability;
    private double[] _stateProbability;


    public void PrintTransitionProbability() {
        System.out.println("Transition Probability: ");
        System.out.println("-----------------------");
        for (int a = 0; a < get_transitionProbability().length; a++) {
            for (int b = 0; b < get_transitionProbability()[a].length; b++) {
                System.out.print(get_transitionProbability()[a][b] + " \t ");
            }
            System.out.println();
        }
    }

    public void PrintStateProbability() {
        for (int a = 0; a < get_transitionProbability().length; a++) {
            double x = 0;
            for (int b = 0; b < get_transitionProbability()[a].length; b++) {
                System.out.print("(" + get_stateProbability()[b] + "*" + get_transitionProbability()[b][a] + ")");
                if (b == get_transitionProbability()[a].length - 1) System.out.print(" = ");
                else System.out.print(" + ");
                x = x + (get_stateProbability()[b] * get_transitionProbability()[b][a]);
            }
            System.out.println(x);
            System.out.println();
        }
    }

    public double[][] get_transitionProbability() {
        return _transitionProbability;
    }

    public void set_transitionProbability(double[][] _transitionProbability) {
        this._transitionProbability = _transitionProbability;
    }

    public double[] get_stateProbability() {
        return _stateProbability;
    }

    public void set_StartStateProbability(double[] _StartStateProbability) {
        this._stateProbability = _StartStateProbability;
    }
}
