/**
 * 
 */
package com.thoughtworks.problem3.model;

import com.thoughtworks.problem3.model.symbols.MetalSymbol;
import com.thoughtworks.problem3.model.symbols.MetalSymbolTable;
import com.thoughtworks.problem3.model.symbols.NumeralSymbol;
import com.thoughtworks.problem3.model.symbols.NumeralSymbolTable;

/**
 * Galactic translation query context that tracks symbol declarations
 * and prints query output.
 *  
 * @author rgeyer
 *
 */
public class QueryContext {
    
    private final NumeralSymbolTable numerals = new NumeralSymbolTable();
    private final MetalSymbolTable metals = new MetalSymbolTable();
    
    public QueryContext() {
        // do nothing
    }

    /**
     * @return the {@link NumeralSymbolTable}
     */
    public NumeralSymbolTable getNumerals() {
        return numerals;
    }
    
    /**
     * @return the {@link MetalSymbolTable}
     */
    public MetalSymbolTable getMetals() {
        return metals;
    }

    /**
     * Adds the supplied numeral to the Numeral symbol table
     * 
     * @param numeral numeral symbol to add
     * @returns this context for fluent expressions
     */
    public QueryContext addNumeral(NumeralSymbol numeral) {
        numerals.add(numeral);
        return this;
    }

    /**
     * Adds the supplied metal to the Metal symbol table
     * 
     * @param metal metal to add
     * @returns this context for fluent expressions
     */
    public QueryContext addMetal(MetalSymbol metal) {
        metals.add(metal);
        return this;
    }
    
    /**
     * Look up the supplied name from the metal symbol table.
     * 
     * @param metalName name to look up
     * @return the metal symbol if found, otherwise <code>null</code>
     */
    public MetalSymbol lookup(String metalName) {
        return metals.lookup(metalName);
    }

    /**
     * Prints the supplied output.
     * 
     * @param output string to print
     */
    public void print(String output) {
        // if I had more time, I'd use an output Strategy pattern here
        // and inject a IPrinter concrete object for output.
        System.out.println(output);
    }

}
