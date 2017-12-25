package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class CompoundStmt extends Stmt {

    public Decl astDecl;
    public Stmt astStmt;

    public CompoundStmt (Decl astDecl, Stmt astStmt, SourcePos pos) {
	super (pos);
	this.astDecl = astDecl;
	this.astStmt = astStmt;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}