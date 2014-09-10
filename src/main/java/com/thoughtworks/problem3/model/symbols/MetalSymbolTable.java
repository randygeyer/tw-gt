/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;


/**
 * Maintains a collection of defined Metals.
 * 
 * FIXME: refactor this class out and just use SymbolTable<MetalSymbol>. 
 * 
 * @author rgeyer
 *
 */
public class MetalSymbolTable extends SymbolTable<MetalSymbol> {

    public MetalSymbolTable() {
        super("metal");
    }

    /**
     * Adds the supplied metal symbol to this symbol table
     * 
     * @param symbol symbol to add to the table
     * @throws IllegalArgumentException if the symbol is null or duplicate
     */
    public MetalSymbolTable add(MetalSymbol symbol) {
        super.addSymbol(symbol);
        return this;
    }
}
