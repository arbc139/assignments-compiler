package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public abstract class Decl extends AST {

    public Decl (SourcePos pos) {
	super (pos);
    }

}
