package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.parser.ParseException;
import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;
import com.thoughtworks.problem3.utils.Validate;

public class TerminalRule extends Rule {
    
    protected final TokenType tokenType;

    public TerminalRule(TokenBuffer tokens, TokenType tokenType) {
        super(tokens);
        Validate.notNull(tokenType, "tokenType cannot be null");
        this.tokenType = tokenType;
    }

    @Override
    public boolean parse(QueryContext context) {
        final boolean isMatch = getToken().isTokenType(tokenType);
        if (!isMatch) {
            throw createSyntaxException();
        }
        return isMatch;
    }
    
    protected ParseException createSyntaxException() {
        return super.createSyntaxException(tokenType);
    }

}
