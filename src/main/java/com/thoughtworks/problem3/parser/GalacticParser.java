/**
 * 
 */
package com.thoughtworks.problem3.parser;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.parser.rules.MetalDeclarationRule;
import com.thoughtworks.problem3.parser.rules.MetalValueQueryRule;
import com.thoughtworks.problem3.parser.rules.NumeralDeclarationRule;
import com.thoughtworks.problem3.parser.rules.NumeralTranslationQueryRule;
import com.thoughtworks.problem3.parser.rules.Rule;
import com.thoughtworks.problem3.parser.rules.TopLevelRule;


/**
 * Parses the galactic translation external DSL, with the following 4 translation queries,
 * with accompanying examples:
 * <ol>
 * <li>Numeral declaration (e.g. glob is I)</li>
 * <li>Metal unit declaration (e.g. glob glob Silver is 34 Credits)</li>
 * <li>Numeral translation query (e.g. how much is pish tegj glob glob ?)</li>
 * <li>Metal value query (e.g. how many Credits is glob prok Silver ?)</li>
 * </ol>
 * <p>
 * Each query maps to a TopLevelRule for parsing.
 * <p>
 * The modified BNF grammar for the external DSL is: 
 * <p>
 * <code>
 * numeral_declaration ::= numeral_identifier "is" roman_literal<br/>
 * numeral_identifier ::= "a..z" { "a..z" }<br/>
 * roman_literal ::= 'I' | 'V' | 'X' | 'L' | 'C' | 'D' | 'M'<br/>
 * <p>
 * metal_unit_declaration ::= numeral_expression metal_identifier "is" integer_literal "Credits"<br/>
 * numeral_expression ::= numeral_identifier { numeral_identifier }<br/>
 * metal_identifier ::= "A..Z" { "a..z" }<br/>
 * integer_literal ::= "1..9" { "0..9" }<br/>
 * <p>
 * numeral_translation_query ::= "how" "much" "is" numeral_expression ['?']<br/>
 * <p>
 * metal_value_query ::= "how" "many" "Credits" "is" numeral_expression metal_identifier ['?']<br/>
 * </code>
 * <p>
 * Each command is parsed separately, with the top level rule determined by Regular Expression.
 * 
 * @author rgeyer
 *
 */
public class GalacticParser {
    
    private static final String ERROR_MSG = "I have no idea what you are talking about";
    
    private final Lexer lexer;
    private final QueryContext context;
    private final TokenBuffer tokens = new TokenBuffer();
    private final List<TopLevelRule> topLevelRules = new ArrayList<TopLevelRule>();
    
    public GalacticParser(Lexer lexer, QueryContext context) {
        this.lexer = lexer;
        this.context = context;
        initializeRules();
    }

    private void initializeRules() {
        // add top level grammar rules
        topLevelRules.add(new NumeralTranslationQueryRule(tokens));
        topLevelRules.add(new MetalValueQueryRule(tokens));
        topLevelRules.add(new NumeralDeclarationRule(tokens));
        topLevelRules.add(new MetalDeclarationRule(tokens));
    }

    public void parse(String input) {
        
        try {
            // reset the TokenBuffer
            tokens.reset(lexer.lex(input));
            
            final Rule start = findTopLevelRule(input);
            start.parse(context);
        } catch (Exception t) {
            context.print(ERROR_MSG);
        }
    }

    // package level scope for unit testing
    Rule findTopLevelRule(String input) {
        for (TopLevelRule rule : topLevelRules) {
            if (rule.matches(input)) {
                return rule;
            }
        }
        throw createSyntaxException(input);
    }
    
    // package level scope for unit testing
    ParseException createSyntaxException(String input) {
        final String msg = String.format("Syntax error; unexpected input=%s", input); 
        return new ParseException(msg);
    }


}
