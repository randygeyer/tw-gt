/**
 * 
 */
package com.thoughtworks.problem3.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test fixture for {@link Validate}.
 * 
 * @author rgeyer
 *
 */
public class ValidateTest {
    
    private static final Logger logger = LoggerFactory.getLogger(ValidateTest.class);

    
    @Test
    public void testNotNull_Success() {
        logger.trace("testNotNull_Success()");
        
        Validate.notNull(new Object(), "");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNotNull_Null() {
        logger.trace("testNotNull_Null()");
        
        Validate.notNull(null, "");
    }
    
    @Test
    public void testNotEmpty_Success() {
        logger.trace("testNotEmpty_Success()");
        
        Validate.notEmpty(" ", "");
        Validate.notEmpty("good", "");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNotEmpty_Null() {
        logger.trace("testNotEmpty_Null()");
        
        Validate.notEmpty(null, "");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNotEmpty_Empty() {
        logger.trace("testNotEmpty_Empty()");
        
        Validate.notEmpty("", "");
    }

    @Test
    public void testNotBlank_Success() {
        logger.trace("testNotBlank_Success()");
        
        Validate.notBlank("good", "");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNotBlank_Null() {
        logger.trace("testNotBlank_Null()");
        
        Validate.notBlank(null, "");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNotBlank_Blank() {
        logger.trace("testNotBlank_Blank()");
        
        Validate.notBlank("  ", "");
    }

}
