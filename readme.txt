Problem Three: Merchant's Guide To The Galaxy

This app translates galactic numbers and units used during galactic 
transactions of Earth resources, e.g. Silver, Gold, etc.  The queries 
are specified using an external DSL (Domain Specific Language) for 
entering translation data and submitting translation queries.

The app accepts 4 types of text line queries read from an input file, 
or typed interactively.  The query types fall into two categories, 
declarations and translation queries.  The commands types are:
 
1. Numeral symbol declaration, e.g. glob is I
2. Metal unit declaration, e.g. glob glob Silver is 34 Credits
3. Numeral translation query, e.g. how much is pish tegj glob glob ?
4. Metal value query, e.g. how many Credits is glob prok Silver ?

These query types form the basis of the application Semantic Model. 

Queries are parsed using the following regular BNF grammar:

command* ::= declaration_statement | query_statement

declaration_statement* ::= numeral_declaration | metal_unit_declaration
numeral_declaration ::= numeral_identifier "is" roman_literal
numeral_identifier ::= "a..z" { "a..z" }
roman_literal ::= 'I' | 'V' | 'X' | 'L' | 'C' | 'D' | 'M'

metal_unit_declaration ::= numeral_expression metal_identifier "is" integer_literal "Credits"
numeral_expression ::= numeral_identifier { numeral_identifier }
metal_identifier ::= "A..Z" { "a..z" }
integer_literal ::= "1..9" { "0..9" }

query_statement* ::= (numeral_translation_query | metal_value_query) 
numeral_translation_query ::= "how" "much" "is" numeral_expression ['?']
metal_value_query ::= "how" "many" "Credits" "is" numeral_expression metal_identifier ['?']

Grammar rules with an asterisk are parsed using Regular Expressions (see the 
GalacticParser class).  The remaining rules are parsed by tokenizing the input 
(Lexer class) and syntax analysis is done by Rule subclasses.  Input lines are 
parsed one line and interpreted one line at a time. The Rule subclasses are 
"smart", and build the semantic model as the input line is parsed. No further 
analysis is required, so no AST (or parse tree) is needed.  After successfully 
building a Query subclass, the Rules invoke the interpret() method which 
processes the query.

The semantic model consists of Query subclasses that model each of the query 
types, which access symbols through a QueryContext which contains the symbol 
tables (MetalSymbols & NumeralSymbols). As mentioned above, the Query subclasses 
implement the Interpreter pattern.

===============================================================================

The app is written in Java 7.  The project can be built using Maven 3.X.

Runtime requirements:

* Java 7 runtime is installed 
  http://www.oracle.com/technetwork/java/javase/downloads/java-se-jre-7-download-432155.html
* java executable is on the path

Run the app by typing the following at a shell command prompt:
 
  java -jar gt.jar [path]<inputFile>
  
  e.g. java -jar gt.jar ./input.txt

A sample input.txt file is provided in the root directory of the zip file.

=====================================================================================================

The ThoughtWorks problem definition follows, copied from the Code Assignment email:

You decided to give up on earth after the latest financial collapse left 99.99% of 
the earth's population with 0.01% of the wealth. Luckily, with the scant sum of money 
that is left in your account, you are able to afford to rent a spaceship, leave earth, 
and fly all over the galaxy to sell common metals and dirt (which apparently is worth 
a lot).

Buying and selling over the galaxy requires you to convert numbers and units, and you 
decided to write a program to help you.

The numbers used for intergalactic transactions follows similar convention 
to the roman numerals and you have painstakingly collected the appropriate 
translation between them.

Numbers are formed by combining symbols together and adding the values. For 
example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are placed 
in order of value, starting with the largest values. When smaller values 
precede larger values, the smaller values are subtracted from the larger 
values, and the result is added to the total. For example MCMXLIV = 1000 + 
(1000 − 100) + (50 − 10) + (5 − 1) = 1944.

* The symbols "I", "X", "C", and "M" can be repeated three times in succession, 
  but no more. (They may appear four times if the third and fourth are separated 
  by a smaller value, such as XXXIX.) "D", "L", and "V" can never be repeated.
* "I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" 
  and "C" only.  "C" can be subtracted from "D" and "M" only. "V", "L", and "D" 
  can never be subtracted.
* Only one small-value symbol may be subtracted from any large-value symbol.
* A number written in Arabic numerals can be broken into digits. For example, 
  1903 is composed of 1, 9, 0, and 3. To write the Roman numeral, each of the 
  non-zero digits should be treated separately. In the above example, 1,000 = 
  M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII.

Input to your program consists of lines of text detailing your notes on the 
conversion between intergalactic units and roman numerals.

You are expected to handle invalid queries appropriately.

Test input:
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?

Test Output:
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 782 Credits
I have no idea what you are talking about