/**
 * 
 */
package com.thoughtworks.problem3.model;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.NumeralTranslationQuery;
import com.thoughtworks.problem3.model.symbols.NumeralExpression;
import com.thoughtworks.problem3.model.symbols.NumeralSymbol;
import com.thoughtworks.problem3.model.symbols.NumeralSymbolTable;
import com.thoughtworks.problem3.model.symbols.RomanNumeral;

/**
 * Unit test fixture for {@link NumeralTranslationQuery}.
 * 
 * @author rgeyer
 *
 */
public class NumeralTranslationQueryTest {
    
    private static final Logger logger = LoggerFactory.getLogger(NumeralTranslationQueryTest.class);
    
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
        
        assertQueryDeclaration(createQuery("dec", "dec"), 20);
        assertQueryDeclaration(createQuery("cen", "cen"), 200);
    }
    
    private void assertQueryDeclaration(NumeralTranslationQuery query, int total) {
        logger.trace("assertQueryDeclaration(): total={}", total);
        
        query.interpret(context);
    }
    
    private NumeralTranslationQuery createQuery(String... numerals) {
        NumeralExpression expr = new NumeralExpression(numeralTable);
        for (String numeral : numerals) {
            expr.add(numeral);
        }
        return new NumeralTranslationQuery(expr);
    }

    private QueryContext createContext() {
        final QueryContext context = new QueryContext();
        for (NumeralSymbol numeral : numeralTable.getAll()) {
            context.addNumeral(numeral);
        }
        return context;
    }
    
}
