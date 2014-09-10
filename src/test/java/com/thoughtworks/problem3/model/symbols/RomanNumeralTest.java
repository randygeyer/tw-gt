/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.model.symbols.RomanNumeral;

/**
 * Unit test fixture for {@link RomanNumeral}.
 * 
 * @author rgeyer
 *
 */
public class RomanNumeralTest {
    
    private static final Logger logger = LoggerFactory.getLogger(RomanNumeralTest.class);

    /**
     * Test method for {@link RomanNumeral#from(char)}.
     */
    @Test
    public void testFrom_Success() {
        logger.trace("testFrom_Success()");
        
        assertEquals(RomanNumeral.I, RomanNumeral.from('I'));
        assertEquals(RomanNumeral.V, RomanNumeral.from('V'));
        assertEquals(RomanNumeral.X, RomanNumeral.from('X'));
        assertEquals(RomanNumeral.L, RomanNumeral.from('L'));
        assertEquals(RomanNumeral.C, RomanNumeral.from('C'));
        assertEquals(RomanNumeral.D, RomanNumeral.from('D'));
        assertEquals(RomanNumeral.M, RomanNumeral.from('M'));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testFrom_Illegal() {
        logger.trace("testFrom_Illegal()");
        
        RomanNumeral.from(' ');
    }
    
    @Test
    public void testIsValid() {
        logger.trace("testIsValid()");
        
        assertFalse(RomanNumeral.isValidExpression(null));
        
        assertTrue(RomanNumeral.isValidExpression(""));
        assertTrue(RomanNumeral.isValidExpression("I"));
        assertTrue(RomanNumeral.isValidExpression("II"));
        assertTrue(RomanNumeral.isValidExpression("III"));

        assertTrue(RomanNumeral.isValidExpression("IV"));
        assertTrue(RomanNumeral.isValidExpression("V"));
        assertTrue(RomanNumeral.isValidExpression("VI"));
        assertTrue(RomanNumeral.isValidExpression("VIII"));

        assertTrue(RomanNumeral.isValidExpression("IX"));
        assertTrue(RomanNumeral.isValidExpression("X"));
        assertTrue(RomanNumeral.isValidExpression("XI"));
        assertTrue(RomanNumeral.isValidExpression("XIV"));
        assertTrue(RomanNumeral.isValidExpression("XV"));
        assertTrue(RomanNumeral.isValidExpression("XIX"));
        assertTrue(RomanNumeral.isValidExpression("XXV"));
        assertTrue(RomanNumeral.isValidExpression("XXX"));
        assertTrue(RomanNumeral.isValidExpression("XXXV"));
        assertTrue(RomanNumeral.isValidExpression("XXXIX"));

        assertTrue(RomanNumeral.isValidExpression("XL"));
        assertTrue(RomanNumeral.isValidExpression("L"));
        assertTrue(RomanNumeral.isValidExpression("LI"));
        assertTrue(RomanNumeral.isValidExpression("LV"));
        assertTrue(RomanNumeral.isValidExpression("LX"));
        assertTrue(RomanNumeral.isValidExpression("LXXX"));
        assertTrue(RomanNumeral.isValidExpression("LXXXV"));
        assertTrue(RomanNumeral.isValidExpression("LXXXIX"));

        assertTrue(RomanNumeral.isValidExpression("XC"));
        assertTrue(RomanNumeral.isValidExpression("C"));
        assertTrue(RomanNumeral.isValidExpression("CC"));
        assertTrue(RomanNumeral.isValidExpression("CCC"));
        assertTrue(RomanNumeral.isValidExpression("CD"));
        assertTrue(RomanNumeral.isValidExpression("CDLXXXIX"));

        assertTrue(RomanNumeral.isValidExpression("D"));
        assertTrue(RomanNumeral.isValidExpression("DI"));
        assertTrue(RomanNumeral.isValidExpression("DV"));
        assertTrue(RomanNumeral.isValidExpression("DX"));
        assertTrue(RomanNumeral.isValidExpression("DC"));
        assertTrue(RomanNumeral.isValidExpression("DCC"));
        assertTrue(RomanNumeral.isValidExpression("DCCC"));
        assertTrue(RomanNumeral.isValidExpression("DCCCXXXIX"));
        
        assertTrue(RomanNumeral.isValidExpression("CM"));
        assertTrue(RomanNumeral.isValidExpression("CMXCIX"));
        
        assertTrue(RomanNumeral.isValidExpression("M"));
        assertTrue(RomanNumeral.isValidExpression("MM"));
        assertTrue(RomanNumeral.isValidExpression("MMM"));
        

        assertFalse(RomanNumeral.isValidExpression("IIII"));
        assertFalse(RomanNumeral.isValidExpression("IIV"));
        assertFalse(RomanNumeral.isValidExpression("IIX"));
        assertFalse(RomanNumeral.isValidExpression("IXV"));
        assertFalse(RomanNumeral.isValidExpression("IL"));
        assertFalse(RomanNumeral.isValidExpression("IC"));
        assertFalse(RomanNumeral.isValidExpression("ID"));
        assertFalse(RomanNumeral.isValidExpression("IM"));

        assertFalse(RomanNumeral.isValidExpression("VX"));
        assertFalse(RomanNumeral.isValidExpression("VV"));
        assertFalse(RomanNumeral.isValidExpression("VL"));
        assertFalse(RomanNumeral.isValidExpression("VC"));
        assertFalse(RomanNumeral.isValidExpression("VD"));
        assertFalse(RomanNumeral.isValidExpression("VM"));

        assertFalse(RomanNumeral.isValidExpression("XXXX"));
        assertFalse(RomanNumeral.isValidExpression("XD"));
        assertFalse(RomanNumeral.isValidExpression("XM"));

        assertFalse(RomanNumeral.isValidExpression("CCCC"));
    }

    @Test
    public void testComputeValue_Valid() {
        logger.trace("testComputeValue_Valid()");
        
        assertEquals(0, RomanNumeral.computeValue(null));
        assertEquals(0, RomanNumeral.computeValue(""));
        assertEquals(0, RomanNumeral.computeValue(" "));
        assertEquals(1, RomanNumeral.computeValue("I"));
        assertEquals(2, RomanNumeral.computeValue("II"));
        assertEquals(3, RomanNumeral.computeValue("III"));
        assertEquals(4, RomanNumeral.computeValue("IV"));
        assertEquals(5, RomanNumeral.computeValue("V"));
        assertEquals(6, RomanNumeral.computeValue("VI"));
        assertEquals(7, RomanNumeral.computeValue("VII"));
        assertEquals(8, RomanNumeral.computeValue("VIII"));
        assertEquals(9, RomanNumeral.computeValue("IX"));
        assertEquals(10, RomanNumeral.computeValue("X"));
        assertEquals(15, RomanNumeral.computeValue("XV"));
        assertEquals(19, RomanNumeral.computeValue("XIX"));
        assertEquals(39, RomanNumeral.computeValue("XXXIX"));
        assertEquals(40, RomanNumeral.computeValue("XL"));
        assertEquals(49, RomanNumeral.computeValue("XLIX"));
        assertEquals(90, RomanNumeral.computeValue("XC"));
        assertEquals(99, RomanNumeral.computeValue("XCIX"));
        assertEquals(600, RomanNumeral.computeValue("DC"));
        assertEquals(900, RomanNumeral.computeValue("CM"));
        assertEquals(999, RomanNumeral.computeValue("CMXCIX"));
        assertEquals(2014, RomanNumeral.computeValue("MMXIV"));
    }
    
    @Test
    public void testComputeValue_Invalid() {
        logger.trace("testComputeValue_Invalid()");
        
        assertIllegalValue("bogus");
        assertIllegalValue("XXXX");
        assertIllegalValue("IXV");
    }
    
    private void assertIllegalValue(String input) {
        try {
            RomanNumeral.computeValue(input);
            fail("Expected IllegalArgumentException for input=" + input);
        } catch (IllegalArgumentException ex) {
            // expected
            logger.trace("assertIllegalValue(): message={}", ex.getMessage());
        }
    }
}
