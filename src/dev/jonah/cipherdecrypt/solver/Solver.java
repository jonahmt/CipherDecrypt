package dev.jonah.cipherdecrypt.solver;

import dev.jonah.cipherdecrypt.fitness.Scorer;

/**
 * The class that solves the cipher. Does this with an
 * Encryptor and a Scorer. Repeatedly changes the cipher and scores
 * until a certain threshold is reached.
 */
public class Solver {

    // --- Constants:

    /** The number of cipher modifications with no score increase
     *  that prompts a random new cipher.
     */
    private final static int LOCAL_MAX_TIME = (int) 500;
    /** The number of total cipher modifications with no score increase
     *  that prompts the current best to be declared as the true cipher.
     */
    private final static int GLOBAL_MAX_TIME = (int) 1e5;

    // --- Fields:

    /** The scorer used to score the substitutions. */
    private Scorer scorer;
    /** The encryptor used to do the substitutions. */
    private Encryptor encryptor;
    /** The best cipher (highest score) tried so far. */
    private String bestSoFar;
    /** The best score of the best cipher tried so far. */
    private double bestScore;


    // --- Constructors:

    /** Creates a new Solver object. */
    public Solver() {
        encryptor = new Encryptor();
        scorer = new Scorer();

        bestSoFar = encryptor.getCipher();
        bestScore = 0.0;
    }

    /**
     * Solves the encrypted message. Returns the solved, true message.
     */
    public String solve(String message) {
        int globalCheck = 0;
        while (globalCheck < GLOBAL_MAX_TIME) {

            int localCheck = 0;
            while (localCheck < LOCAL_MAX_TIME) {
                encryptor.modifyCipher();
                double score = scorer.score(encryptor.encryptMessage(message));
                if (score > bestScore) {
                    encryptor.setCipher();
                    bestSoFar = encryptor.getCipher();
                    bestScore = score;
                    localCheck = 0;
                    globalCheck = 0;

                    System.out.println(bestSoFar);
                    System.out.println(bestScore);
                } else {
                    localCheck++;
                    globalCheck++;
                }
            }

            encryptor.newCipher();
            System.out.println(globalCheck);

        }

        return encryptor.encryptMessage(message);
    }

    public String getCurrentCipher() {
        return encryptor.getCipher();
    }

}
