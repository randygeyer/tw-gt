/**
 * 
 */
package com.thoughtworks.problem3.model;

/**
 * Represents our base query which can be interpreted with a supplied context.
 * 
 * @author rgeyer
 *
 */
public abstract class Query {
    
    /**
     * Interprets the query with the supplied context.
     * 
     * @param context
     */
    public abstract void interpret(QueryContext context);

}
