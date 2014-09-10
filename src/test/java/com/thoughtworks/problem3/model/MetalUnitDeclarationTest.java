/**
 * 
 */
package com.thoughtworks.problem3.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.MetalUnitDeclaration;
import com.thoughtworks.problem3.model.symbols.MetalSymbol;
import com.thoughtworks.problem3.model.symbols.NumeralExpression;
import com.thoughtworks.problem3.model.symbols.NumeralSymbol;
import com.thoughtworks.problem3.model.symbols.NumeralSymbolTable;
import com.thoughtworks.problem3.model.symbols.RomanNumeral;

/**
 * Unit test fixture for {@link MetalUnitDeclaration}.
 * 
 * @author rgeyer
 *
 */
public class MetalUnitDeclarationTest {
    
    private static final Logger logger = LoggerFactory.getLogger(MetalUnitDeclarationTest.class);
    
    private NumeralSymbolTable numeralTable;
    private QueryContext context;
    
    @Before
    public void setup() {
        numeralTable = new NumeralSymbolTable()
                .add(new NumeralSymbol("one", RomanNumeral.I))
                .add(new NumeralSymbol("dec", RomanNumeral.X))
                .add(new NumeralSymbol("cen", RomanNumeral.C))
                ;
        
        context = createContext();
    }

    @Test
    public void testInterpret() {
        logger.trace("testInterpret()");
        
        assertMetalDeclaration("Gold", createMetalDeclaration("Gold", 5000, "cen"), new BigDecimal(50));
        assertMetalDeclaration("Silver", createMetalDeclaration("Silver", 200, "dec", "dec"), new BigDecimal(10));
    }
    
    private void assertMetalDeclaration(String name, MetalUnitDeclaration metal, BigDecimal unitPrice) {
        metal.interpret(context);
        
        final MetalSymbol symbol = context.lookup(name);
        assertNotNull(symbol);
        assertEquals(name, symbol.getName());
        assertEquals(unitPrice, symbol.getUnitPrice());
    }
    
    private QueryContext createContext() {
        final QueryContext context = new QueryContext();
        for (NumeralSymbol numeral : numeralTable.getAll()) {
            context.addNumeral(numeral);
        }
        return context;
    }

    private MetalUnitDeclaration createMetalDeclaration(String name, int value, String... numerals) {
    
        final NumeralExpression numeralExpr = new NumeralExpression(numeralTable);
        for (String numeral : numerals) {
            numeralExpr.add(numeral);
        }
        final MetalUnitDeclaration decl = new MetalUnitDeclaration(name, numeralExpr, value);
        return decl;
    }
    
}
