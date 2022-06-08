package dev.jonah.cipherdecrypt.fitness;

import java.util.HashMap;

/**
 * Class that compares the actual calculated frequencies to
 * the ones that are expcted (from a FrequencyExpectation object).
 *
 * @author Jonah Tharakan
 */
public class Scorer {

    // --- Fields:

    private FrequencyExpectation expectation;


    // --- Constructors:

    /**
     * Creates a new Scorer with FrequencyExpectation expectation.
     */
    public Scorer(FrequencyExpectation expectation) {
        this.expectation = expectation;
    }


    // --- Methods:

    /**
     * Returns a reference to this Scorer's FrequencyExpectation object.
     */
    public FrequencyExpectation getExpectation() {
        return this.expectation;
    }

    /**
     * Returns the difference between the log expected frequency of quadgram
     * QUADGRAM and the actual log frequency found, given by FREQUENCY.
     *
     * Positive if the frequency is greater than expected,
     * negative if the frequency is less than expected.
     */
    public double logFrequencyDiff(String quadgram, double logFrequency) {
        return logFrequency - expectation.getLogFrequency(quadgram);
    }

    /**
     * Returns the square of the difference between the log expected
     * frequency of quadgram QUADGRAM and the actual log frequency found,
     * given by FREQUENCY.
     */
    public double squaredFrequencyDiff(String quadgram, double logFrequency) {
        double d = logFrequencyDiff(quadgram, logFrequency);
        return d * d;
    }

    /**
     * Scores the entire message, by finding the frequency of each
     * quadgram, taking the log, and comparing to the expected  log
     * frequencies using squaredFreqDiff. Adds all the results.
     */
    public double score(String message) {
        double score = 0.0;
        HashMap<String, Integer> counts = new HashMap<>();
        int total = 0;

        // Populate the hashmap:
        for (int i = 0; i < message.length() - 4; i++) {
            String quadgram = message.substring(i, i+4);
            counts.put(quadgram, counts.getOrDefault(quadgram, 0) + 1);
            total++;
        }

        // For each quadgram, calculate the probability and score it.
        // Add accordingly to score
        for (String quadgram : counts.keySet()) {
            double probability =  (double) counts.get(quadgram) / total;
            double logProb = Math.log(probability);
            score += squaredFrequencyDiff(quadgram, logProb);
        }

        return score;
    }


}
