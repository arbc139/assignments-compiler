package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class EmptyStmt extends Stmt {

    public EmptyStmt (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
