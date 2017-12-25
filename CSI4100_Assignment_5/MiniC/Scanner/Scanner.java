package MiniC.Scanner;

import MiniC.Scanner.SourceFile;
import MiniC.Scanner.Token;

public final class Scanner {

  //
  // NOTE:
  //
  // This is a dummy scanner implementation that is only used when the
  // course participant wishes to use the provided scanner class file.
  // The dummy implementation makes the MiniC framework compileable.
  // After compiling, the class-file of the dummy scanner is replaced
  // by the provided scanner's class file.
  //
  // Students who wish to use their own scanner should replace this file with
  // their own scanner from Assignment 1.

  public Scanner(SourceFile source) {
    System.out.println("ERROR: empty scanner implementation used!");
    System.out.println("Provide your own Scanner.java or use the provided"
                       + " class files!");
    System.out.println("See the Assignment specification on how to use the"
                       + " provided classfiles.");
    System.exit(1);
  }

  public void enableDebugging() {
    return;
  }

  public Token scan () {
    SourcePos pos = new SourcePos();
    Token ErrorToken = new Token(Token.ERROR, "Empty Scanner!", pos);
    return ErrorToken;
  }

}
