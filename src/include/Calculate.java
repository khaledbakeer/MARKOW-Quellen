package include;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculate {

    private int _t = 0;
    private int _j;
    private int _i;
    private double[][] _transitionProbability;
    private double[] _stateProbability;

    public List<double[]> resultsTable;

    public Calculate(double[][] _transitionProbability, double[] StartStateProbability) {
        this._j = _transitionProbability.length;
        this._i = _transitionProbability[0].length;
        this._transitionProbability = _transitionProbability;
        this._stateProbability = StartStateProbability;
        resultsTable = new ArrayList<>();

        double[] tmp = new double[_i];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = StartStateProbability[i];
        }
        resultsTable.add(tmp);
    }

    public void PrintTransitionProbability() {
        System.out.println("Transition Probability: ");
        System.out.println("-----------------------");
        for (int a = 0; a < get_transitionProbability().length; a++) {
            for (int b = 0; b < get_transitionProbability()[a].length; b++) {
                System.out.print(round(get_transitionProbability()[a][b], 2) + " \t ");
            }
            System.out.println();
        }
    }

    public void PrintStateProbability(int t) {
        for (int h = 0; h < t + 1; h++) {
            System.out.print("\nState Probability for ");
            System.out.print("t = " + _t + " -> " + "( ");
            Arrays.stream(resultsTable.get(h)).forEach(x -> System.out.print(round(x, 2) + " "));
            System.out.println(")");
            System.out.println("------------------------------------------------");
            double[] tmp = new double[_i];
            for (int a = 0; a < get_transitionProbability().length; a++) {
                double x = 0;
                for (int b = 0; b < get_transitionProbability()[a].length; b++) {
                    System.out.print("(" + round(resultsTable.get(h)[b], 2) + "*" + round(get_transitionProbability()[b][a], 2) + ")");
                    if (b == get_transitionProbability()[a].length - 1) System.out.print(" = ");
                    else System.out.print(" + ");
                    x = x + (resultsTable.get(h)[b] * get_transitionProbability()[b][a]);
                }
                tmp[a] = x;
                System.out.println(round(x, 2));
                System.out.println();
            }

            resultsTable.add(tmp);
            ++_t;
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public double[][] get_transitionProbability() {
        return _transitionProbability;
    }
}
