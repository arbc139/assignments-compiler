package MiniC;

import MiniC.Scanner.Scanner;
import MiniC.Scanner.SourceFile;
import MiniC.Parser.Parser;
import MiniC.AstGen.Program;
import MiniC.TreeDrawer.Drawer;
import MiniC.TreePrinter.Printer;
import MiniC.Unparser.Unparser;

public class MiniC {

    private static Scanner scanner;
    private static Parser parser;
    private static ErrorReporter reporter;
    private static Drawer drawer;
    private static Printer printer;
    private static Unparser unparser;
    /* The abstract syntax tree representing
     * the source program:
     */
    private static Program AST;
    //commandline args:
    private static String sourceName;
    private static boolean DrawTree, DrawTreePlusPos, PrintTree, UnparseTree;
    private static String PrintTreeF, UnparseTreeF;


    static void compileProgram (String sourceName) {
        System.out.println("********** " +
                           "MiniC Compiler" +
                           " **********");

        System.out.println("Syntax Analysis ...");

        SourceFile source = new SourceFile(sourceName);

        scanner  = new Scanner(source);
        /*
         * Enable this to observe the sequence of tokens
         * delivered by the scanner:
         *
         */
        //scanner.enableDebugging();
        reporter = new ErrorReporter();
        parser   = new Parser(scanner, reporter);
        drawer   = new Drawer();
	printer  = new Printer();
	unparser = new Unparser();
        AST = parser.parse();	    // 1st pass

	boolean successful = (reporter.numErrors == 0);
        if (successful) {
	    if(PrintTree) {
		printer.print(AST, PrintTreeF);
	    }
	    if(UnparseTree) {
		unparser.unparse(AST, UnparseTreeF);
	    }
	    if(DrawTree || DrawTreePlusPos) {
		drawer.draw(AST, DrawTreePlusPos);
            }
	    System.out.println("Compilation was successful.");
	} else {
            System.out.println("Compilation was unsuccessful.");
	}
    }

    public static void usage() {
	System.out.println("Usage: MiniC filename");
	System.out.println("Options: -ast to draw the AST");
	System.out.println("Options: -astp to draw the AST plus source pos"); 
	System.out.println("Options: -t <file> to dump the AST to <file>");
	System.out.println("Options: -u <file> to unparse the AST to <file>");
	System.exit(1);
    }

    public static void processCmdLine(String[] args) {
	DrawTree = false;
        DrawTreePlusPos = false;
	PrintTree = false;
	PrintTreeF = "";
	UnparseTree = false;
	UnparseTreeF = "";
        sourceName = "";
	int arg_index = 0;
	while (arg_index < args.length) {
	    if (args[arg_index].equals("-ast")) {
		DrawTree = true;
		arg_index++;
	    } else if (args[arg_index].equals("-astp")) {
		DrawTreePlusPos = true;
		arg_index++;
	    } else if (args[arg_index].equals("-t")) {
		PrintTree = true;
		if (args.length < arg_index + 1) {
		    usage();
		} else {
		    arg_index++;
                    PrintTreeF = args[arg_index];
                    arg_index++;
		}
	    } else if (args[arg_index].equals("-u")) {
		UnparseTree = true;
		if (args.length < arg_index + 1) {
                    usage();
		} else {
                    arg_index++;
                    UnparseTreeF = args[arg_index];
                    arg_index++;
		}
	    } else {
		sourceName = args[arg_index];
		arg_index++;
	    }
	}
	if (sourceName.equals("")) {
	    usage();
	}
    }

    public static void main(String[] args) {
	processCmdLine(args);
	compileProgram(sourceName);
    }

}
