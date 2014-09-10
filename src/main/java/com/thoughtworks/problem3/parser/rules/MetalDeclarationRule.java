package com.thoughtworks.problem3.parser.rules;

import com.thoughtworks.problem3.model.QueryContext;
import com.thoughtworks.problem3.model.MetalUnitDeclaration;
import com.thoughtworks.problem3.model.symbols.NumeralExpression;
import com.thoughtworks.problem3.parser.TokenBuffer;
import com.thoughtworks.problem3.parser.TokenType;

/**
 * Parses the following grammar:
 * 
 * metal_declaration ::= numeral_expression metal_identifier "is" integer_literal "Credits"
 * 
 * @author rgeyer
 *
 */
public class MetalDeclarationRule extends TopLevelRule {

    private static final String REGEX = "^([^how][a-z]+\\s+)+[A-Z][a-z]*.*";
    
    private final NumeralExpressionRule numeralExpression;
    private final MetalIdentifierRule metalIdentifier;
    private final IntegerLiteralRule integerLiteral;

    public MetalDeclarationRule(TokenBuffer tokens) {
        super(tokens, REGEX);
        
        numeralExpression = new NumeralExpressionRule(tokens);
        metalIdentifier = new MetalIdentifierRule(tokens);
        integerLiteral = new IntegerLiteralRule(tokens);
    }

    @Override
    public boolean parse(QueryContext context) {
        final NumeralExpression expr = new NumeralExpression(context.getNumerals());
        numeralExpression.parse(context, expr);
        
        final String identifier = super.parseIdentifier(context, metalIdentifier);
        super.validateAndConsumeToken(TokenType.IS_KEYWORD);
        final int integer = super.parseInteger(context, integerLiteral);
        super.validateEOF();
        
        final MetalUnitDeclaration decl = new MetalUnitDeclaration(identifier, expr, integer);
        decl.interpret(context);

        return true;
    }

}
