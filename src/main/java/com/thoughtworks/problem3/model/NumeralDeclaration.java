/**
 * 
 */
package com.thoughtworks.problem3.model;

import com.thoughtworks.problem3.model.symbols.NumeralSymbol;
import com.thoughtworks.problem3.model.symbols.RomanNumeral;
import com.thoughtworks.problem3.utils.Validate;


/**
 * An intergalactic numeral declaration query, e.g.
 * <p><code>glob is I</code></p>
 * This declaration creates a {@link NumeralSymbol} with its associated 
 * Roman numeral symbol and adds it the {@link QueryContext}.
 * <p>
 * The BNF for this declaration is:
 * <p><code>
 * numeral_declaration ::= numeral_identifier "is" roman_literal<br/>
 * numeral_identifier ::= "a..z" { "a..z" }
 * roman_literal ::= 'I' | 'V' | 'X' | 'L' | 'C' | 'D' | 'M'
 * </code>
 * 
 * @author rgeyer
 *
 */
public class NumeralDeclaration extends Query {
    
    private final NumeralSymbol symbol;

    public NumeralDeclaration(String symbolName, RomanNumeral numeral) {
        Validate.notNull(numeral, "numeral cannot be null or empty");
        
        this.symbol = new NumeralSymbol(symbolName, numeral);
    }

    /**
     * Adds this command's symbol to the context.
     * 
     * @param context
     */
    @Override
    public void interpret(QueryContext context) {
        context.addNumeral(symbol);
    }

}
