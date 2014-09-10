/**
 * 
 */
package com.thoughtworks.problem3.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.NumeralDeclaration;
import com.thoughtworks.problem3.model.symbols.NumeralSymbol;
import com.thoughtworks.problem3.model.symbols.RomanNumeral;

/**
 * Unit test fixture for {@link NumeralDeclaration}.
 * 
 * @author rgeyer
 *
 */
public class NumeralDeclarationTest {
    
    private static final Logger logger = LoggerFactory.getLogger(NumeralDeclarationTest.class);
    
    private QueryContext context;
    
    @Before
    public void setup() {
        context = new QueryContext();
    }

    @Test
    public void testInterpret() {
        logger.trace("testInterpret()");
        
        assertNumeralDeclaration("uno", 'I', createNumeralDeclaration("uno", 'I'));
        assertNumeralDeclaration("dec", 'X', createNumeralDeclaration("dec", 'X'));
        assertNumeralDeclaration("cen", 'C', createNumeralDeclaration("cen", 'C'));
    }
    
    private void assertNumeralDeclaration(String symbol, char roman, NumeralDeclaration decl) {
        logger.trace("assertNumericDeclaration(): symbol={}", symbol);

        decl.interpret(context);
        
        final NumeralSymbol numeral = context.getNumerals().lookup(symbol);
        assertNotNull(numeral);
        assertEquals(symbol, numeral.getName());
        assertEquals(roman, numeral.getRomanNumeral().symbol());
    }
    
    
    private NumeralDeclaration createNumeralDeclaration(String name, char roman) {
        return new NumeralDeclaration(name, RomanNumeral.from(roman));
    }
    
}
