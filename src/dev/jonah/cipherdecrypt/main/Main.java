package dev.jonah.cipherdecrypt.main;

import dev.jonah.cipherdecrypt.fitness.FrequencyExpectation;
import dev.jonah.cipherdecrypt.fitness.Scorer;
import dev.jonah.cipherdecrypt.solver.Solver;

/** Contains the main method. Handles input.
 *
 * @author Jonah Tharakan
 */
public class Main {

    public static void main(String[] args) {

        Solver solver = new Solver();

        String message = "TIONTIONTION";

        System.out.println(solver.solve(message));

    }

}
