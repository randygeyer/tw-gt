/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.utils.Validate;

/**
 * Unit test fixture for {@link MetalSymbol}.
 * 
 * @author rgeyer
 *
 */
public class MetalSymbolTest {
    
    private static final Logger logger = LoggerFactory.getLogger(MetalSymbolTest.class);

    @Test
    public void testMetal() {
        logger.trace("testMetal()");
        
        final String NAME = "name";
        final BigDecimal VALUE = BigDecimal.TEN;
        final MetalSymbol metal = new MetalSymbol(NAME, VALUE);
        
        Validate.notBlank(metal.toString(), "Metal.toString() failed");
        logger.debug("metal={}", metal);
        assertEquals(NAME, metal.getName());
        assertEquals(VALUE, metal.getUnitPrice());
    }
    
    @Test
    public void testEquals() {
        logger.trace("testEquals()");
        
        final String NAME1 = "name1";
        final String NAME2 = "name2";
        final BigDecimal VALUE = BigDecimal.TEN;
        
        final MetalSymbol metal1 = new MetalSymbol(NAME1, VALUE);
        final MetalSymbol metal2 = new MetalSymbol(NAME2, VALUE);

        assertTrue(metal1.equals(metal1));
        assertTrue(metal1.equals(new MetalSymbol(NAME1, VALUE)));
        assertFalse(metal1.equals(null));
        assertFalse(metal1.equals(metal2));
    }

}
