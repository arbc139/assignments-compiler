package MiniC.Unparser;

import java.io.*;
import MiniC.AstGen.Program;

public class Unparser {

    public void unparse(Program ast, String FileName) {
	try {
	    // Create file 
	    FileWriter fstream = new FileWriter(FileName);
	    BufferedWriter out = new BufferedWriter(fstream);
            // Create an UnparseVisitor and visit the AST:
            UnparseVisitor uv = new UnparseVisitor(out);
            ast.accept(uv);
	    //Close the output stream
	    out.close();
	} catch (Exception e) {
            //Catch exception if any
	    System.err.println("Error: " + e.getMessage());
	    System.exit(1);
	}
    }

}
