package MiniC.TreePrinter;

import java.io.*;
import MiniC.AstGen.Program;

public class Printer {

    public void print(Program ast, String FileName) {
	try {
	    // Create file 
	    FileWriter fstream = new FileWriter(FileName);
	    BufferedWriter out = new BufferedWriter(fstream);
            // Create a TreePrinterVisitor and visit the AST:
            TreePrinterVisitor pv = new TreePrinterVisitor(out);
            ast.accept(pv);
	    //Close the output stream
	    out.close();
	} catch (Exception e) {
            //Catch exception if any
	    System.err.println("Error: " + e.getMessage());
	    System.exit(1);
	}
    }

}
