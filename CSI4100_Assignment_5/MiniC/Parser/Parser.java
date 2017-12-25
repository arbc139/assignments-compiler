package MiniC.Parser;

import MiniC.Scanner.Scanner;
import MiniC.Scanner.Token;
import MiniC.Scanner.SourcePos;
import MiniC.Parser.SyntaxError;
import MiniC.ErrorReporter;
import MiniC.AstGen.*;


public class Parser {

  //
  // NOTE:
  //
  // This is a dummy parser implementation that is only used when the
  // course participant wishes to use the provided parser class file.
  // The dummy implementation makes the MiniC framework compileable.
  // After compiling, the class-file of the dummy parser is replaced
  // by the provided parser's class file.
  //
  // Students who wish to use their own parser should replace this file with
  // their own parser from Assignment 1.

  public Parser(Scanner lexer, ErrorReporter reporter) {
    System.out.println("ERROR: empty parser implementation used!");
    System.out.println("Provide your own Parser.java or use the provided"
                       + " class files!");
    System.out.println("See the Assignment specification on how to use the"
                       + " provided classfiles.");
    System.exit(1);
  }


  public Program parse() {
    Program ProgramAST = null;
    return ProgramAST;
  }

}
