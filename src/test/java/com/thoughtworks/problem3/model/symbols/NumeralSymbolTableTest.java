/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.model.symbols.NumeralSymbol;
import com.thoughtworks.problem3.model.symbols.NumeralSymbolTable;
import com.thoughtworks.problem3.model.symbols.RomanNumeral;

/**
 * Unit test fixture for {@link NumeralSymbolTable}.
 * 
 * @author rgeyer
 *
 */
public class NumeralSymbolTableTest {
    
    private static final Logger logger = LoggerFactory.getLogger(NumeralSymbolTableTest.class);

    @Test
    public void test() {
        logger.trace("test()");
        
        final NumeralSymbol symbol = new NumeralSymbol("one", RomanNumeral.I); 
        NumeralSymbolTable table = new NumeralSymbolTable()
                .add(symbol);
            
        assertEquals(symbol, table.lookup(symbol.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd_Duplicate() {
        logger.trace("testAdd_Duplicate()");

        final NumeralSymbol symbol = new NumeralSymbol("one", RomanNumeral.I); 
        final NumeralSymbolTable table = new NumeralSymbolTable()
                .add(symbol)
                .add(symbol);

        // avoid compiler warning
        table.toString();
    }
}
