/**
 * 
 */
package com.thoughtworks.problem3.parser;

import com.thoughtworks.problem3.utils.Validate;

/**
 * Token produced by {@link Lexer}.
 * 
 * @author rgeyer
 *
 */
public class Token {
    
    // Standard Keyword tokens for testing convenience
    final static Token CREDITS_KEYWORD = new Token(TokenType.CREDITS_KEYWORD, "Credits");
    final static Token HOW_KEYWORD = new Token(TokenType.HOW_KEYWORD, "how");
    final static Token IS_KEYWORD = new Token(TokenType.IS_KEYWORD, "is");
    final static Token MANY_KEYWORD = new Token(TokenType.MANY_KEYWORD, "many");
    final static Token MUCH_KEYWORD = new Token(TokenType.MUCH_KEYWORD, "much");
    final static Token QUESTION_MARK = new Token(TokenType.QUESTION_MARK, "?");
    
    private final TokenType type;
    private final String text;
    
    /**
     * @param type type of token
     */
    public Token(TokenType type) {
        this(type, null);
    }
    
    /**
     * @param type type of token
     * @param text optional text associated with this token
     */
    public Token(TokenType type, String text) {
        Validate.notNull(type, "type cannot be null");
        
        this.type = type;
        this.text = text;
    }
    
    /**
     * @return the type
     */
    public TokenType getType() {
        return type;
    }
    /**
     * @return the text if any, otherwise <code>null</code>
     */
    public String getText() {
        return text;
    }
    
    /**
     * Convenience method to check if this token type matches the supplied type.
     * 
     * @param tokenType type to match
     * @return <code>true</code> if a match; otherwise <code>false</code>
     */
    public boolean isTokenType(TokenType tokenType) {
        Validate.notNull(tokenType, "tokenType cannot be null");
        return type.equals(tokenType);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Token)) {
            return false;
        }
        Token other = (Token) obj;
        if (text == null) {
            if (other.text != null) {
                return false;
            }
        } else if (!text.equals(other.text)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final String d = (text == null ? "" : String.format("; text=%s", text));
        return String.format("[type=%s%s]", type, d);
    }

}
