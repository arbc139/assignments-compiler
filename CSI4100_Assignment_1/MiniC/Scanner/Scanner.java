package MiniC.Scanner;

import java.util.Stack;
import java.util.regex.Pattern;

import MiniC.Scanner.SourceFile;
import MiniC.Scanner.Token;

public final class Scanner {

  private enum ScannerState {
    NONE, TOKEN, COMMENT,
  }

  private SourceFile sourceFile;
  private Stack streamStack;

  private char currentChar;
  private boolean verbose;
  private StringBuffer currentLexeme;
  private StringBuffer commentLexeme;
  private ScannerState state;
  private int currentLineNr;
  private int currentColNr;

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

  private boolean isExponentBreakPoint(char c) {
    return Pattern.matches("[eE]", String.valueOf(c));
  }

  private boolean isPlusMinus(char c) {
    return Pattern.matches("[\\-\\+]", String.valueOf(c));
  }

///////////////////////////////////////////////////////////////////////////////

  public Scanner(SourceFile source) {
    sourceFile = source;
    streamStack = new Stack();
    currentChar = sourceFile.readChar();
    verbose = false;
    currentLineNr = 1;
    currentColNr= 1;
    state = ScannerState.NONE;
  }

  public void enableDebugging() {
    verbose = true;
  }

  // takeIt appends the current character to the current token, and gets
  // the next character from the source program (or the to-be-implemented
  // "untake" buffer in case of look-ahead characters that got 'pushed back'
  // into the input stream).

  private void takeIt() {
    if (state == ScannerState.TOKEN) {
      currentLexeme.append(currentChar);
    } else if (state == ScannerState.COMMENT) {
      commentLexeme.append(currentChar);
    }
    if (streamStack.isEmpty()) {
      currentChar = sourceFile.readChar();
    } else {
      currentChar = (char) streamStack.pop();
    }
  }

