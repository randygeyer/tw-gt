package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.MetalValueQuery;
import com.thoughtworks.problem3.model.symbols.NumeralExpression;
import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;

/**
 * The BNF for this query is:
 * <p><code>
 * numeral_translation_query ::= "how" "many" "Credits" "is" numeral_expression metal_identifier ['?']
 * </code>
 * 
 * @author rgeyer
 *
 */
public class MetalValueQueryRule extends TopLevelRule {

    private static final String REGEX = "^how\\s+many\\s+.*";

    private final NumeralExpressionRule numeralExpression;
    private final MetalIdentifierRule metalIdentifier;

    public MetalValueQueryRule(TokenBuffer tokens) {
        super(tokens, REGEX);

        numeralExpression = new NumeralExpressionRule(tokens);
        metalIdentifier = new MetalIdentifierRule(tokens);
    }

    @Override
    public boolean parse(QueryContext context) {
        super.validateAndConsumeToken(TokenType.HOW_KEYWORD);
        super.validateAndConsumeToken(TokenType.MANY_KEYWORD);
        super.validateAndConsumeToken(TokenType.CREDITS_KEYWORD);
        super.validateAndConsumeToken(TokenType.IS_KEYWORD);
        
        final NumeralExpression expr = new NumeralExpression(context.getNumerals());
        numeralExpression.parse(context, expr);
        
        final String metalName = super.parseIdentifier(context, metalIdentifier);
        super.validateAndConsumeToken(TokenType.QUESTION_MARK, true);
        super.validateEOF();

        final MetalValueQuery query = new MetalValueQuery(metalName, expr);
        query.interpret(context);
        return true;
    }

}
