/**
 * 
 */
package com.thoughtworks.problem3.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test fixture for {@link TokenType}.
 * 
 * @author rgeyer
 *
 */
public class TokenTypeTest {
    
    private static final Logger logger = LoggerFactory.getLogger(TokenTypeTest.class);

    @Test
    public void testMatches() {
        logger.trace("testMatches()");
        
        // Keywords        
        assertTrue(TokenType.CREDITS_KEYWORD.matches("Credits"));
        assertTrue(TokenType.HOW_KEYWORD.matches("how"));
        assertTrue(TokenType.IS_KEYWORD.matches("is"));
        assertTrue(TokenType.MANY_KEYWORD.matches("many"));
        assertTrue(TokenType.MUCH_KEYWORD.matches("much"));
        assertTrue(TokenType.QUESTION_MARK.matches("?"));
        
        // Literals
        assertTrue(TokenType.INTEGER_LITERAL.matches("1"));
        assertTrue(TokenType.INTEGER_LITERAL.matches("10"));
        assertTrue(TokenType.INTEGER_LITERAL.matches("123"));
        assertTrue(TokenType.INTEGER_LITERAL.matches("999"));
        assertFalse(TokenType.INTEGER_LITERAL.matches("0"));
        assertFalse(TokenType.INTEGER_LITERAL.matches("0123"));

        assertTrue(TokenType.NUMERAL_LITERAL.matches("I"));
        assertTrue(TokenType.NUMERAL_LITERAL.matches("V"));
        assertTrue(TokenType.NUMERAL_LITERAL.matches("X"));
        assertTrue(TokenType.NUMERAL_LITERAL.matches("L"));
        assertTrue(TokenType.NUMERAL_LITERAL.matches("C"));
        assertTrue(TokenType.NUMERAL_LITERAL.matches("D"));
        assertTrue(TokenType.NUMERAL_LITERAL.matches("M"));
        
        // Identifiers
        assertTrue(TokenType.METAL_IDENTIFIER.matches("Gold"));
        assertTrue(TokenType.METAL_IDENTIFIER.matches("Silver"));
        assertTrue(TokenType.METAL_IDENTIFIER.matches("Plat"));

        assertTrue(TokenType.NUMERAL_IDENTIFIER.matches("p"));
        assertTrue(TokenType.NUMERAL_IDENTIFIER.matches("glob"));
        assertTrue(TokenType.NUMERAL_IDENTIFIER.matches("prok"));
        assertTrue(TokenType.NUMERAL_IDENTIFIER.matches("pish"));
    }

}
