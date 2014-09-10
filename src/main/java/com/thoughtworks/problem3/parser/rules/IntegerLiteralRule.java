package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;

public class IntegerLiteralRule extends TerminalRule {

    public IntegerLiteralRule(TokenBuffer tokens) {
        super(tokens, TokenType.INTEGER_LITERAL);
    }

}
