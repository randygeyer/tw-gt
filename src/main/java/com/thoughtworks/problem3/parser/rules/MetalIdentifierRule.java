package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;

public class MetalIdentifierRule extends TerminalRule {

    public MetalIdentifierRule(TokenBuffer tokens) {
        super(tokens, TokenType.METAL_IDENTIFIER);
    }

}
