package MiniC.Parser;

import MiniC.Scanner.Scanner;
import MiniC.Scanner.Token;
import MiniC.Scanner.SourcePos;
import MiniC.Parser.SyntaxError;
import MiniC.ErrorReporter;
import MiniC.AstGen.*;

public class Parser {

  private Scanner scanner;
  private ErrorReporter errorReporter;
  private Token currentToken;
  private SourcePos previousTokenPosition;

  public Parser(Scanner lexer, ErrorReporter reporter) {
    scanner = lexer;
    errorReporter = reporter;
  }

  // accept() checks whether the current token matches tokenExpected.
  // If so, it fetches the next token.
  // If not, it reports a syntax error.
  void accept(int tokenExpected) throws SyntaxError {
    if (currentToken.kind == tokenExpected) {
      previousTokenPosition = currentToken.GetSourcePos();
      currentToken = scanner.scan();
    } else {
      syntaxError("\"%\" expected here", Token.spell(tokenExpected));
    }
  }

  // acceptIt() unconditionally accepts the current token
  // and fetches the next token from the scanner.
  void acceptIt() {
    previousTokenPosition = currentToken.GetSourcePos();
    currentToken = scanner.scan();
  }

  // start records the position of the start of a phrase.
  // This is defined to be the position of the first
  // character of the first token of the phrase.
  void start(SourcePos position) {
    position.StartCol = currentToken.GetSourcePos().StartCol;
    position.StartLine = currentToken.GetSourcePos().StartLine;
  }

  // finish records the position of the end of a phrase.
  // This is defined to be the position of the last
  // character of the last token of the phrase.
  void finish(SourcePos position) {
    position.EndCol = previousTokenPosition.EndCol;
    position.EndLine = previousTokenPosition.EndLine;
  }

  void syntaxError(String messageTemplate, String tokenQuoted) throws SyntaxError {
    SourcePos pos = currentToken.GetSourcePos();
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
    throw(new SyntaxError());
  }

  boolean isTypeSpecifier(int token) {
    if (token == Token.VOID || token == Token.INT || token == Token.BOOL ||
        token == Token.FLOAT) {
      return true;
    } else {
      return false;
    }
  }

  boolean isExprFirst(int token) {
    return token == Token.ID || token == Token.LEFTPAREN ||
      token == Token.INTLITERAL || token == Token.BOOLLITERAL ||
      token == Token.FLOATLITERAL || token == Token.STRINGLITERAL ||
      token == Token.PLUS || token == Token.MINUS ||
      token == Token.NOT;
  }

