/**
 * 
 */
package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.parser.ParseException;
import com.thoughtworks.problem3.parser.Token;
import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;
import com.thoughtworks.problem3.utils.Validate;

/**
 * Base class for grammar rule objects for parsing.
 *  
 * @author rgeyer
 *
 */
public abstract class Rule {
    
    protected final TokenBuffer tokens;
    
    protected Rule(TokenBuffer tokens) {
        Validate.notNull(tokens, "tokens cannot be null");
        this.tokens = tokens;
    }
    
    public abstract boolean parse(QueryContext context);
    
    protected Token getToken() {
        return tokens.getToken();
    }
    
    /**
     * Advances the token buffer to the next token.
     */
    protected void nextToken() {
        tokens.nextToken();
    }
    
    /**
     * Parses the current token using the supplied rule, advancing the token
     * and returning the token text.
     * 
     * @param rule to use to parse
     * @return the current token text before advancing
     */
    protected String parseIdentifier(QueryContext context, Rule rule) {
        rule.parse(context);
        final String identifier = getToken().getText();
        nextToken();
        return identifier;
    }
    
    /**
     * Parses the current token using the supplied rule, advancing the token
     * and returning the token text as an integer.
     * 
     * @param rule to use to parse
     * @return the current token text as an integer before advancing
     */
    protected int parseInteger(QueryContext context, Rule rule) {
        rule.parse(context);
        final Integer integer = Integer.valueOf(getToken().getText());
        nextToken();
        return integer.intValue();
    }
    
    /**
     * Validates the current token against the supplied token type.  If valid,
     * advances token.  If invalid, throws ParseException.  Use for meaningless
     * keyword or punctuation tokens, e.g. is, how, ?
     * 
     * @param type expected token type
     * @throws ParseException if token is not found
     */
    protected void validateAndConsumeToken(TokenType type) {
        validateAndConsumeToken(type, false);
    }
    
    /**
     * Validates the current token against the supplied token type.  If valid,
     * advances token.  If invalid, throws ParseException.  Use for meaningless
     * keyword or punctuation tokens, e.g. is, how, ?
     * 
     * @param type expected token type
     * @param isOptional expected token is optional
     * @throws ParseException if token is not found and isOptional flag is <code>false</code>
     */
    protected void validateAndConsumeToken(TokenType type, boolean isOptional) {
        if (getToken().isTokenType(type)) {
            nextToken();
            return;
        }
        if (!isOptional) {
            throw createSyntaxException(type);
        }
    }
    
    /**
     * Advances token and throws parser exception if next token is not EOF.
     */
    protected void validateEOF() {
        nextToken();
        final Token token = getToken();
        if (!token.isTokenType(TokenType.EOF)) {
            final String msg = String.format("Syntax error; unexpected symbol %s", token.getText());
            throw new ParseException(msg);
        }
    }

    /**
     * Creates a {@link ParseException} as a syntax error adding the expected
     * token type to the error message.
     *  
     * @param expected token type expected
     * @return new {@link ParseException}
     */
    protected ParseException createSyntaxException(TokenType expected) {
        final String msg = String.format("Syntax error on token=%s; expected=%s", 
                getToken().getText(), expected.text()); 
        return new ParseException(msg);
    }

}
