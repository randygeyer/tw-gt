/**
 * 
 */
package com.thoughtworks.problem3.parser;

import java.util.regex.Pattern;

import com.thoughtworks.problem3.utils.Validate;

/**
 * Token types produced by the {@link Lexer}, along with their regular expression.
 * 
 * @author rgeyer
 *
 */
public enum TokenType {

    // Keywords - listed first to give precedence during lexing
    CREDITS_KEYWORD("Credits", "^Credits", true),
    HOW_KEYWORD("how", "^how", true),
    IS_KEYWORD("is", "^is", true),
    MANY_KEYWORD("many", "^many", true),
    MUCH_KEYWORD("much", "^much", true),
    
    // Literals
    NUMERAL_LITERAL("roman numeral", "^(I|V|X|L|C|D|M)", true),
    INTEGER_LITERAL("integer", "^[1-9][0-9]*", true),
    
    // Identifiers
    METAL_IDENTIFIER("identifier", "^[A-Z][a-z]*", true),
    NUMERAL_IDENTIFIER("identifier", "^[a-z]+", true),

    // Punctuation & whitespace
    QUESTION_MARK("?", "^\\?", false),
    WHITESPACE(" ", "^\\s+", false),
    EOF("", "", false);
    
    private final Pattern regex;
    private final boolean outputToken;
    private final String text;
    
    private TokenType(String text, String regex, boolean outputToken) {
        this.text = text;
        this.regex = Pattern.compile(regex);
        this.outputToken = outputToken;
    }
    
    public Pattern regex() {
        return regex;
    }
    
    public boolean outputToken() {
        return outputToken;
    }
    
    public String text() {
        return text;
    }
    
    public boolean matches(String input) {
        Validate.notNull(input, "input cannot be null");
        return regex.matcher(input).matches();
    }
    
}
