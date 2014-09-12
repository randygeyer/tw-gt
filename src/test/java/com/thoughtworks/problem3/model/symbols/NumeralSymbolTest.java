/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.utils.Validate;

/**
 * Unit test fixture for {@link NumeralSymbol}.
 * 
 * @author rgeyer
 *
 */
public class NumeralSymbolTest {
    
    private static final Logger logger = LoggerFactory.getLogger(NumeralSymbolTest.class);

    @Test
    public void testSymbol() {
        logger.trace("testSymbol()");
        
        final String NAME = "name";
        final RomanNumeral NUMERAL = RomanNumeral.D;
        final NumeralSymbol symbol = new NumeralSymbol(NAME, NUMERAL);
        
        Validate.notBlank(symbol.toString(), "Symbol.toString() failed");
        logger.debug("Symbol: {}", symbol);
        assertEquals(NAME, symbol.getName());
        assertEquals(NUMERAL, symbol.getRomanNumeral());
        assertEquals(NUMERAL.numericValue(), symbol.getValue());
    }
    
    @Test
    public void testEquals() {
        logger.trace("testEquals()");
        
        final String NAME1 = "name1";
        final String NAME2 = "name2";
        final RomanNumeral NUMERAL = RomanNumeral.D;
        
        final NumeralSymbol symbol1 = new NumeralSymbol(NAME1, NUMERAL);
        final NumeralSymbol symbol2 = new NumeralSymbol(NAME2, NUMERAL);

        assertTrue(symbol1.equals(symbol1));
        assertTrue(symbol1.equals(new NumeralSymbol(NAME1, NUMERAL)));
        assertFalse(symbol1.equals(null));
        assertFalse(symbol1.equals(symbol2));
    }

}
