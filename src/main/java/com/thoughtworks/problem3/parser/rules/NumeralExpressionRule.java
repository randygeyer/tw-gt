package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.symbols.NumeralExpression;
import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;

public class NumeralExpressionRule extends TerminalRule {

    public NumeralExpressionRule(TokenBuffer tokens) {
        super(tokens, TokenType.NUMERAL_IDENTIFIER);
    }

    public boolean parse(QueryContext context, NumeralExpression expression){
        while (getToken().isTokenType(super.tokenType)) {
            expression.add(getToken().getText());
            super.nextToken();
        }
        if (expression.getCount() > 0) {
            return true;
        }
        throw super.createSyntaxException();
    }

}
