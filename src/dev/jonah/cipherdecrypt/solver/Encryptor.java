package dev.jonah.cipherdecrypt.solver;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Class that encrypts a message using a substitution cipher.
 * The cipher can be changed while using it.
 *
 * @author Jonah Tharakan
 */
public class Encryptor {

    // --- Fields:

    /** Random object. */
    private Random random;
    /** The cipher of the solver. */
    private String cipher;
    /** The cipher we are looking at currently. Not the actual cipher until
     *  setCipher gets called.
     */
    private String lookingAt;


    // --- Constructors:

    /**
     * Creates a new Encryptor object with default (identity) cipher.
     */
    public Encryptor() {
        random = new Random();

        cipher = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lookingAt = "QWERTYUIOPASDFGHJKLZXCVBNM";
    }

    // --- Methods:

    /**
     * Modifies the lookingAt cipher by randomly swapping two of its letters.
     */
    public void modifyCipher() {
        int idx1 = random.nextInt(26);
        int idx2 = random.nextInt(25);
        if (idx2 >= idx1) {
            idx2++; // ensures idx2 never equals idx1
        }
        StringBuilder swapped = new StringBuilder(cipher);
        swapped.setCharAt(idx1, cipher.charAt(idx2));
        swapped.setCharAt(idx2, cipher.charAt(idx1));

        String r = swapped.toString();
        lookingAt = r;
    }

    /**
     * Modifies the lookingAt cipher by creating a completely new one.
     * Used to get out of a local maximum.
     */
    public void newCipher() {
        StringBuilder result = new StringBuilder();
        while (cipher.length() != 0) {
            int idx = random.nextInt(cipher.length());
            result.append(cipher.charAt(idx));
            cipher = cipher.substring(0, idx) + cipher.substring(idx + 1);
        }

        String r = result.toString();
        cipher = r;
        lookingAt = cipher;
    }


    /**
     * Encrypts a single character using the cipher.
     * Returns '*' if the given character is not a letter
     */
    private char encryptLetter(char c) {
        if (!Character.isLetter(c)) {
            return '*';
        }
        c = Character.toUpperCase(c);
        return cipher.charAt(c - 65);
    }

    /**
     * Encrypts the whole message using the cipher.
     */
    public String encryptMessage(String m) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            result.append(encryptLetter(m.charAt(i)));
        }
        return result.toString();
    }

    /** "Locks in" the cipher to a modification we looked at. Used when
     *  we find a cipher with a higher score.
     */
    public void setCipher() {
        cipher = lookingAt;
    }

    public String getCipher() {
        return cipher;
    }
}
