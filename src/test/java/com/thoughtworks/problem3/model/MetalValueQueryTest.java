/**
 * 
 */
package com.thoughtworks.problem3.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.MetalValueQuery;
import com.thoughtworks.problem3.model.symbols.MetalSymbol;
import com.thoughtworks.problem3.model.symbols.MetalSymbolTable;
import com.thoughtworks.problem3.model.symbols.NumeralExpression;
import com.thoughtworks.problem3.model.symbols.NumeralSymbol;
import com.thoughtworks.problem3.model.symbols.NumeralSymbolTable;
import com.thoughtworks.problem3.model.symbols.RomanNumeral;

/**
 * Unit test fixture for {@link MetalValueQuery}.
 * 
 * @author rgeyer
 *
 */
public class MetalValueQueryTest {
    
    private static final Logger logger = LoggerFactory.getLogger(MetalValueQueryTest.class);
    
    private NumeralSymbolTable numeralTable;
    private MetalSymbolTable metalTable;
    private QueryContext context;
    
    @Before
    public void setup() {
        numeralTable = new NumeralSymbolTable()
                .add(new NumeralSymbol("one", RomanNumeral.I))
                .add(new NumeralSymbol("dec", RomanNumeral.X))
                .add(new NumeralSymbol("cen", RomanNumeral.C))
                ;
        metalTable = new MetalSymbolTable()
                .add(new MetalSymbol("Silver", new BigDecimal(100)))
                .add(new MetalSymbol("Gold", new BigDecimal(1000)))
                ;
        context = createContext();
    }

    @Test
    public void testInterpret() {
        logger.trace("testInterpret()");
        
        assertQueryDeclaration("Silver", createQuery("Silver", "dec", "dec"), 20, new BigDecimal(2000));
        assertQueryDeclaration("Gold", createQuery("Gold", "cen", "cen"), 200, new BigDecimal(200000));
    }
    
    private void assertQueryDeclaration(String name, MetalValueQuery query, int amount, BigDecimal total) {
        logger.trace("assertQueryDeclaration(): name={}", name);
        
        query.interpret(context);
        assertEquals(total, query.calculateValue(context, amount));
    }
    
    private MetalValueQuery createQuery(String name, String...numerals) {
        NumeralExpression expr = new NumeralExpression(numeralTable);
        for (String numeral : numerals) {
            expr.add(numeral);
        }
        return new MetalValueQuery(name, expr);
    }

    private QueryContext createContext() {
        final QueryContext context = new QueryContext();
        for (NumeralSymbol numeral : numeralTable.getAll()) {
            context.addNumeral(numeral);
        }
        for (MetalSymbol metal : metalTable.getAll()) {
            context.addMetal(metal);
        }
        return context;
    }
    
}
