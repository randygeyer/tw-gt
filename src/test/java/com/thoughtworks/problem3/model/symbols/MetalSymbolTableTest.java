/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.model.symbols.MetalSymbol;
import com.thoughtworks.problem3.model.symbols.MetalSymbolTable;

/**
 * Unit test fixture for {@link MetalSymbolTable}.
 * 
 * @author rgeyer
 *
 */
public class MetalSymbolTableTest {

    private static final Logger logger = LoggerFactory.getLogger(MetalSymbolTableTest.class);

    @Test
    public void testAdd_Success() {
        logger.trace("testAdd_Success()");

        final MetalSymbol metal = new MetalSymbol("one", BigDecimal.ONE);
        MetalSymbolTable table = new MetalSymbolTable()
                .add(metal);

        assertEquals(metal, table.lookup(metal.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd_Duplicate() {
        logger.trace("testAdd_Duplicate()");

        final MetalSymbol metal = new MetalSymbol("one", BigDecimal.ONE);
        final MetalSymbolTable table = new MetalSymbolTable()
                .add(metal)
                .add(metal);

        // avoid compiler warning
        table.toString();
    }

}
