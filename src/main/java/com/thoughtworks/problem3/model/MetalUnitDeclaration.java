/**
 * 
 */
package com.thoughtworks.problem3.model;

import java.math.BigDecimal;

import com.thoughtworks.problem3.model.symbols.MetalSymbol;
import com.thoughtworks.problem3.model.symbols.NumeralExpression;


/**
 * An intergalactic metal declaration query, e.g.
 * <p><code>glob glob Silver is 34 Credits</code>
 * <p>
 * This declaration creates a {@link MetalSymbol} with a unit value computed from
 * the supplied amount expression and value, and adds it the symbol table in 
 * the {@link QueryContext}.
 * <p>
 * The BNF for this declaration is:
 * <p><code>
 * metal_declaration ::= numeral_expression metal_identifier "is" integer_literal "Credits"
 * numeral_expression ::= numeral_identifier { numeral_identifier }
 * metal_identifier ::= "A..Z" { "a..z" }
 * integer_literal ::= "1..9" { "0..9" }
 * </code>
 * 
 * @author rgeyer
 *
 */
public class MetalUnitDeclaration extends Query {
    
    private final String metalName;
    private final NumeralExpression symbolExpression;
    private final BigDecimal value;

    public MetalUnitDeclaration(String metalName, NumeralExpression symbolExpression, int value) {
        super();
        this.metalName = metalName;
        this.symbolExpression = symbolExpression;
        this.value = new BigDecimal(value);
    }

    /**
     * Computes the metal unit value and adds the metal to the context symbol table.
     * 
     * @param context
     */
    @Override
    public void interpret(QueryContext context) {
        final int amount = symbolExpression.calculateNumericValue();
        final BigDecimal unitValue = value.divide(new BigDecimal(amount));
        final MetalSymbol metal = new MetalSymbol(metalName, unitValue);
        context.addMetal(metal);
    }


}
