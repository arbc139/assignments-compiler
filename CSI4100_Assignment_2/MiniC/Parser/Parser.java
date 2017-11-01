package MiniC.Parser;


import MiniC.Scanner.Token;
import MiniC.Scanner.SourcePos;
import MiniC.Parser.SyntaxError;
import MiniC.Scanner.Scanner;
import MiniC.ErrorReporter;

public class Parser {

    private Scanner scanner;
    private ErrorReporter errorReporter;
    private Token currentToken;

    public Parser(Scanner lexer, ErrorReporter reporter) {
	scanner = lexer;
        errorReporter = reporter;
    }

    // accept() checks whether the current token matches tokenExpected.
    // If so, it fetches the next token.
    // If not, it reports a syntax error.
    void accept (int tokenExpected) throws SyntaxError {
	if (currentToken.kind == tokenExpected) {
	    currentToken = scanner.scan();
	} else {
	    syntaxError("\"%\" expected here", Token.spell(tokenExpected));
	}
    }

    // acceptIt() unconditionally accepts the current token
    // and fetches the next token from the scanner.
    void acceptIt() {
	currentToken = scanner.scan();
    }

    void syntaxError(String messageTemplate, String tokenQuoted) throws SyntaxError {
	SourcePos pos = currentToken.GetSourcePos();
	errorReporter.reportError(messageTemplate, tokenQuoted, pos);
	throw(new SyntaxError());
    }

    boolean isTypeSpecifier(int token) {
	if(token == Token.VOID ||
           token == Token.INT  ||
           token == Token.BOOL ||
           token == Token.FLOAT) {
	    return true;
	} else {
	    return false;
	}
    }

    ///////////////////////////////////////////////////////////////////////////////
    //
    // toplevel parse() routine:
    //
    ///////////////////////////////////////////////////////////////////////////////

    public void parse() {

	currentToken = scanner.scan(); // get first token from scanner...

	try {
	    parseProgram();
	    if (currentToken.kind != Token.EOF) {
		syntaxError("\"%\" not expected after end of program",
			       currentToken.GetLexeme());
	    }
	}
	catch (SyntaxError s) {return; /* to be refined in Assignment 3...*/ }
	return;
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    //
    // parseProgram():
    //
    // program ::= ( (VOID|INT|BOOL|FLOAT) ID ( FunPart | VarPart ) )*
    //
    ///////////////////////////////////////////////////////////////////////////////

    public void parseProgram() throws SyntaxError {
	while (isTypeSpecifier(currentToken.kind)) {
            acceptIt();
	    accept(Token.ID);
	    if(currentToken.kind == Token.LEFTPAREN) {
		parseFunPart();
	    } else {
		parseVarPart();
	    }
	}
    }


    ///////////////////////////////////////////////////////////////////////////////
    //
    // parseFunPart():
    //
    // FunPart ::= ( "(" ParamsList? ")" CompoundStmt )
    //
    ///////////////////////////////////////////////////////////////////////////////

    public void parseFunPart() throws SyntaxError {
        // We already know that the current token is "(".
        // Otherwise use accept() !
        acceptIt();
        if (isTypeSpecifier(currentToken.kind)) {
	    parseParamsList();
	}
	accept(Token.RIGHTPAREN);
	parseCompoundStmt();
    }


    ///////////////////////////////////////////////////////////////////////////////
    //
    // parseParamsList():
    //
    // ParamsList ::= ParamsDecl ( "," ParamsDecl ) *
    //
    ///////////////////////////////////////////////////////////////////////////////

    public void parseParamsList() throws SyntaxError {
	// to be completed by you...

    } 


    ///////////////////////////////////////////////////////////////////////////////
    //
    // parseCompoundStmt():
    //
    // CompoundStmt ::= "{" VariableDefinition* Stmt* "}"
    //
    ///////////////////////////////////////////////////////////////////////////////

    public void parseCompoundStmt() throws SyntaxError {
	// to be completed by you...

    } 


    ///////////////////////////////////////////////////////////////////////////////
    //
    // parseVarPart():
    //
    // VarPart ::= ( "[" INTLITERAL "]" )?  ( "=" initializer ) ? ( "," init_decl)* ";"
    //
    ///////////////////////////////////////////////////////////////////////////////

    public void parseVarPart() throws SyntaxError {
	// to be completed by you...

    }

    // to be completed by you...

}
