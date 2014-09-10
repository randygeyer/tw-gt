/**
 * 
 */
package com.thoughtworks.problem3.utils;

/**
 * Argument validation utility methods (since I couldn't use external libraries :)
 * 
 * Patterned after a subset of Apache commons-lang Validate class.
 * 
 * @author rgeyer
 *
 */
public class Validate {
    
    private Validate() {
        // static class
    }
    
    /**
     * Validates the supplied object is not null.
     * 
     * @param obj object to validate
     * @param errorMsg error message to include in the thrown exception
     * @throws IllegalArgumentException if the object is null
     */
    public static void notNull(Object obj, String errorMsg) {
        if(obj == null)
            throw new IllegalArgumentException(errorMsg);
    }

    /**
     * Validates the supplied string is not null or empty.
     * 
     * @param str string to validate
     * @param errorMsg error message to include in the thrown exception
     * @throws IllegalArgumentException if the string is null or empty
     */
    public static void notEmpty(String str, String errorMsg) {
        if(str != null && str.length() > 0)
            return;
        
        throw new IllegalArgumentException(errorMsg);
    }

    /**
     * Validates the supplied string is not null, empty or blank by trimming the 
     * supplied string.
     * 
     * @param str string to validate
     * @param errorMsg error message to include in the thrown exception
     * @throws IllegalArgumentException if the string is null, empty or blank
     */
    public static void notBlank(String str, String errorMsg) {
        if(str != null && str.trim().length() > 0)
            return;
        
        throw new IllegalArgumentException(errorMsg);
    }

}
