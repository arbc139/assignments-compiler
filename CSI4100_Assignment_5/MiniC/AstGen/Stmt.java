package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public abstract class Stmt extends AST {

    public Stmt (SourcePos pos) {
	super (pos);
    }

}
