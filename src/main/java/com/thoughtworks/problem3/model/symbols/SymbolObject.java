/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import com.thoughtworks.problem3.utils.Validate;

/**
 * Abstract symbol object used in a {@link SymbolTable}.
 * 
 * @author rgeyer
 *
 */
public abstract class SymbolObject {
    
    protected final String name;
    
    protected SymbolObject(String name) {
        Validate.notBlank(name, "name cannot be null or blank");
        this.name = name;
    }

    /**
     * @return the name of this symbol
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SymbolObject)) {
            return false;
        }
        SymbolObject other = (SymbolObject) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
