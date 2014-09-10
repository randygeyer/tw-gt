/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import java.math.BigDecimal;

import com.thoughtworks.problem3.utils.Validate;

/**
 * A metal resource used in galactic trade, along with its unit price in credits.
 * 
 * @author rgeyer
 *
 */
public class MetalSymbol extends SymbolObject {
    
    private final BigDecimal unitValue;

    public MetalSymbol(String name, BigDecimal unitValue) {
        super(name);

        Validate.notNull(unitValue, "unitValue cannot be null");
        this.unitValue = unitValue;
    }

    /**
     * @return the unitPrice
     */
    public BigDecimal getUnitPrice() {
        return unitValue;
    }

    @Override
    public String toString() {
        return String.format("[name=%s; unitValue=%.2f]", name, unitValue);
    }
}
