/**
 * 
 */
package com.thoughtworks.problem3.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test fixture for {@link StringUtils}.
 * 
 * @author rgeyer
 *
 */
public class StringUtilsTest {
    
    private static final Logger logger = LoggerFactory.getLogger(StringUtilsTest.class);

    /**
     * Test method for {@link StringUtils#isNullOrEmpty(java.lang.String)}.
     */
    @Test
    public void testIsNullOrEmpty() {
        logger.trace("testIsNullOrEmpty()");
        
        assertTrue(StringUtils.isNullOrEmpty(null));
        assertTrue(StringUtils.isNullOrEmpty(""));
        assertFalse(StringUtils.isNullOrEmpty(" "));
        assertFalse(StringUtils.isNullOrEmpty("test"));
    }

    /**
     * Test method for {@link StringUtils#isNullOrBlank(java.lang.String)}.
     */
    @Test
    public void testIsNullOrBlank() {
        logger.trace("testIsNullOrBlank()");

        assertTrue(StringUtils.isNullOrBlank(null));
        assertTrue(StringUtils.isNullOrBlank(""));
        assertTrue(StringUtils.isNullOrBlank(" "));
        assertTrue(StringUtils.isNullOrBlank("   "));
        assertFalse(StringUtils.isNullOrBlank("test"));
    }

    /**
     * Test method for {@link StringUtils#reverse(java.lang.String)}.
     */
    @Test
    public void testReverse() {
        logger.trace("testReverse()");

        assertEquals(null, StringUtils.reverse(null));
        assertEquals("", StringUtils.reverse(""));
        assertEquals(" ", StringUtils.reverse(" "));
        assertEquals("  ", StringUtils.reverse("  "));
        
        assertEquals("g", StringUtils.reverse("g"));
        assertEquals("god", StringUtils.reverse("dog"));
    }

}
