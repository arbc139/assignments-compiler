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
  void accept(int tokenExpected) throws SyntaxError {
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

  void syntaxError(String messageTemplate, String tokenQuoted)
      throws SyntaxError {
    SourcePos pos = currentToken.GetSourcePos();
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
	  throw(new SyntaxError());
  }

  boolean isTypeSpecifier(int token) {
    return token == Token.VOID || token == Token.INT  || token == Token.BOOL ||
      token == Token.FLOAT;
  }

  boolean isRelational(int token) {
    return token == Token.EQ || token == Token.NOTEQ || token == Token.LESSEQ ||
      token == Token.LESS || token == Token.GREATER ||
      token == Token.GREATEREQ;
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
        syntaxError(
          "\"%\" not expected after end of program",
          currentToken.GetLexeme());
      }
    }
    catch (SyntaxError s) { return; /* to be refined in Assignment 3...*/ }
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
    parseParamsDecl();
    while (currentToken.kind == Token.COMMA) {
      accept(Token.COMMA);
      parseParamsDecl();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseParamsDecl():
  //
  // ParamsList ::= (VOID|INT|BOOL|FLOAT) Declarator
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseParamsDecl() throws SyntaxError {
    if (!isTypeSpecifier(currentToken.kind)) {
      syntaxError("Type specifier expected here.");
    }
    acceptIt();
    parseDeclarator();
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseDeclarator():
  //
  // Declarator ::= ID ( "[" INTLITERAL "]" )?
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseDeclarator() throws SyntaxError {
    accept(Token.ID);
    if (currentToken.kind == Token.LEFTBRACKET) {
      acceptIt();
      accept(Token.INTLITERAL);
      accept(Token.RIGHTBRACKET);
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseCompoundStmt():
  //
  // CompoundStmt ::= "{" VariableDefinition* Stmt* "}"
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseCompoundStmt() throws SyntaxError {
    accept(Token.LEFTBRACE);
    while (isTypeSpecifier(currentToken)) {
      acceptIt();
      accept(Token.ID);
      parseVarPart();
    }
    while (
      currentToken == Token.LEFTBRACE ||  // CompoundStmt
      currentToken == Token.IF ||  // IfStmt
      currentToken == Token.WHILE ||  // WhileStmt
      currentToken == Token.FOR ||  // ForStmt
      currentToken == Token.RETURN ||  // return ...
      currentToken == Token.ID) {  // ID ...
      parseStmt();
    }
    accept(Token.RIGHTBRACE);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseStmt():
  //
  // Stmt ::= CompoundStmt | IfStmt | WhileStmt | ForStmt |
  //    "return" Expr? ";" |
  //    "ID" (
  //        "=" Expr ";" |
  //        "[" Expr "]" "=" Expr ";" |
  //        ArgList ";"
  //    )
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseStmt() throws SyntaxError {
    switch (currentToken) {
      case Token.LEFTBRACE:
        parseCompoundStmt();
        break;
      case Token.IF:
        parseIfStmt();
        break;
      case Token.WHILE:
        parseWhileStmt();
        break;
      case Token.FOR:
        parseForStmt();
        break;
      case Token.RETURN:
        accept(Token.RETURN);
        if (/*TODO(dykim): Expr의 첫번째 character*/true) {
          parseExpr();
        }
        accept(Token.SEMICOLON);
        break;
      case Token.ID:
        accept(Token.ID);
        if (currentToken == Token.ASSIGN) {
          accept(Token.ASSIGN);
          parseExpr();
          accept(Token.SEMICOLON);
        } else if (currentToken == Token.LEFTBRACKET) {
          accept(Token.LEFTBRACKET);
          parseExpr();
          accept(Token.RIGHTBRACKET);
          accept(Token.ASSIGN);
          parseExpr();
          accept(Token.SEMICOLON);
        } else if (currentToken == Token.LEFTPAREN) {
          accept(Token.LEFTPAREN);
          parseArgList();
          accept(Token.SEMICOLON);
        } else {
          syntaxError("expect = or [ or (");
        }
        break;
      default:
        syntaxError("expect = or [ or (");
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseIfStmt():
  //
  // IfStmt ::= "if" "(" Expr ")" Stmt ( "else" Stmt ) ?
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseIfStmt() throws SyntaxError {
    accept(Token.IF);
    accept(Token.LEFTBRACE);
    parseExpr();
    accept(Token.RIGHTBRACE);
    parseStmt();
    if (currentToken == Token.ELSE) {
      accept(Token.ELSE);
      parseStmt();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseWhileStmt():
  //
  // WhileStmt ::= "while" "(" Expr ")" Stmt
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseWhileStmt() throws SyntaxError {
    accept(Token.WHILE);
    accept(Token.LEFTBRACE);
    parseExpr();
    accept(Token.RIGHTBRACE);
    parseStmt();
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseForStmt():
  //
  // ForStmt ::= "for" "(" AssignExpr? ";" Expr? ";" AssignExpr? ")" Stmt
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseForStmt() throws SyntaxError {
    accept(Token.FOR);
    accept(Token.LEFTBRACE);
    if (currentToken == Token.ID) {
      parseAssignExpr();
    }
    accept(Token.SEMICOLON);
    if (/*TODO(dykim): Expr의 첫번째 character*/true) {
      parseExpr();
    }
    accept(Token.SEMICOLON);
    if (currentToken == Token.ID) {
      parseAssignExpr();
    }
    accept(Token.RIGHTBRACE);
    parseStmt();
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseAssignExpr():
  //
  // AssignExpr ::= "ID" "=" Expr
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseAssignExpr() throws SyntaxError {
    accept(Token.ID);
    accept(Token.ASSIGN);
    parseExpr();
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseExpr():
  //
  // Expr ::= AndExpr ( "||" AndExpr )*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseExpr() throws SyntaxError {
    parseAndExpr();
    while (currentToken == Token.OR) {
      parseAndExpr();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseAndExpr():
  //
  // AndExpr ::= RelationalExpr ( "&&" RelationalExpr )*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseAndExpr() throws SyntaxError {
    parseRelationalExpr();
    while (currentToken == Token.AND) {
      parseRelationalExpr();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseRelationalExpr():
  //
  // RelationalExpr ::= AddExpr (
  //    "==" AddExpr |
  //    "!=" AddExpr |
  //    "<" AddExpr |
  //    "<=" AddExpr |
  //    ">" AddExpr |
  //    ">=" AddExpr
  // ) ?
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseRelationalExpr() throws SyntaxError {
    parseAddExpr();
    if (isRelational(currentToken)) {
      acceptIt();
      parseAddExpr();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseAddExpr():
  //
  // AddExpr ::= MultExpr ( ("+" | "-") MultExpr )*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseAddExpr() throws SyntaxError {
    parseMultExpr();
    while (currentToken == Token.PLUS || currentToken == Token.MINUS) {
      acceptIt();
      parseMultExpr();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseMultExpr():
  //
  // MultExpr ::= UnaryExpr ( ("*" | "/") UnaryExpr )*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseMultExpr() throws SyntaxError {
    parseUnaryExpr();
    while (currentToken == Token.TIMES || currentToken == Token.DIV) {
      acceptIt();
      parseUnaryExpr();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseUnaryExpr():
  //
  // UnaryExpr ::= PrimaryExpr (
  //   "+" UnaryExpr |
  //   "-" UnaryExpr |
  //   "!" UnaryExpr
  // ) ?
  //
  ///////////////////////////////////////////////////////////////////////////////
  public void parseUnaryExpr() throws SyntaxError {
    parsePrimaryExpr();
    if (currentToken == Token.PLUS || currentToken == Token.MINUS ||
        currentToken == Token.NOT) {
      acceptIt();
      parseUnaryExpr();
    }
  }

  // TODO(dykim): 여기서부터 쭉 뒤에 것들도 개발해야함.
  // parsePrimaryExpr부터

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
