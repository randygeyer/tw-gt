/**
 * 
 */
package com.thoughtworks.problem3.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test fixture for {@link Lexer}.
 * 
 * @author rgeyer
 *
 */
public class LexerTest {
    
    private static final Logger logger = LoggerFactory.getLogger(LexerTest.class);
    
    private Lexer lexer;
    
    @Before
    public void setup() {
        lexer = new Lexer();
    }

    @Test
    public void testLex_Success() {
        logger.trace("testLex_Success()");

        List<Token> tokens;
        
        tokens = lex("glob is I", 3);
        assertToken(new Token(TokenType.NUMERAL_IDENTIFIER, "glob"), tokens);
        assertToken(Token.IS_KEYWORD, tokens);
        assertToken(new Token(TokenType.NUMERAL_LITERAL, "I"), tokens);
        
        tokens = lex("glob prok Gold is 57800 Credits", 6);
        assertToken(new Token(TokenType.NUMERAL_IDENTIFIER, "glob"), tokens);
        assertToken(new Token(TokenType.NUMERAL_IDENTIFIER, "prok"), tokens);
        assertToken(new Token(TokenType.METAL_IDENTIFIER, "Gold"), tokens);
        assertToken(Token.IS_KEYWORD, tokens);
        assertToken(new Token(TokenType.INTEGER_LITERAL, "57800"), tokens);
        assertToken(Token.CREDITS_KEYWORD, tokens);

        tokens = lex("how much is pish tegj glob glob ?", 7);
        assertToken(Token.HOW_KEYWORD, tokens);
        assertToken(Token.MUCH_KEYWORD, tokens);
        assertToken(Token.IS_KEYWORD, tokens);
        assertToken(new Token(TokenType.NUMERAL_IDENTIFIER, "pish"), tokens);
        assertToken(new Token(TokenType.NUMERAL_IDENTIFIER, "tegj"), tokens);
        assertToken(new Token(TokenType.NUMERAL_IDENTIFIER, "glob"), tokens);
        assertToken(new Token(TokenType.NUMERAL_IDENTIFIER, "glob"), tokens);
        //assertToken(Token.QUESTION_MARK, tokens);

        tokens = lex("how many Credits is glob prok Iron ?", 7);
        assertToken(Token.HOW_KEYWORD, tokens);
        assertToken(Token.MANY_KEYWORD, tokens);
        assertToken(Token.CREDITS_KEYWORD, tokens);
        assertToken(Token.IS_KEYWORD, tokens);
        assertToken(new Token(TokenType.NUMERAL_IDENTIFIER, "glob"), tokens);
        assertToken(new Token(TokenType.NUMERAL_IDENTIFIER, "prok"), tokens);
        assertToken(new Token(TokenType.METAL_IDENTIFIER, "Iron"), tokens);
        //assertToken(Token.QUESTION_MARK, tokens);
    }
    
    @Test(expected=ParseException.class)
    public void testLex_Failure() {
        logger.trace("testLex_Failure()");
        
        lex("bogus token is 01234", 4);
    }

    private List<Token> lex(String input, int tokenCount) {
        logger.trace("lex(): input={}; count={}", input, tokenCount);
        
        final List<Token> tokens = lexer.lex(input);
        assertNotNull(tokens);
        assertEquals(tokenCount, tokens.size());
        return tokens;
    }
    
    private void assertToken(Token token, List<Token> tokens) {
        logger.trace("assertToken(); token={}", token);
        
        assertNotNull(tokens);
        assertTrue(tokens.contains(token));
    }

}
