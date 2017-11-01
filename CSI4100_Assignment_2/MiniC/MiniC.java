package MiniC;

import MiniC.Scanner.Scanner;
import MiniC.Scanner.SourceFile;
import MiniC.Parser.Parser;

public class MiniC{

    private static Scanner scanner;
    private static Parser parser;
    private static ErrorReporter reporter;

    static void compileProgram (String sourceName) {

        System.out.println("********** " +
                           "MiniC Compiler" +
                           " **********");

        SourceFile source = new SourceFile(sourceName);

        System.out.println("Syntax Analysis ...");

        scanner  = new Scanner(source);
        /*
         * Enable this to observe the sequence of tokens
         * delivered by the scanner:
         *
         */
        //scanner.enableDebugging();
        reporter = new ErrorReporter();
        parser   = new Parser(scanner, reporter);
        parser.parse();	    // 1st pass
        /*
         * The following loop was used with the first assignment
         * to repeatedly request tokens from the scanner.
         * The above call to parser.parse() has replaced it
         * with Assignment 2.
         *
        do {
          t = scanner.scan(); // scan 1 token
        } while (t.kind != Token.EOF);
        */

	boolean successful = (reporter.numErrors == 0);
        if (successful) {
            System.out.println("Compilation was successful.");
        } else {
            System.out.println("Compilation was unsuccessful.");
        }
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: MiniC filename");
            System.exit(1);
        }

        String sourceName = args[0];
        compileProgram(sourceName);
    }
}
