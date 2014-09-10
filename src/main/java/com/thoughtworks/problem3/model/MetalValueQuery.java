/**
 * 
 */
package com.thoughtworks.problem3.model;

import java.math.BigDecimal;

import com.thoughtworks.problem3.model.symbols.MetalSymbol;
import com.thoughtworks.problem3.model.symbols.NumeralExpression;
import com.thoughtworks.problem3.utils.Validate;


/**
 * An intergalactic metal value query, e.g.
 * <p><code>how many Credits is glob prok Silver ?</code></p>
 * This query computes the value in galactic Credits for the supplied amount of metal.
 * <p>
 * The BNF for this query is:
 * <p><code>
 * numeral_translation_query ::= "how" "many" "Credits" "is" numeral_expression metal_identifier ['?']
 * </code>
 * 
 * @author rgeyer
 *
 */
public class MetalValueQuery extends Query {
    
    private final String metalName;
    private final NumeralExpression expression;

    public MetalValueQuery(String metalName, NumeralExpression expression) {
        Validate.notBlank(metalName, "metalName cannot be null or empty");
        Validate.notNull(expression, "expression cannot be null");
        
        this.metalName = metalName;
        this.expression = expression;
    }

    /**
     * Computes and prints the Credits value for the supplied amount of metal.
     * 
     * @param context
     */
    @Override
    public void interpret(QueryContext context) {
        final int amount = expression.calculateNumericValue();
        final BigDecimal credits = calculateValue(context, amount);
        final String msg = String.format("%s %s is %.0f Credits", expression, metalName, credits);
        context.print(msg);
    }
    
    BigDecimal calculateValue(QueryContext context, int amount) {
        final MetalSymbol metal = context.lookup(metalName);
        return metal.getUnitPrice().multiply(new BigDecimal(amount));
    }
    
}
