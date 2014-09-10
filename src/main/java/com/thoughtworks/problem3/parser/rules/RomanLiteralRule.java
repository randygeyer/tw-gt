package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;

public class RomanLiteralRule extends TerminalRule {

    public RomanLiteralRule(TokenBuffer tokens) {
        super(tokens, TokenType.NUMERAL_LITERAL);
    }

}
