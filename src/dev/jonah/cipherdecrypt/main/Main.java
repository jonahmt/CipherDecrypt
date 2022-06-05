package dev.jonah.cipherdecrypt.main;

import dev.jonah.cipherdecrypt.fitness.FrequencyExpectation;

/** Contains the main method. Handles input.
 *
 * @author Jonah Tharakan
 */
public class Main {

    public static void main(String[] args) {

        FrequencyExpectation baseline = new FrequencyExpectation();

        System.out.println(baseline.getTotalCount());
        System.out.println();

        String[] list = {"TION", "THER", "WHAT", "WKIF", "ZZZZ"};
        for (String str : list) {
            System.out.println(str);
            System.out.println(baseline.getCount(str));
            System.out.println(baseline.getFrequency(str));
            System.out.println(baseline.getLogFrequency(str));
            System.out.println();
        }

    }

}
