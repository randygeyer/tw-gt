/**
 * 
 */
package com.thoughtworks.problem3.model;

import com.thoughtworks.problem3.model.symbols.NumeralExpression;
import com.thoughtworks.problem3.utils.Validate;


/**
 * An intergalactic numeral translation query, e.g.
 * <p><code>how much is pish tegj glob glob ?</code></p>
 * This query translates an intergalactic numeral expression to
 * an Earth numeric value, using the numeral symbol table from 
 * the {@link QueryContext}.
 * <p>
 * The BNF for this query is:
 * <p><code>
 * numeral_translation_query ::= "how" "much" "is" numeral_expression ['?']
 * </code>
 * 
 * @author rgeyer
 *
 */
public class NumeralTranslationQuery extends Query {
    
    private final NumeralExpression expression;

    public NumeralTranslationQuery(NumeralExpression expression) {
        Validate.notNull(expression, "expression cannot be null");
        
        this.expression = expression;
    }

    /**
     * Computes and prints the numeric value of the numeral expression.
     * 
     * @param context
     */
    @Override
    public void interpret(QueryContext context) {
        final int value = expression.calculateNumericValue();
        final String msg = String.format("%s is %d", expression, value);
        context.print(msg);
    }
    
}