  boolean isRelational(int token) {
    return token == Token.EQ || token == Token.NOTEQ || token == Token.LESSEQ ||
      token == Token.LESS || token == Token.GREATER ||
      token == Token.GREATEREQ;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseArrayIndexDecl (Type T):
  //
  // Take [INTLITERAL] and generate an ArrayType
  //
  ///////////////////////////////////////////////////////////////////////////////
  public ArrayType parseArrayIndexDecl(Type T) throws SyntaxError {
    IntLiteral L;
    IntExpr IE;
    accept(Token.LEFTBRACKET);
    SourcePos pos = currentToken.GetSourcePos();
    L = new IntLiteral(currentToken.GetLexeme(), pos);
    accept(Token.INTLITERAL);
    accept(Token.RIGHTBRACKET);
    IE = new IntExpr(L, pos);
    return new ArrayType(T, IE, previousTokenPosition);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // toplevel parse() routine:
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Program parse() {
    Program ProgramAST = null;

    previousTokenPosition = new SourcePos();
    previousTokenPosition.StartLine = 0;
    previousTokenPosition.StartCol = 0;
    previousTokenPosition.EndLine = 0;
    previousTokenPosition.EndCol = 0;

    currentToken = scanner.scan(); // get first token from scanner...

    try {
      ProgramAST = parseProgram();
      if (currentToken.kind != Token.EOF) {
        syntaxError(
          "\"%\" not expected after end of program", currentToken.GetLexeme());
      }
    }
    catch (SyntaxError s) { return null; }
    return ProgramAST;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseProgram():
  //
  // program ::= ( (VOID|INT|BOOL|FLOAT) ID ( FunPart | VarPart ) )* ";"
  //
  ///////////////////////////////////////////////////////////////////////////////

  // parseProgDecls: recursive helper function to facilitate AST construction.
  public Decl parseProgDecls() throws SyntaxError {
    if (!isTypeSpecifier(currentToken.kind)) {
      return new EmptyDecl(previousTokenPosition);
    }
    SourcePos pos = new SourcePos();
    start(pos);
    Type T = parseTypeSpecifier();
    ID Ident = parseID();
    if (currentToken.kind == Token.LEFTPAREN) {
      Decl newD = parseFunPart(T, Ident, pos);
      return new DeclSequence(newD, parseProgDecls(), previousTokenPosition);
    }
    DeclSequence Vars = parseVarPart(T, Ident);
    DeclSequence VarsTail = Vars.GetRightmostDeclSequenceNode();
    Decl RemainderDecls = parseProgDecls();
    VarsTail.SetRightSubtree(RemainderDecls);
    return Vars;
  }

  public Program parseProgram() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    Decl D = parseProgDecls();
    finish(pos);
    Program P = new Program(D, pos);
    return P;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseFunPart():
  //
  // FunPart ::= ( "(" ParamsList? ")" CompoundStmt )
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Decl parseFunPart(Type T, ID Ident, SourcePos pos) throws SyntaxError {
    // We already know that the current token is "(".
    // Otherwise use accept() !
    acceptIt();
    Decl PDecl = parseParamsList(); // can also be empty...
    accept(Token.RIGHTPAREN);
    CompoundStmt CStmt = parseCompoundStmt();
    finish(pos);
    return new FunDecl(T, Ident, PDecl, CStmt, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseParamsList():
  //
  // ParamsList ::= ParameterDecl ( "," ParameterDecl ) *
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Decl parseParamsList() throws SyntaxError {
    if (!isTypeSpecifier(currentToken.kind)) {
      return new EmptyFormalParamDecl(previousTokenPosition);
    }
    Decl PDecl = parseParameterDecl();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
    }
    return new FormalParamDeclSequence(
      PDecl, parseParamsList(), previousTokenPosition);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseParameterDecl():
  //
  // ParameterDecl ::= (VOID|INT|BOOL|FLOAT) Declarator
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Decl parseParameterDecl() throws SyntaxError {
    Type T = null;
    Decl D = null;

    SourcePos pos = new SourcePos();
    start(pos);
    if (isTypeSpecifier(currentToken.kind)) {
      T = parseTypeSpecifier();
    } else {
      syntaxError(
        "Type specifier instead of % expected", Token.spell(currentToken.kind));
    }
    D = parseDeclarator(T, pos);
    return D;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseDeclarator():
  //
  // Declarator ::= ID ( "[" INTLITERAL "]" )?
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Decl parseDeclarator(Type T, SourcePos pos) throws SyntaxError {
    ID Ident = parseID();
    if (currentToken.kind == Token.LEFTBRACKET) {
      ArrayType ArrT = parseArrayIndexDecl(T);
      finish(pos);
      return new FormalParamDecl(ArrT, Ident, pos);
    }
    finish(pos);
    return new FormalParamDecl(T, Ident, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseVarPart():
  //
  // VarPart ::= ( "[" INTLITERAL "]" )?  ( "=" initializer ) ? ("," init_decl)* ";"
  //
  ///////////////////////////////////////////////////////////////////////////////
  public DeclSequence parseVarPart(Type T, ID Ident) throws SyntaxError {
    Type theType = T;
    Decl D;
    DeclSequence Seq = null;
    Expr E = new EmptyExpr(previousTokenPosition);
    if (currentToken.kind == Token.LEFTBRACKET) {
      theType = parseArrayIndexDecl(T);
    }
    if (currentToken.kind == Token.ASSIGN) {
      acceptIt();
      // You can use the following code after you have implemented
      // parseInitializer():
      E = parseInitializer();
    }
    D = new VarDecl(theType, Ident, E, previousTokenPosition);
    if (currentToken.kind == Token.COMMA) {
      Seq = new DeclSequence(
        D, parseInitDeclList(theType), previousTokenPosition);
    } else {
      Seq = new DeclSequence(
        D, new EmptyDecl(previousTokenPosition), previousTokenPosition);
    }
    accept(Token.SEMICOLON);
    return Seq;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseInitializer():
  //
  // Initializer ::= expr | "{" expr ( "," expr )* "}"
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parseInitializer() throws SyntaxError {
    if (isExprFirst(currentToken.kind)) {
      return parseExpr();
    } else if (currentToken.kind == Token.LEFTBRACE) {
      SourcePos pos = new SourcePos();
      start(pos);
      acceptIt();
      Expr lExp = parseExpr();
      Expr rExp = parseExprSequence();
      accept(Token.RIGHTBRACE);
      finish(pos);
      return new ExprSequence(lExp, rExp, pos);
    }
    syntaxError("", "");
    return null;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseExprSequence():
  //
  // ExprSequence ::== ( "," expr )*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parseExprSequence() throws SyntaxError {
    if (currentToken.kind != Token.COMMA) {
      return new EmptyExpr(previousTokenPosition);
    }
    acceptIt();
    return new ExprSequence(
      parseExpr(), parseExprSequence(), previousTokenPosition);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseInitDeclList():
  //
  // InitDeclList ::= ("," InitDecl)*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Decl parseInitDeclList(Type t) throws SyntaxError {
    if (currentToken.kind != Token.COMMA) {
      return new EmptyDecl(previousTokenPosition);
    }
    accept(Token.COMMA);
    return new DeclSequence(
      parseInitDecl(t), parseInitDeclList(t), previousTokenPosition);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseInitDecl():
  //
  // InitDecl ::= ID ( "[" INTLITERAL "]" )? ( "=" initializer )?
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Decl parseInitDecl(Type t) throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    ID id = parseID();
    Type theType = t;
    if (currentToken.kind == Token.LEFTBRACKET) {
      theType = parseArrayIndexDecl(t);
    }
    if (currentToken.kind == Token.ASSIGN) {
      acceptIt();
      return new VarDecl(theType, id, parseInitializer(), previousTokenPosition);
    }
    return new VarDecl(
      theType, id, new EmptyExpr(previousTokenPosition), previousTokenPosition);
  }


  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseExpr():
  //
  // Expr ::= AndExpr ( "||" AndExpr )*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parseExpr() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    Expr lExpr = parseAndExpr();
    if (currentToken.kind != Token.OR) {
      finish(pos);
      return lExpr;
    }
    Expr rExpr = parseAndExprList();
    finish(pos);
    return new ExprSequence(lExpr, rExpr, pos);
  }

  public Expr parseAndExprList() throws SyntaxError {
    if (currentToken.kind != Token.OR) {
      return new EmptyExpr(previousTokenPosition);
    }
    acceptIt();
    return new ExprSequence(
      parseAndExpr(), parseAndExprList(), previousTokenPosition);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseAndExpr():
  //
  // AndExpr ::= RelationalExpr ( "&&" RelationalExpr )*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parseAndExpr() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    Expr lExpr = parseRelationalExpr();
    if (currentToken.kind != Token.AND) {
      finish(pos);
      return lExpr;
    }
    Expr rExpr = parseRelationalExprList();
    finish(pos);
    return new ExprSequence(lExpr, rExpr, pos);
  }

  public Expr parseRelationalExprList() throws SyntaxError {
    if (currentToken.kind != Token.AND) {
      return new EmptyExpr(previousTokenPosition);
    }
    acceptIt();
    return new ExprSequence(
      parseRelationalExpr(), parseRelationalExprList(), previousTokenPosition);
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
  public Expr parseRelationalExpr() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    Expr lExpr = parseAddExpr();
    if (!isRelational(currentToken.kind)) {
      finish(pos);
      return lExpr;
    }
    acceptIt();
    Expr rExpr = parseAddExpr();
    finish(pos);
    return new ExprSequence(lExpr, rExpr, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseAddExpr():
  //
  // AddExpr ::= MultExpr ( ("+" | "-") MultExpr )*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parseAddExpr() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    Expr lExpr = parseMultExpr();
    if (currentToken.kind != Token.PLUS && currentToken.kind != Token.MINUS) {
      finish(pos);
      return lExpr;
    }
    Expr rExpr = parseMultExprList();
    finish(pos);
    return new ExprSequence(lExpr, rExpr, pos);
  }

  public Expr parseMultExprList() throws SyntaxError {
    if (currentToken.kind != Token.PLUS && currentToken.kind != Token.MINUS) {
      return new EmptyExpr(previousTokenPosition);
    }
    acceptIt();
    return new ExprSequence(
      parseMultExpr(), parseMultExprList(), previousTokenPosition);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseMultExpr():
  //
  // MultExpr ::= UnaryExpr ( ("*" | "/") UnaryExpr )*
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parseMultExpr() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    Expr lExpr = parseUnaryExpr();
    if (currentToken.kind != Token.TIMES && currentToken.kind != Token.DIV) {
      finish(pos);
      return lExpr;
    }
    Expr rExpr = parseUnaryExprList();
    finish(pos);
    return new ExprSequence(lExpr, rExpr, pos);
  }

  public Expr parseUnaryExprList() throws SyntaxError {
    if (currentToken.kind != Token.TIMES && currentToken.kind != Token.DIV) {
      return new EmptyExpr(previousTokenPosition);
    }
    acceptIt();
    return new ExprSequence(
      parseUnaryExpr(), parseUnaryExprList(), previousTokenPosition);
  }


  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseUnaryExpr():
  //
  // UnaryExpr ::= ("+"|"-"|"!")* PrimaryExpr
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parseUnaryExpr() throws SyntaxError {
    while (currentToken.kind == Token.PLUS ||
        currentToken.kind == Token.MINUS ||
        currentToken.kind == Token.NOT) {
      Operator opAST = new Operator(
        currentToken.GetLexeme(), previousTokenPosition);
      acceptIt();
      return new UnaryExpr(opAST, parseUnaryExpr(), previousTokenPosition);
    }
    return parsePrimaryExpr();
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parsePrimaryExpr():
  //
  // PrimaryExpr ::= ID arglist?
  //              |  ID "[" expr "]"
  //              |  "(" expr ")"
  //              |  INTLITERAL | BOOLLITERAL | FLOATLITERAL | STRINGLITERAL
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parsePrimaryExpr() throws SyntaxError {
    switch (currentToken.kind) {
      case Token.ID: {
        SourcePos pos = new SourcePos();
        start(pos);
        ID ident = parseID();
        Expr expr = new EmptyExpr(previousTokenPosition);
        if (currentToken.kind == Token.LEFTPAREN) {
          expr = parseArgList();
        } else if (currentToken.kind == Token.LEFTBRACKET) {
          acceptIt();
          expr = parseExpr();
          accept(Token.RIGHTBRACKET);
        }
        finish(pos);
        return new CallExpr(ident, expr, pos);
      }
      case Token.LEFTPAREN: {
        acceptIt();
        Expr expr = parseExpr();
        accept(Token.RIGHTPAREN);
        return expr;
      }
      case Token.INTLITERAL:
      case Token.BOOLLITERAL:
      case Token.FLOATLITERAL:
      case Token.STRINGLITERAL:
        return parseLiteralExpr(currentToken.kind);
      default:
        syntaxError("", "");
    }
    return null;
  }

  public Expr parseLiteralExpr(int literalTokenKind) throws SyntaxError {
    switch (literalTokenKind) {
      case Token.INTLITERAL: {
        IntLiteral literal = new IntLiteral(
          currentToken.GetLexeme(), currentToken.GetSourcePos());
        acceptIt();
        return new IntExpr(literal, previousTokenPosition);
      }
      case Token.BOOLLITERAL: {
        BoolLiteral literal = new BoolLiteral(
          currentToken.GetLexeme(), currentToken.GetSourcePos());
        acceptIt();
        return new BoolExpr(literal, previousTokenPosition);
      }
      case Token.FLOATLITERAL: {
        FloatLiteral literal = new FloatLiteral(
          currentToken.GetLexeme(), currentToken.GetSourcePos());
        acceptIt();
        return new FloatExpr(literal, previousTokenPosition);
      }
      case Token.STRINGLITERAL: {
        StringLiteral literal = new StringLiteral(
          currentToken.GetLexeme(), currentToken.GetSourcePos());
        acceptIt();
        return new StringExpr(literal, previousTokenPosition);
      }
      default:
        syntaxError("", "");
    }
    return null;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseCompoundStmt():
  //
  // CompoundStmt ::= "{" VariableDef* Stmt* "}"
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Decl parseCompoundDecls() throws SyntaxError {
    if (!isTypeSpecifier(currentToken.kind)) {
      return new EmptyDecl(previousTokenPosition);
    }
    Type T = parseTypeSpecifier();
    ID Ident = parseID();
    DeclSequence Vars = parseVarPart(T, Ident);
    DeclSequence VarsTail = Vars.GetRightmostDeclSequenceNode();
    Decl RemainderDecls = parseCompoundDecls();
    VarsTail.SetRightSubtree(RemainderDecls);
    return Vars;
  }

  public Stmt parseCompoundStmts () throws SyntaxError {
    if (currentToken.kind != Token.LEFTBRACE &&
        currentToken.kind != Token.IF &&
        currentToken.kind != Token.WHILE &&
        currentToken.kind != Token.FOR &&
        currentToken.kind != Token.RETURN &&
        currentToken.kind != Token.ID) {
      return new EmptyStmt(previousTokenPosition);
    }
    Stmt S = null;
    // You can use the following code after implementation of parseStmt():
    S = parseStmt();
    return new StmtSequence(S, parseCompoundStmts(), previousTokenPosition);
  }

  public CompoundStmt parseCompoundStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    accept(Token.LEFTBRACE);
    Decl D = parseCompoundDecls();
    Stmt S = parseCompoundStmts();
    accept(Token.RIGHTBRACE);
    finish(pos);
    if ((D.getClass() == EmptyDecl.class) &&
        (S.getClass() == EmptyStmt.class)) {
      return new EmptyCompoundStmt(previousTokenPosition);
    }
    return new CompoundStmt(D, S, pos);
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
  public Stmt parseStmt() throws SyntaxError {
    switch (currentToken.kind) {
      case Token.LEFTBRACE:
        return parseCompoundStmt();
      case Token.IF:
        return parseIfStmt();
      case Token.WHILE:
        return parseWhileStmt();
      case Token.FOR:
        return parseForStmt();
      case Token.RETURN:
        return parseReturnStmt();
      case Token.ID: {
        SourcePos pos = new SourcePos();
        start(pos);
        ID ident = parseID();
        if (currentToken.kind == Token.ASSIGN) {
          Expr varExpr = new VarExpr(ident, previousTokenPosition);
          accept(Token.ASSIGN);
          Expr expr = parseExpr();
          accept(Token.SEMICOLON);
          finish(pos);
          return new AssignStmt(varExpr, expr, pos);
        } else if (currentToken.kind == Token.LEFTBRACKET) {
          Expr varExpr = new VarExpr(ident, previousTokenPosition);
          accept(Token.LEFTBRACKET);
          Expr indexExpr = parseExpr();
          accept(Token.RIGHTBRACKET);
          finish(pos);
          Expr arrayExpr = new ArrayExpr(varExpr, indexExpr, pos);
          accept(Token.ASSIGN);
          Expr expr = parseExpr();
          accept(Token.SEMICOLON);
          finish(pos);
          return new AssignStmt(arrayExpr, expr, pos);
        } else if (currentToken.kind == Token.LEFTPAREN) {
          Expr arglist = parseArgList();
          accept(Token.SEMICOLON);
          finish(pos);
          Expr callExpr = new CallExpr(ident, arglist, pos);
          return new CallStmt(callExpr, pos);
        } else {
          syntaxError("expect = or [ or (", "");
        }
      }
      default:
        syntaxError("expect = or [ or (", "");
    }
    return null;
  }


  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseIfStmt():
  //
  // IfStmt ::= "if" "(" Expr ")" Stmt ( "else" Stmt ) ?
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Stmt parseIfStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    accept(Token.IF);
    accept(Token.LEFTPAREN);
    Expr expr = parseExpr();
    accept(Token.RIGHTPAREN);
    Stmt thenStmt = parseStmt();
    if (currentToken.kind != Token.ELSE) {
      finish(pos);
      return new IfStmt(expr, thenStmt, pos);
    }
    accept(Token.ELSE);
    Stmt elseStmt = parseStmt();
    finish(pos);
    return new IfStmt(expr, thenStmt, elseStmt, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseWhileStmt():
  //
  // WhileStmt ::= "while" "(" Expr ")" Stmt
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Stmt parseWhileStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    accept(Token.WHILE);
    accept(Token.LEFTPAREN);
    Expr expr = parseExpr();
    accept(Token.RIGHTPAREN);
    Stmt stmt = parseStmt();
    finish(pos);
    return new WhileStmt(expr, stmt, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseForStmt():
  //
  // ForStmt ::= "for" "(" AssignExpr? ";" Expr? ";" AssignExpr? ")" Stmt
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Stmt parseForStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    accept(Token.FOR);
    accept(Token.LEFTPAREN);
    Expr startExpr = new EmptyExpr(previousTokenPosition);
    if (currentToken.kind == Token.ID) {
      startExpr = parseAssignExpr();
    }
    accept(Token.SEMICOLON);
    Expr conditionExpr = new EmptyExpr(previousTokenPosition);
    if (isExprFirst(currentToken.kind)) {
      conditionExpr = parseExpr();
    }
    accept(Token.SEMICOLON);
    Expr mutateExpr = new EmptyExpr(previousTokenPosition);
    if (currentToken.kind == Token.ID) {
      mutateExpr = parseAssignExpr();
    }
    accept(Token.RIGHTPAREN);
    finish(pos);
    Stmt stmt = parseStmt();
    return new ForStmt(startExpr, conditionExpr, mutateExpr, stmt, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseReturnStmt():
  //
  // ReturnStmt ::= "return" Expr? ";"
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Stmt parseReturnStmt() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    accept(Token.RETURN);
    Expr expr = new EmptyExpr(previousTokenPosition);
    if (isExprFirst(currentToken.kind)) {
      expr = parseExpr();
    }
    accept(Token.SEMICOLON);
    finish(pos);
    return new ReturnStmt(expr, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseAssignExpr():
  //
  // AssignExpr ::= "ID" "=" Expr
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parseAssignExpr() throws SyntaxError {
    SourcePos pos = new SourcePos();
    start(pos);
    ID ident = parseID();
    Expr varExpr = new VarExpr(ident, previousTokenPosition);
    accept(Token.ASSIGN);
    Expr expr = parseExpr();
    finish(pos);
    return new AssignExpr(varExpr, expr, pos);
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseArgList():
  //
  // ArgList ::= "(" ( expr ( "," expr )* )? ")"
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Expr parseArgs() throws SyntaxError {
    if (currentToken.kind == Token.RIGHTPAREN) {
      return new EmptyActualParam(previousTokenPosition);
    }
    Expr Params = null;
    Params = new ActualParam(parseExpr(), previousTokenPosition);
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
    }
    return new ActualParamSequence(Params, parseArgs(), previousTokenPosition);
  }

  public Expr parseArgList() throws SyntaxError {
    accept(Token.LEFTPAREN);
    Expr Params = parseArgs();
    accept(Token.RIGHTPAREN);
    return Params;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseID():
  //
  // ID (terminal)
  //
  ///////////////////////////////////////////////////////////////////////////////
  public ID parseID() throws SyntaxError {
    ID Ident = new ID(currentToken.GetLexeme(), currentToken.GetSourcePos());
    accept(Token.ID);
    return Ident;
  }

  ///////////////////////////////////////////////////////////////////////////////
  //
  // parseTypeSpecifier():
  //
  // VOID | INT | FLOAT | BOOL (all terminals)
  //
  ///////////////////////////////////////////////////////////////////////////////
  public Type parseTypeSpecifier() throws SyntaxError {
    Type T = null;
    switch (currentToken.kind) {
      case Token.INT:
        T = new IntType(currentToken.GetSourcePos());
        break;
      case Token.FLOAT:
        T = new FloatType(currentToken.GetSourcePos());
        break;
      case Token.BOOL:
        T = new BoolType(currentToken.GetSourcePos());
        break;
      case Token.VOID:
        T = new VoidType(currentToken.GetSourcePos());
        break;
      default:
        syntaxError("Type specifier expected", "");
    }
    acceptIt();
    return T;
  }
}
