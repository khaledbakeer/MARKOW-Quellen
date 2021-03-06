package include;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MARKOW-Quellen:
 * ----------------
 * <p>
 * Eine Markow-Quelle ist das Mathematische Modell einer Informationsquelle, bei dem die aufeinanderfolgende Auswahl
 * von Quellenzeichen, d. h. die Folge der Zustände, sowohl von der momentanen Verteilung der Auftritts- bzw.
 * Zustandswahrscheinlichkeiten als auch von der Verteilung der Übergangswahrscheinlichkeiten abhängt.
 */
public class Markow {

    private int _t = 0;
    private int _j;
    private int _i;
    private double[][] _transitionProbability;
    private double[] _stateProbability;
    private List<double[]> resultsTable;

    /**
     * @param _transitionProbability
     * @param StartStateProbability
     */
    public Markow(double[][] _transitionProbability, double[] StartStateProbability) {
        if (_transitionProbability.length != StartStateProbability.length)
            throw new IllegalArgumentException("The start state probability must have the same counts of elements as The transition probability" +
                    "\n The start state probability has " + StartStateProbability.length + " elements" +
                    "\n and the transition probability has " + _transitionProbability.length + " elements");

        if (_transitionProbability.length == 0)
            throw new IllegalArgumentException("The Transition Probability must have at least one element.");
        else this._j = _transitionProbability.length;

        if (_transitionProbability[0].length == 0)
            throw new IllegalArgumentException("The Transition Probability must have at least one element.");
        else this._i = _transitionProbability[0].length;

        if (_transitionProbability == null) throw new NullPointerException("The Transition Probability cannot be null");
        else this._transitionProbability = _transitionProbability;

        if (StartStateProbability == null) throw new NullPointerException("The Start State Probability cannot be null");
        this._stateProbability = StartStateProbability;

        double sum = 0;
        for (int num = 0; num < StartStateProbability.length; num++) {
            sum = round(sum + StartStateProbability[num], 5);
        }
        if (sum != 1) throw new IllegalArgumentException("The sum of Start State Probability elements must equal 1");

        for (int i = 0; i < _transitionProbability.length; i++) {
            sum = 0;
            for (int j = 0; j < _transitionProbability[i].length; j++) {
                sum = round(sum + _transitionProbability[i][j], 5);
            }
            if (sum != 1)
                throw new IllegalArgumentException("The sum of every row in Transition Probability array must equal 1");
        }

        resultsTable = new ArrayList<>();

        double[] tmp = new double[_i];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = StartStateProbability[i];
        }
        resultsTable.add(tmp);
    }

    /**
     *
     */
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

    /**
     * @param t
     */
    public void PrintDetailedStateProbability(int t) {
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

    /**
     *
     */
    public void WhenChangedStateProbability() {
        System.out.println("---------------------------------------------------------------------------------");
        for (int i = 1; i < resultsTable.size(); i++) {
            for (int j = 0; j < resultsTable.get(i).length; j++) {
                if (round(resultsTable.get(i - 1)[j], 2) == round(resultsTable.get(i)[j], 2)) {
                    if (j == resultsTable.get(i).length - 1) {
                        System.out.print("From the t = " + (i) + ". The State Probability ");
                        System.out.print("( ");
                        Arrays.stream(resultsTable.get(i + 1)).forEach(x -> System.out.print(round(x, 2) + " "));
                        System.out.print(") ");
                        System.out.print("will not change anymore!");
                        System.out.println();
                        System.out.println("---------------------------------------------------------------------------------");
                        return;
                    }
                }
            }
        }

    }

    /**
     *
     */
    public void PrintStateProbabilityTable() {
        System.out.println();
        System.out.println("Table of all State Probabilities to find the Steady State: ");
        System.out.println("-----------------------------------------------------------");
        for (int j = 0; j < resultsTable.get(0).length; j++) {
            System.out.print("P" + (j + 1) + " \t ");
            for (int i = 0; i < resultsTable.size(); i++) {
                System.out.print(round(resultsTable.get(i)[j], 2) + " \t ");
            }
            System.out.println();
        }
    }

    /**
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * @return
     */
    public double[][] get_transitionProbability() {
        return _transitionProbability;
    }
}
