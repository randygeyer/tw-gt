/**
 * 
 */
package com.thoughtworks.problem3.model;

import com.thoughtworks.problem3.parser.GalacticParser;
import com.thoughtworks.problem3.parser.Lexer;

/**
 * Translates intergalactic numbers and Credits to Earth values.
 * Delegates parsing to {@link GalacticParser}.  Main purpose is to shield
 * the application class from dependency injection, since external libraries
 * are not allowed.
 *  
 * @see GalacticParser
 *
 * @author rgeyer
 * 
 */
public class GalacticTranslator {
    
    private final QueryContext context;
    private final GalacticParser parser;
    
    public GalacticTranslator() {
        // poor man's dependency injection :)
        this.context = new QueryContext();
        this.parser = new GalacticParser(new Lexer(), context);
    }

    public void interpret(String input) {
        parser.parse(input);
    }
    
}
