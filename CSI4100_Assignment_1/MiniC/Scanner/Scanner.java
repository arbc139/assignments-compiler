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
    switch (currentChar) {
      case '=':
        takeIt();
        if (currentChar == '=') {
          takeIt();
          return Token.EQ;
        }
        return Token.ASSIGN;
      case '|':
        takeIt();
        if (currentChar == '|') {
          takeIt();
          return Token.OR;
        }
        return Token.ERROR;
      case '&':
        takeIt();
        if (currentChar == '&') {
          takeIt();
          return Token.AND;
        }
        return Token.ERROR;
      case '!':
        takeIt();
        if (currentChar == '=') {
          takeIt();
          return Token.NOTEQ;
        }
        return Token.NOT;
      case '<':
        takeIt();
        if (currentChar == '=') {
          takeIt();
          return Token.LESSEQ;
        }
        return Token.LESS;
      case '>':
        takeIt();
        if (currentChar == '=') {
          takeIt();
          return Token.GREATEREQ;
        }
        return Token.GREATER;
      case '+':
        takeIt();
        return Token.PLUS;
      case '-':
        takeIt();
        return Token.MINUS;
      case '*':
        takeIt();
        return Token.TIMES;
      case '/':
        takeIt();
        return Token.DIV;
      default:
        takeIt();
        return Token.ERROR;
    }
  }

  private int scanToken() {
    int tokenType = Token.ERROR;

    if (isOperator(currentChar)) {
      tokenType = scanOperator();
    } else if (isLetter(currentChar)) {
      takeIt();
      while (isLetter(currentChar) || isDigit(currentChar)) {
        takeIt();
      }
      tokenType = Token.ID;
    } else if (isDigit(currentChar)) {
      takeIt();
      while (isDigit(currentChar)) {
        takeIt();
      }
      // Note: code for floating point literals is missing here...
      tokenType = Token.INTLITERAL;
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
