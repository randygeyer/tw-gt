package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;

public class NumeralIdentifierRule extends TerminalRule {

    public NumeralIdentifierRule(TokenBuffer tokens) {
        super(tokens, TokenType.NUMERAL_IDENTIFIER);
    }

}
