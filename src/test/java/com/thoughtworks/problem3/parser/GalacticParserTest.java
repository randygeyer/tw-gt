/**
 * 
 */
package com.thoughtworks.problem3.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.symbols.MetalSymbol;
import com.thoughtworks.problem3.model.symbols.NumeralSymbol;
import com.thoughtworks.problem3.parser.GalacticParser;
import com.thoughtworks.problem3.parser.Lexer;
import com.thoughtworks.problem3.parser.ParseException;
import com.thoughtworks.problem3.parser.rules.MetalDeclarationRule;
import com.thoughtworks.problem3.parser.rules.MetalValueQueryRule;
import com.thoughtworks.problem3.parser.rules.NumeralDeclarationRule;
import com.thoughtworks.problem3.parser.rules.NumeralTranslationQueryRule;

/**
 * Unit test fixture for {@link GalacticParser}.
 * 
 * @author rgeyer
 *
 */
public class GalacticParserTest {
    
    private static final Logger logger = LoggerFactory.getLogger(GalacticParserTest.class);
    
    private QueryContext context;
    private GalacticParser parser;

    @Before
    public void setup() {
        context = new QueryContext();
        parser = new GalacticParser(new Lexer(), context);
    }
    
    @Test
    public void testParse_NumeralDeclaration() {
        logger.trace("testParse_NumeralDeclaration()");
        
        assertNumeralDeclaration("glob is I", "glob", 'I');
        assertNumeralDeclaration("blah is V", "blah", 'V');
        assertNumeralDeclaration("dohh is X", "dohh", 'X');
    }
    
    private void assertNumeralDeclaration(String input, String symbol, char roman) {
        logger.trace("assertNumeralDeclaration(): input={}", input);

        parser.parse(input);
        final NumeralSymbol numeral = context.getNumerals().lookup(symbol);
        assertNotNull(numeral);
        assertEquals(roman, numeral.getRomanNumeral().symbol());
    }
    
    @Test
    public void testParse_MetalDeclaration() {
        logger.trace("testParse_MetalDeclaration()");
        
        loadContext();
    }
    
    private void assertMetalDeclaration(String input, String symbol, int value) {
        logger.trace("assertMetalDeclaration(): input={}", input);

        parser.parse(input);
        final MetalSymbol metal = context.getMetals().lookup(symbol);
        assertNotNull(metal);
        assertEquals(new BigDecimal(value), metal.getUnitPrice());
    }
    
    @Test
    public void testParse_NumeralTranslationQuery() {
        logger.trace("testParse_NumeralTranslationQuery()");
        
        assertNumeralDeclaration("uno is I", "uno", 'I');
        assertNumeralDeclaration("dec is X", "dec", 'X');
        assertNumeralDeclaration("cen is C", "cen", 'C');
        
        assertNumeralTranslationQuery("how much is cen dec uno uno ?");
    }
    
    private void assertNumeralTranslationQuery(String input) {
        logger.trace("assertNumeralTranslationQuery(): input={}", input);

        parser.parse(input);
    }

    @Test
    public void testParse_MetalValueQuery() {
        logger.trace("testParse_MetalValueQuery()");
        
        loadContext();
        assertMetalValueQuery("how many Credits is glob glob Silver ?");
    }
    
    private void assertMetalValueQuery(String input) {
        logger.trace("assertMetalValueQuery(): input={}", input);

        parser.parse(input);
    }

    @Test
    public void testParse_Failure() {
        logger.trace("testParse_Failure()");
        
        loadContext();
        // no way to validate, but should print unknown response to console
        assertNumeralTranslationQuery("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    }

    private void loadContext() {
        assertNumeralDeclaration("glob is I", "glob", 'I');
        assertNumeralDeclaration("dohh is X", "dohh", 'X');
        assertMetalDeclaration("glob Silver is 34 Credits", "Silver", 34);
        assertMetalDeclaration("glob glob Gold is 34 Credits", "Gold", 17);
        assertMetalDeclaration("glob dohh Plat is 90 Credits", "Plat", 10);
    }
    
    @Test
    public void testCreateSyntaxException() {
        logger.trace("testCreateSyntaxException()");
        
        final String INPUT = "bogus";
        
        final ParseException ex = parser.createSyntaxException(INPUT);
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains(INPUT));
    }
    
    @Test
    public void testFindTopLevelRule_Success() {
        logger.trace("testFindTopLevelRule_Success()");
        
        assertRule("glob is I", NumeralDeclarationRule.class);
        
        assertRule("glob Silver is 34 Credits", MetalDeclarationRule.class);
        assertRule("glob glob Silver is 34 Credits", MetalDeclarationRule.class);
        
        assertRule("how much is pish tegj glob glob ?", NumeralTranslationQueryRule.class);
        
        assertRule("how many Credits is glob prok Silver ?", MetalValueQueryRule.class);
    }
    
    @Test
    public void testFindTopLevelRule_Failure() {
        logger.trace("testFindTopLevelRule_Failure()");
        
        assertRuleFailed(null);
        assertRuleFailed("");
        assertRuleFailed(" ");
        assertRuleFailed("how will ");
    }
    
    public void assertRule(String input, Class<?> rule) {
        logger.trace("assertRule(): rule={}; input={}", rule.getSimpleName(), input);
        
        assertTrue(parser.findTopLevelRule(input).getClass().equals(rule));
    }
    
    public void assertRuleFailed(String input) {
        
        try {
            parser.findTopLevelRule(input);
            logger.trace("assertRuleFailed(): input={}", input);
            fail("findTopLevelRule() should have failed for input=" + input);
        } catch (ParseException ex) {
            logger.trace("assertRuleFailed(): input={}; message={}", input, ex.getMessage());
        }
    }

}
