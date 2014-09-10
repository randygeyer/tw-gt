/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.problem3.utils.Validate;

/**
 * An intergalactic symbol expression consisting of one or more {@link NumeralSymbol} names.
 * 
 * @author rgeyer
 *
 */
public class NumeralExpression {

    private final NumeralSymbolTable numeralTable;
    private final List<String> numerals = new ArrayList<String>();
    
    public NumeralExpression(NumeralSymbolTable numeralTable) {
        Validate.notNull(numeralTable, "numeralTable cannot be null");
        this.numeralTable = numeralTable;
    }
    
    /**
     * Adds the supplied symbol name to this expression.
     * 
     * @param numeral name of the symbol to add
     * @return this SymbolExpression
     */
    public NumeralExpression add(String numeral) {
        Validate.notBlank(numeral, "numeral cannot be null or empty");
        numerals.add(numeral);
        return this;
    }
    
    /**
     * @return the current count of symbols in this expression.
     */
    public int getCount() {
        return numerals.size();
    }

    /**
     * Calculates the numeric value for this numeral expression by:
     * 1. looking up the numeral symbols in the symbol table
     * 2. building a roman numeral expression from the associated Roman numerals
     * 3. computing the roman numeral expression value
     * 
     * @return if this expression is empty, returns zero; otherwise, the calculated numeric value   
     */
    public int calculateNumericValue() {
        if (numerals.size() == 0) {
            return 0;
        }
        
        final StringBuilder numeralExpression = new StringBuilder(numerals.size());
        
        for (String num : numerals) {
            // 1. lookup symbol from table, throw if not found.
            final NumeralSymbol numeral = numeralTable.lookup(num);
            if (numeral == null) {
                throw new RuntimeException("Numeral not defined; symbol=" + num);
            }
            // 2. add to Roman numeral expression
            numeralExpression.append("" + numeral.getRomanNumeral().symbol());
        }
        
        // 3. compute value from symbols' associated roman numerals
        return RomanNumeral.computeValue(numeralExpression.toString());
    }
    
    @Override
    public String toString() {
        if (numerals.size() == 0) {
            return "<empty>";
        }
        
        final StringBuilder sb = new StringBuilder();
        for (String numeral : numerals) {
            sb.append(" " + numeral);
        }
        return sb.substring(1);
    }
}
