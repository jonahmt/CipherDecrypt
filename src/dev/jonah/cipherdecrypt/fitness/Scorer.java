package dev.jonah.cipherdecrypt.fitness;

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


}
