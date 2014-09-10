package com.thoughtworks.problem3.parser;

import java.util.ArrayList;
import java.util.List;

public class TokenBuffer {

    private final List<Token> tokens = new ArrayList<Token>();
    private int position = 0;
    
    public TokenBuffer() {
        // do nothing
    }
    
    public void reset(List<Token> tokens) {
        position = 0;
        this.tokens.clear();
        this.tokens.addAll(tokens);
    }

    public Token getToken() {
        if (position < tokens.size()) {
            return tokens.get(position);
        }
        return new Token(TokenType.EOF);
    }
    
    public Token lookAhead() {
        int pos = position+1;
        if (pos < tokens.size()) {
            return tokens.get(pos);
        }
        return new Token(TokenType.EOF);
    }
    
    public void nextToken() {
        position++;
    }

}
