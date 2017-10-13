package MiniC.Scanner;

import java.util.regex.Pattern;

import MiniC.Scanner.SourceFile;
import MiniC.Scanner.Token;

public final class Scanner {

  private enum ScannerState {
    NORMAL, SINGLE_LINE_COMMENT, MULTI_LINE_COMMENT, STRING_LITERAL,
  }

  private SourceFile sourceFile;

  private char currentChar;
  private boolean verbose;
  private StringBuffer currentLexeme;
  private boolean currentlyScanningToken;
  private int currentLineNr;
  private int currentColNr;
  private ScannerState state;

  private boolean isDigit(char c) {
    return Pattern.matches("\\d", String.valueOf(c));
  }

  private boolean isLetter(char c) {
    return Pattern.matches("[a-zA-z_]", String.valueOf(c));
  }

  private boolean isWhiteSpace(char c) {
    return Pattern.matches("\\s", String.valueOf(c));
  }

  private boolean isOperator(char c) {
    return Pattern.matches(
      "[\\=\\|\\&\\!\\<\\>\\+\\-\\*\\/]", String.valueOf(c));
  }

  private boolean isSeperator(char c) {
    return Pattern.matches("[\\{\\}\\(\\)\\[\\]\\,\\;]", String.valueOf(c));
  }

  private boolean isFractionBreakPoint(char c) {
    return Pattern.matches("[\\.eE]", String.valueOf(c));
  }

///////////////////////////////////////////////////////////////////////////////

  public Scanner(SourceFile source) {
    sourceFile = source;
    currentChar = sourceFile.readChar();
    verbose = false;
    currentLineNr = 1;
    currentColNr= 1;
    state = ScannerState.NORMAL;
  }

  public void enableDebugging() {
    verbose = true;
  }

  // takeIt appends the current character to the current token, and gets
  // the next character from the source program (or the to-be-implemented
  // "untake" buffer in case of look-ahead characters that got 'pushed back'
  // into the input stream).

  private void takeIt() {
    if (currentlyScanningToken) {
      currentLexeme.append(currentChar);
    }
    currentChar = sourceFile.readChar();
  }

  private int scanOperator() {
    takeIt();
    switch (currentChar) {
      case '=':
        if (currentChar == '=') {
          takeIt();
          return Token.EQ;
        }
        return Token.ASSIGN;
      case '|':
        if (currentChar == '|') {
          takeIt();
          return Token.OR;
        }
        return Token.ERROR;
      case '&':
        if (currentChar == '&') {
          takeIt();
          return Token.AND;
        }
        return Token.ERROR;
      case '!':
        if (currentChar == '=') {
          takeIt();
          return Token.NOTEQ;
        }
        return Token.NOT;
      case '<':
        if (currentChar == '=') {
          takeIt();
          return Token.LESSEQ;
        }
        return Token.LESS;
      case '>':
        if (currentChar == '=') {
          takeIt();
          return Token.GREATEREQ;
        }
        return Token.GREATER;
      case '+':
        return Token.PLUS;
      case '-':
        return Token.MINUS;
      case '*':
        return Token.TIMES;
      case '/':
        return Token.DIV;
      default:
        return Token.ERROR;
    }
  }

  private int scanSepearator() {
    takeIt();
    switch (currentChar) {
      case '{':
        return Token.LEFTBRACE;
      case '}':
        return Token.RIGHTBRACE;
      case '(':
        return Token.LEFTBRACKET;
      case ')':
        return Token.RIGHTBRACKET;
      case '[':
        return Token.LEFTPAREN;
      case ']':
        return Token.RIGHTPAREN;
      case ';':
        return Token.SEMICOLON;
      default:
        return Token.ERROR;
    }
  }

  private int getKeywordToken(String currentLexemeStr) {
    switch (currentLexemeStr) {
      case "bool":
        return Token.BOOL;
      case "else":
        return Token.ELSE;
      case "for":
        return Token.FOR;
      case "float":
        return Token.FLOAT;
      case "if":
        return Token.IF;
      case "int":
        return Token.INT;
      case "return":
        return Token.RETURN;
      case "void":
        return Token.VOID;
      case "while":
        return Token.WHILE;
      case "true":
        // Fall through...
      case "false":
        return Token.BOOLLITERAL;
      default:
        return Token.ID;
    }
  }

  private int scanIdentifierKeywordBoolLiteral() {
    takeIt();
    while (isLetter(currentChar) || isDigit(currentChar)) {
      takeIt();
    }
    return getKeywordToken(currentLexeme.toString());
  }

  private int scanIntFloatLiteral() {
    takeIt();
    while (isDigit(currentChar)) {
      takeIt();
    }
    if (!isFractionBreakPoint(currentChar)) {
      return Token.INTLITERAL;
    }
    // Fraction handling...
    // TODO(totoro): Fraction 뒤에 핸들링 구현해야햄 알아찌 내 자신아? (하트)
  }

  private int scanToken() {
    int tokenType = Token.ERROR;
    if (isOperator(currentChar)) {
      tokenType = scanOperator();
    } else if (isSeperator(currentChar)) {
      tokenType = scanSepearator();
    } else if (isLetter(currentChar)) {
      tokenType = scanIdentifierKeywordBoolLiteral();
    } else if (isDigit(currentChar)) {
      tokenType = scanIntFloatLiteral();
    } else if (currentChar == '\u0000') {
      currentLexeme.append('$');
      tokenType = Token.EOF;
    } else {
      // Add code here for the remaining MiniC tokens...
      takeIt();
      tokenType = Token.ERROR;
    }
    return tokenType;
  }

  public Token scan() {
    Token currentToken;
    SourcePos pos;
    int kind;

    currentlyScanningToken = false;
    while (isWhiteSpace(currentChar)) {
      if (currentChar == '\n') {
        takeIt();
        currentColNr = 1;
        currentLineNr++;
        continue;
      }
      if (currentChar == '\r') {
        takeIt();
        if (currentChar == '\n') {
          takeIt();
          currentColNr = 1;
          currentLineNr++;
          continue;
        }
        currentColNr++;
        continue;
      }
      takeIt();
      currentColNr++;
    }

    currentlyScanningToken = true;
    currentLexeme = new StringBuffer("");
    pos = new SourcePos();
    pos.StartLine = currentLineNr;
    pos.EndLine = currentLineNr;
    pos.StartCol = currentColNr;
    kind = scanToken();
    currentToken = new Token(kind, currentLexeme.toString(), pos);
    currentColNr += currentLexeme.toString().length() - 1;
    pos.EndCol = currentColNr;
    if (verbose) currentToken.print();
    return currentToken;
  }
}
