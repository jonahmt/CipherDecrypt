package dev.jonah.cipherdecrypt.fitness;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Scanner;

/** Class that contains the expected (target) frequencies
 *  of quadgrams, based on their actual frequencies in the english
 *  language. Setup is done by reading from file "english_quadgrams.txt".
 *
 * @author Jonah Tharakan
 */
public class FrequencyExpectation {

    // --- Fields:

    /** The hash map which contains the actual counts for each quadgram. */
    private HashMap<String, BigInteger> frequencies;
    /** The total count of all quadgrams in the file. */
    private BigInteger totalCount;


    // --- Constructors:

    /**
     * Creates a new FrequencyExpectation object using the "english_quadrams.txt" file.
     */
    public FrequencyExpectation() {
        totalCount = BigInteger.ZERO;
        frequencies = new HashMap<>();
        parseFile();
    }

    private void parseFile() {
        File file = new File("res/english_quadgrams.txt");
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                String quadgram = line[0];
                BigInteger count = new BigInteger(line[1]);
                totalCount = totalCount.add(count);
                frequencies.put(quadgram, count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // --- Methods:

    /**
     * Returns the total count of the quadgrams from the file.
     */
    public BigInteger getTotalCount() {
        return totalCount;
    }

    /**
     * Returns the actual count of the specified quadgram.
     * Quadgrams which have count less than 101 are treated as having
     * a count of 1.
     */
    public BigInteger getCount(String quadgram) {
        BigInteger result = frequencies.get(quadgram);
        if (result == null || result.equals(BigInteger.ZERO)) {
            result = BigInteger.ONE;
        }
        return result;
    }

    /** Returns the relative frequency (probability) of
     *  the specified quadgram.
     */
    public double getFrequency(String quadgram) {
        BigDecimal count = new BigDecimal(totalCount);
        BigDecimal val = new BigDecimal(getCount(quadgram));
        BigDecimal probability = val.divide(count, 50, RoundingMode.FLOOR);
        return probability.doubleValue();
    }

    /** Returns the log of the relative frequency (probability) of
     *  the specified quadgram.
     */
    public double getLogFrequency(String quadgram) {
        return Math.log(getFrequency(quadgram));
    }


}
