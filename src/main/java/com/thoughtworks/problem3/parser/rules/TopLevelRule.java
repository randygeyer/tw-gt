package com.thoughtworks.problem3.parser.rules;

import java.util.regex.Pattern;

import com.thoughtworks.problem3.parser.TokenBuffer;

/**
 * Top level parsing rule responsible for determining if it matches the 
 * supplied input.
 * 
 * @author rgeyer
 *
 */
public abstract class TopLevelRule extends Rule {

    private final Pattern pattern;
    
    public TopLevelRule(TokenBuffer tokens, String matchingRegex) {
        super(tokens);
        pattern = Pattern.compile(matchingRegex);
    }

    /**
     * Determines if this rule can parse the supplied input.
     * 
     * @param input text to parse
     * @return <code>true</code> if this rule can parse the supplied input
     */
    public boolean matches(String input) {
        if (input == null) return false;
        return pattern.matcher(input).matches();
    }
    
}
