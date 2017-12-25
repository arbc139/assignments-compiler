package MiniC;

import MiniC.Scanner.SourcePos;

public class ErrorReporter {

  int numErrors;

  ErrorReporter()
  {
    numErrors = 0;
  }

  public void reportError(String message, String tokenName, SourcePos pos) {
    System.out.print ("ERROR: ");

    for (int c = 0; c < message.length(); c++) {
        if (message.charAt(c) == '%') {
	    System.out.print(tokenName);
	} else {
	    System.out.print(message.charAt(c));
	}
    }
    System.out.println(" " + pos.StartCol + ".." + pos.EndCol + ", line " + pos.StartLine + ".");
    numErrors++;
  }

}