/**
 * 
 */
package com.thoughtworks.problem3.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Tokenizer an input line (statement).
 * 
 * @author rgeyer
 *
 */
public class Lexer {
    
    public List<Token> lex(String input) {
        
        final ArrayList<Token> tokens = new ArrayList<Token>();
        
        final String[] rawTokens = input.split("\\s+");
        for (String rawToken : rawTokens) {
            boolean found = false;
            for (TokenType tokenType : TokenType.values()) {
                if (tokenType.matches(rawToken)) {
                    found = true;
                    if (tokenType.outputToken()) {
                        tokens.add(new Token(tokenType, rawToken));
                    }
                    break;
                }
            }
            if (!found) {
                throw new ParseException("Unknown token encountered; token=" + rawToken);
            }
        }
        
        return tokens;
    }
    
}
