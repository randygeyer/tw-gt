/**
 * 
 */
package com.thoughtworks.problem3.model.symbols;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test fixture for {@link NumeralExpression}.
 * 
 * @author rgeyer
 *
 */
public class NumeralExpressionTest {

    /**
     * Test method for {@link NumeralExpression#add(java.lang.String)}.
     */
    @Test
    public void testAdd() {
        final NumeralExpression expr = createSymbolExpression();
        assertEquals(4, expr.getCount());
    }

    /**
     * Test method for {@link NumeralExpression#calculateNumericValue()}.
     */
    @Test
    public void testCalculateNumericValue() {
        final NumeralExpression expr = createSymbolExpression();
        assertEquals(119, expr.calculateNumericValue());
    }

    private NumeralExpression createSymbolExpression() {
        return new NumeralExpression(createSymbolTable())
                .add("cen")
                .add("dec")
                .add("one")
                .add("dec");
    }

    private NumeralSymbolTable createSymbolTable() {
        return new NumeralSymbolTable()
                .add(new NumeralSymbol("one", RomanNumeral.I))
                .add(new NumeralSymbol("dec", RomanNumeral.X))
                .add(new NumeralSymbol("cen", RomanNumeral.C));
    }
}
