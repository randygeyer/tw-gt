/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import java.util.regex.Pattern;

import com.thoughtworks.problem3.utils.StringUtils;
import com.thoughtworks.problem3.utils.Validate;

/**
 * A Roman numeral along with its numeric value and symbol.
 * 
 * @author rgeyer
 *
 */
public enum RomanNumeral {

    I(1, 'I'),
    V(5, 'V'),
    X(10, 'X'),
    L(50, 'L'),
    C(100, 'C'),
    D(500, 'D'),
    M(1000, 'M');

    private final int value;
    private final char symbol;

    private RomanNumeral(int value, char symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    /**
     * @return the Roman numeral numeric value
     */
    public int numericValue() {
        return value;
    }

    /**
     * @return the Roman numeral symbol character 
     */
    public char symbol() {
        return symbol;
    }

    /**
     * Converts the supplied symbol to its Numeral.
     * 
     * @param symbol
     *            character to convert to Numeral
     * @return Numeral, if symbol is valid, otherwise throws an exception
     * @throws IllegalArgumentException
     *             when the supplied symbol is not a valid Roman numeral
     * 
     */
    public static RomanNumeral from(char symbol) {
        for (RomanNumeral numeral : RomanNumeral.values()) {
            if (numeral.symbol == symbol) {
                return numeral;
            }
        }
        throw new IllegalArgumentException(symbol + " is not a valid Roman numeral");
    }
    
    /**
     * Converts the first character of supplied symbol to its Numeral.
     * 
     * @param symbol
     *            character to convert to Numeral
     * @return Numeral, if symbol is valid, otherwise throws an exception
     * @throws IllegalArgumentException
     *             when the supplied symbol is not a valid Roman numeral
     * 
     */
    public static RomanNumeral from(String symbol) {
        Validate.notNull(symbol, "symbol cannot be null");
        return from(symbol.charAt(0));
    }

    public static final String EXPRESSION_REGEX = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    public static final Pattern EXPRESSION_PATTERN = Pattern.compile(EXPRESSION_REGEX);

    /**
     * Returns <code>true</code> if the supplied input is a valid Roman
     * numeral expression.
     * 
     * @param input
     *            Roman numeral expression to validate, e.g. IX or MMXIV. Can be
     *            an empty string, which is the equivalent of zero.
     * @return <code>true</code> if non-null and valid, otherwise
     *         <code>false</code>
     */
    public static boolean isValidExpression(String input) {
        if (input == null)
            return false;
        return EXPRESSION_PATTERN.matcher(input).matches();
    }

    /**
     * Computes the decimal value of the supplied Roman numeral expression, e.g.
     * IX.
     * 
     * @param input
     *            Roman numeral expression to compute
     * @return integer 
     *            value of the supplied expression, if valid. If null or blank, returns 0.
     * @throws IllegalArgumentException
     *             if the input is null or an invalid Roman numeral expression
     */
    public static int computeValue(String input) {
        if (StringUtils.isNullOrBlank(input)) {
            return 0;
        }
        if (!isValidExpression(input)) {
            throw new IllegalArgumentException("Value cannot be computed, input is invalid; input=" + input);
        }

        int totalValue = 0;
        final char[] numerals = input.toCharArray();

        int lastValue = 0;

        // process numerals in reverse order to build value
        for (int i = numerals.length; i > 0; i--) {
            RomanNumeral numeral = from(numerals[i - 1]);
            int numeralValue = numeral.value;
            if (lastValue <= numeralValue) {
                totalValue += numeralValue;
            } else {
                totalValue -= numeralValue;
            }
            lastValue = numeralValue;
        }

        return totalValue;
    }

}
