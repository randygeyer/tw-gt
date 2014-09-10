/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.problem3.utils.Validate;

/**
 * Maintains a collection of defined symbols.
 * 
 * @author rgeyer
 *
 * @param <T> Symbol type for the collection
 */
public abstract class SymbolTable<T extends SymbolObject> {

    protected final Map<String, T> symbols = new HashMap<String, T>();
    
    private final String type;
    
    protected SymbolTable(String type) {
        this.type = type;
    }
    
    /**
     * Adds the supplied symbol to this symbol table
     * 
     * @param symbol symbol to add to the table
     * @throws IllegalArgumentException if the symbol is null or duplicate
     */
    protected void addSymbol(T symbol) {
        Validate.notNull(symbol, "symbol cannot be null");

        final String name = symbol.getName();
        if (symbols.containsKey(name)) {
            final String msg = String.format("Duplicate %1$s; %1$s=", type);
            throw new IllegalArgumentException(msg);
        }
        
        symbols.put(name, symbol);
    }
    
    /**
     * Returns the symbol for the supplied name, if it exists.
     * 
     * @param name symbol name to lookup
     * @return symbol if it exists, otherwise <code>null</code>
     */
    public T lookup(String name) {
        return symbols.get(name);
    }
    
    /**
     * @return all the values in the symbol table
     */
    public Collection<T> getAll() {
        return symbols.values();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (String name : symbols.keySet()) {
            sb.append(" " + name);
        }
        return sb.substring(1);
    }
}
