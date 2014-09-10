/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;


/**
 * Represents an intergalactic numeric symbol with an equivalent roman numeral.
 * 
 * @author rgeyer
 *
 */
public class NumeralSymbol extends SymbolObject {
    
    private final RomanNumeral numeral;
    
    public NumeralSymbol(String name, RomanNumeral numeral) {
        super(name);
        this.numeral = numeral;
    }
    
    /**
     * @return the equivalent Roman numeral
     */
    public RomanNumeral getRomanNumeral() {
        return numeral;
    }

    /**
     * @return the equivalent earth value
     */
    public int getValue() {
        return numeral.numericValue();
    }
    
    @Override
    public String toString() {
        return String.format("[name=%s; numeral=%s; value=%d]", name, numeral, getValue());
    }
}
