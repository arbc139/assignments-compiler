package MiniC;

import MiniC.Scanner.Scanner;
import MiniC.Scanner.Token;
import MiniC.Scanner.SourceFile;

public class MiniC{

    private static Scanner scanner;

    static void compileProgram (String sourceName) {
        Token t;

        System.out.println("********** " +
                           "MiniC Compiler" +
                           " **********");

        System.out.println("Lexical Analysis ...");

        SourceFile source = new SourceFile(sourceName);

        scanner  = new Scanner(source);
        scanner.enableDebugging();
        do {
          t = scanner.scan(); // scan 1 token
        } while (t.kind != Token.EOF);

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
