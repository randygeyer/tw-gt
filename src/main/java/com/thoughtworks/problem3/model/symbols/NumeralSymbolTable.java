/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;


/**
 * Maintains a collection of defined Symbols.
 * 
 * FIXME: refactor this class out and just use SymbolTable<NumeralSymbol>.
 *  
 * @author rgeyer
 *
 */
public class NumeralSymbolTable extends SymbolTable<NumeralSymbol> {


    public NumeralSymbolTable() {
        super("numeric");
    }

    /**
     * Adds the supplied symbol to the Symbol table
     * 
     * @param symbol symbol to add to the table
     */
    public NumeralSymbolTable add(NumeralSymbol symbol) {
        super.addSymbol(symbol);
        return this;
    }

    /**
     * Returns the numeral symbol for the supplied name, if it exists.
     * 
     * @param name symbol name to lookup
     * @return symbol if it exists, otherwise <code>null</code>
     */
    public NumeralSymbol lookup(String name) {
        return symbols.get(name);
    }
    
}
