/**
 * 
 */
package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.NumeralDeclaration;
import com.thoughtworks.problem3.model.symbols.RomanNumeral;
import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;

/**
 * Parses the metal symbol declaration. 
 * <p>
 * The BNF for this declaration is:
 * <p><code>
 * numeral_declaration ::= numeral_identifier "is" roman_literal
 * numeral_identifier ::= "a..z" { "a..z" }
 * roman_literal ::= 'I' | 'V' | 'X' | 'L' | 'C' | 'D' | 'M'
 * </code>
 * 
 * @author rgeyer
 *
 */
public class NumeralDeclarationRule extends TopLevelRule {

    private final NumeralIdentifierRule identifier;
    private final RomanLiteralRule romanLiteral;
    
    private static final String REGEX = "^[^how][a-z]+\\s+is.*";
    
    public NumeralDeclarationRule(TokenBuffer tokens) {
        super(tokens, REGEX);
        this.identifier = new NumeralIdentifierRule(tokens);
        this.romanLiteral = new RomanLiteralRule(tokens);
    }

    @Override
    public boolean parse(QueryContext context) {
        final String symbolId = super.parseIdentifier(context, identifier);
        super.validateAndConsumeToken(TokenType.IS_KEYWORD);
        final String numeral = super.parseIdentifier(context, romanLiteral);
        super.validateEOF();

        final NumeralDeclaration decl = 
                new NumeralDeclaration(symbolId, RomanNumeral.from(numeral));
        decl.interpret(context);
        
        return true;
    }

}