  private void untake() {
    streamStack.add(currentChar);
    if (state == ScannerState.TOKEN) {
      currentChar = currentLexeme.charAt(currentLexeme.length() - 1);
      currentLexeme.deleteCharAt(currentLexeme.length() - 1);
    } else if (state == ScannerState.COMMENT) {
      currentChar = commentLexeme.charAt(commentLexeme.length() - 1);
      commentLexeme.deleteCharAt(commentLexeme.length() - 1);
    }
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

  private int scanSepearator() {
    switch (currentChar) {
      case '{':
        takeIt();
        return Token.LEFTBRACE;
      case '}':
        takeIt();
        return Token.RIGHTBRACE;
      case '(':
        takeIt();
        return Token.LEFTBRACKET;
      case ')':
        takeIt();
        return Token.RIGHTBRACKET;
      case '[':
        takeIt();
        return Token.LEFTPAREN;
      case ']':
        takeIt();
        return Token.RIGHTPAREN;
      case ';':
        takeIt();
        return Token.SEMICOLON;
      default:
        takeIt();
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

  private int scanExponent() {
    if (!isExponentBreakPoint(currentChar)) {
      return Token.ERROR;
    }
    takeIt();
    if (isPlusMinus(currentChar)) {
      takeIt();
    }
    if (isDigit(currentChar)) {
      takeIt();
      while (isDigit(currentChar)) { takeIt(); }
      return Token.FLOATLITERAL;
    }
    while (isExponentBreakPoint(currentChar)) {
      untake();
    }
    return Token.ERROR;
  }

  private int scanFloatFractionPart() {
    if (currentChar == '.') {
      takeIt();
      if (isDigit(currentChar)) {
        takeIt();
        while (isDigit(currentChar)) { takeIt(); }
        if (isExponentBreakPoint(currentChar)) {
          scanExponent();
        }
        return Token.FLOATLITERAL;
      } else if (isExponentBreakPoint(currentChar)) {
        scanExponent();
        return Token.FLOATLITERAL;
      } else {
        return Token.FLOATLITERAL;
      }
    } else if (isExponentBreakPoint(currentChar)) {
      int exponentToken = scanExponent();
      if (exponentToken == Token.ERROR) {
        return Token.INTLITERAL;
      }
      return Token.FLOATLITERAL;
    }
    return Token.ERROR;
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
    return scanFloatFractionPart();
  }

  private int scanStringLiteral() {
    if (currentChar != '"') {
      return Token.ERROR;
    }
    takeIt();
    while (currentChar != '"') {
      if (currentChar == '\n') {
        return Token.ERROR;
      }
      if (currentChar == '\r') {
        takeIt();
        if (currentChar == '\n') {
          untake();
          return Token.ERROR;
        }
      }
      takeIt();
    }
    takeIt();
    return Token.STRINGLITERAL;
  }

  private int scanToken() {
    if (isOperator(currentChar)) {
      return scanOperator();
    } else if (isSeperator(currentChar)) {
      return scanSepearator();
    } else if (isLetter(currentChar)) {
      return scanIdentifierKeywordBoolLiteral();
    } else if (currentChar == '.') {
      takeIt();
      if (isDigit(currentChar)) {
        takeIt();
        while (isDigit(currentChar)) { takeIt(); }
        if (isExponentBreakPoint(currentChar)) {
          scanExponent();
        }
        return Token.FLOATLITERAL;
      }
      return Token.ERROR;
    } else if (isDigit(currentChar)) {
      return scanIntFloatLiteral();
    } else if (currentChar == '"') {
      return scanStringLiteral();
    } else if (currentChar == '\u0000') {
      currentLexeme.append('$');
      return Token.EOF;
    } else {
      // Add code here for the remaining MiniC tokens...
      takeIt();
      return Token.ERROR;
    }
  }

  private void handleSingleComment() {
    while (true) {
      if (currentChar == '\n') {
        takeIt();
        currentColNr = 1;
        currentLineNr++;
        return;
      }
      if (currentChar == '\r') {
        takeIt();
        if (currentChar == '\n') {
          takeIt();
          currentColNr = 1;
          currentLineNr++;
          return;
        }
        continue;
      }
      takeIt();
    }
  }

  private void handleMultiComment() {
    while (true) {
      if (currentChar == '\u0000') {
        return;
      }
      if (currentChar == '*') {
        takeIt();
        currentColNr++;
        if (currentChar == '/') {
          takeIt();
          currentColNr++;
          return;
        }
      }
      if (currentChar == '\n') {
        takeIt();
        currentColNr = 1;
        currentLineNr++;
        continue;
      }
      if (currentChar == '\r') {
        takeIt();
        currentColNr++;
        if (currentChar == '\n') {
          takeIt();
          currentColNr = 1;
          currentLineNr++;
          continue;
        }
        continue;
      }
      takeIt();
      currentColNr++;
    }
  }

  private void handleWhiteSpace() {
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
  }

  public Token scan() {
    Token currentToken;
    SourcePos pos;
    int kind;

    state = ScannerState.NONE;
    handleWhiteSpace();

    state = ScannerState.COMMENT;
    commentLexeme = new StringBuffer("");
    while (currentChar == '/' && currentChar != '\u0000') {
      if (currentChar == '/') {
        takeIt();
        currentColNr++;
        if (currentChar == '/') {
          takeIt();
          currentColNr++;
          handleSingleComment();
        } else if (currentChar == '*') {
          takeIt();
          currentColNr++;
          handleMultiComment();
        } else {
          untake();
          currentColNr--;
          break;
        }
      }
      handleWhiteSpace();
    }

    state = ScannerState.TOKEN;
    currentLexeme = new StringBuffer("");
    pos = new SourcePos();
    pos.StartLine = currentLineNr;
    pos.EndLine = currentLineNr;
    pos.StartCol = currentColNr;
    kind = scanToken();
    currentToken = new Token(kind, currentLexeme.toString(), pos);
    currentColNr += currentLexeme.toString().length() - 1;
    pos.EndCol = currentColNr;
    currentColNr++;
    if (verbose) currentToken.print();
    return currentToken;
  }
}
