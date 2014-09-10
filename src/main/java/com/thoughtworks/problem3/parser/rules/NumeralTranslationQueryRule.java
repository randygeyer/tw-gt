package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.NumeralTranslationQuery;
import com.thoughtworks.problem3.model.symbols.NumeralExpression;
import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;

public class NumeralTranslationQueryRule extends TopLevelRule {

    private static final String REGEX = "^how\\s+much\\s+.*";

    private final NumeralExpressionRule numeralExpression;

    public NumeralTranslationQueryRule(TokenBuffer tokens) {
        super(tokens, REGEX);

        numeralExpression = new NumeralExpressionRule(tokens);
    }

    @Override
    public boolean parse(QueryContext context) {
        super.validateAndConsumeToken(TokenType.HOW_KEYWORD);
        super.validateAndConsumeToken(TokenType.MUCH_KEYWORD);
        super.validateAndConsumeToken(TokenType.IS_KEYWORD);
        
        final NumeralExpression expr = new NumeralExpression(context.getNumerals());
        numeralExpression.parse(context, expr);
        
        super.validateAndConsumeToken(TokenType.QUESTION_MARK, true);
        super.validateEOF();

        final NumeralTranslationQuery query = new NumeralTranslationQuery(expr);
        query.interpret(context);

        return true;
    }

}
