/**
 * 
 */
package com.thoughtworks.problem3.utils;

/**
 * Miscellaneous string utilities.
 * 
 * @author rgeyer
 *
 */
public class StringUtils {

    private StringUtils() {
        // static class
    }

    /**
     * @return <code>true</code> if input is null or empty string.
     */
    public static boolean isNullOrEmpty(String input) {
        return (input == null || input.length() == 0);
    }

    /**
     * @return <code>true</code> if input is null, empty or blank string (" ").
     */
    public static boolean isNullOrBlank(String input) {
        return (input == null || input.trim().length() == 0);
    }

    /**
     * Reverses the characters of the supplied string, e.g. dog => god.
     * 
     * @param input
     *            string to reverse
     * @return if input is null or empty, input is returned; otherwise the
     *         reversed string.
     */
    public static String reverse(String input) {
        if (isNullOrBlank(input))
            return input;

        final StringBuilder sb = new StringBuilder(input.length());
        final char[] chars = input.toCharArray();
        for (int i = chars.length; i > 0; i--) {
            sb.append(chars[i-1]);
        }
        return sb.toString();
    }

}
