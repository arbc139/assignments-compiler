package MiniC.Scanner;

import MiniC.Scanner.SourceFile;
import MiniC.Scanner.Token;

public final class Scanner {

  private SourceFile sourceFile;

  private char currentChar;
  private boolean verbose;
  private StringBuffer currentLexeme;
  private boolean currentlyScanningToken;
  private int currentLineNr;
  private int currentColNr;

  private boolean isDigit(char c) {
    return (c >= '0' && c <= '9');
  }


///////////////////////////////////////////////////////////////////////////////

  public Scanner(SourceFile source) {
    sourceFile = source;
    currentChar = sourceFile.readChar();
    verbose = false;
    currentLineNr = -1;
    currentColNr= -1;
  }

  public void enableDebugging() {
    verbose = true;
  }

  // takeIt appends the current character to the current token, and gets
  // the next character from the source program (or the to-be-implemented
  // "untake" buffer in case of look-ahead characters that got 'pushed back'
  // into the input stream).

  private void takeIt() {
    if (currentlyScanningToken)
    {
      currentLexeme.append(currentChar);
    }
    currentChar = sourceFile.readChar();
  }

  private int scanToken() {

    switch (currentChar) {

    case '0':  case '1':  case '2':  case '3':  case '4':
    case '5':  case '6':  case '7':  case '8':  case '9':
      takeIt();
      while (isDigit(currentChar)) {
        takeIt();
      } 
      // Note: code for floating point literals is missing here...
      return Token.INTLITERAL;
    case '+':
        takeIt();
        return Token.PLUS;
    case '\u0000': // sourceFile.eot:
      currentLexeme.append('$');
      return Token.EOF;
    // Add code here for the remaining MiniC tokens...

    default:
      takeIt();
      return Token.ERROR;
    }
  }

  public Token scan () {
    Token currentToken;
    SourcePos pos;
    int kind;

    currentlyScanningToken = false;
    while (currentChar == ' '
           || currentChar == '\f'
           || currentChar == '\n'
           || currentChar == '\r'
           || currentChar == '\t')
    {
      takeIt();
    } 

    currentlyScanningToken = true;
    currentLexeme = new StringBuffer("");
    pos = new SourcePos();
    // Note: currentLineNr and currentColNr are not maintained yet!
    pos.StartLine = currentLineNr;
    pos.EndLine = currentLineNr;
    pos.StartCol = currentColNr;
    kind = scanToken();
    currentToken = new Token(kind, currentLexeme.toString(), pos);
    pos.EndCol = currentColNr;
    if (verbose)
      currentToken.print();
    return currentToken;
  }

}
