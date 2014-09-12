/**
 * 
 */
package com.thoughtworks.problem3.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test fixture for {@link Token}.
 * 
 * @author rgeyer
 *
 */
public class TokenTest {
    
    private static final Logger logger = LoggerFactory.getLogger(TokenTest.class);

    @Test
    public void testLiteral() {
        logger.trace("testLiteral()");
        
        final String TEXT = "123";
        
        final Token token = new Token(TokenType.INTEGER_LITERAL, TEXT);
        assertEquals(TokenType.INTEGER_LITERAL, token.getType());
        assertEquals(TEXT, token.getText());
        assertTrue(token.isTokenType(TokenType.INTEGER_LITERAL));
        
        final String str = token.toString();
        assertTrue(str.length() > 0);
        assertTrue(str.contains("text="));
        logger.debug("Token: {}", token);
    }

    @Test
    public void testKeyword() {
        logger.trace("testKeyword()");
        
        final Token token = new Token(TokenType.HOW_KEYWORD);
        assertEquals(TokenType.HOW_KEYWORD, token.getType());
        assertNull(token.getText());
        
        final String str = token.toString();
        assertTrue(str.length() > 0);
        assertFalse(str.contains("text="));
        logger.debug("Token: {}", token);
    }
    
    @Test
    public void testEquals() {
        logger.trace("testEquals()");
        
        assertEquals(Token.HOW_KEYWORD, Token.HOW_KEYWORD);
        assertEquals(Token.HOW_KEYWORD, new Token(TokenType.HOW_KEYWORD, "how"));
        assertFalse(Token.HOW_KEYWORD.equals(new Token(TokenType.HOW_KEYWORD)));
        assertFalse(Token.HOW_KEYWORD.equals(new Token(TokenType.HOW_KEYWORD, "bogus")));
        assertFalse(Token.HOW_KEYWORD.equals(Token.IS_KEYWORD));
    }

}
