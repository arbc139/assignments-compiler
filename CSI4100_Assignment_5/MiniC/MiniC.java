package MiniC;

import MiniC.Scanner.Scanner;
import MiniC.Scanner.SourceFile;
import MiniC.Parser.Parser;
import MiniC.SemanticAnalysis.SemanticAnalysis;
import MiniC.CodeGen.Emitter;
import MiniC.StdEnvironment;
import MiniC.AstGen.Program;
import MiniC.TreeDrawer.Drawer;
import MiniC.TreePrinter.Printer;
import MiniC.Unparser.Unparser;

public class MiniC{

    private static Scanner scanner;
    private static Parser parser;
    private static SemanticAnalysis sem;
    private static Emitter emitter;
    private static ErrorReporter reporter;
    private static Drawer drawer;
    private static Printer printer;
    private static Unparser unparser;
    private static StdEnvironment stdenv;
    /* The abstract syntax tree representing
     * the source program:
     */
    private static Program AST;
    //commandline args:
    private static String sourceName;
    private static boolean DrawTree1, DrawTree2, DrawStdEnvTree, PrintTree, UnparseTree;
    private static String PrintTreeF, UnparseTreeF;


    static void compileProgram (String sourceName) {

        System.out.println("********** " +
                           "MiniC Compiler" +
                           " **********");


        SourceFile source = new SourceFile(sourceName);

        scanner  = new Scanner(source);
        /*
         * Enable this to observe the sequence of tokens
         * delivered by the scanner:
         *
         */
        //scanner.enableDebugging();
        reporter = new ErrorReporter();
	stdenv   = new StdEnvironment();
        parser   = new Parser(scanner, reporter);
	sem      = new SemanticAnalysis(reporter);
        emitter  = new Emitter(sourceName, reporter);
        drawer   = new Drawer();
	printer  = new Printer();
	unparser = new Unparser();

	if(DrawStdEnvTree) {
            Drawer envdrawer = new Drawer();
	    envdrawer.draw(stdenv.AST);
	}

        System.out.println("Syntax Analysis ...");
        AST = parser.parse();	    // 1st pass

        if (reporter.numErrors == 0) {
	    if(PrintTree) {
		printer.print(AST, PrintTreeF);
	    }
	    if(UnparseTree) {
		unparser.unparse(AST, UnparseTreeF);
	    }
	    if(DrawTree1) {
		drawer.draw(AST);
            }
            System.out.println ("Semantic Analysis ...");
            sem.check(AST);	// 2nd pass
	    if(DrawTree2) {
               drawer.draw(AST);
	    }
            if (reporter.numErrors == 0) {
               System.out.println ("Code Generation ...");
               emitter.genCode(AST);
            }
	}

	boolean successful = (reporter.numErrors == 0);
	if(successful) {
	    System.out.println("Compilation was successful.");
	} else {
            System.out.println("Compilation was unsuccessful.");
	}
    }

    public static void usage() {
	System.out.println("Usage: MiniC [options] filename");
	System.out.println("Option: -ast1 to draw the AST before semantic analysis");
	System.out.println("Option: -ast2 to draw the AST after semantic analysis");
	System.out.println("Option: -envast to draw the StdEnvironment AST"); 
	System.out.println("Option: -t <file> to dump the AST to <file>");
	System.out.println("Option: -u <file> to unparse the AST to <file>");
	System.exit(1);
    }

    public static void processCmdLine(String[] args) {
	DrawTree1 = false;
	DrawTree2 = false;
	DrawStdEnvTree = false;
	PrintTree = false;
	PrintTreeF = "";
	UnparseTree = false;
	UnparseTreeF = "";
        sourceName = "";
	int arg_index = 0;
	while (arg_index < args.length) {
	    if (args[arg_index].equals("-ast1")) {
		DrawTree1 = true;
		arg_index++;
            } else if (args[arg_index].equals("-ast2")) {
		DrawTree2 = true;
		arg_index++;
	    } else if (args[arg_index].equals("-envast")) {
		DrawStdEnvTree = true;
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
                if (arg_index < args.length) {
                   // After the input source file no arg is allowed:
                   usage();
                }
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
