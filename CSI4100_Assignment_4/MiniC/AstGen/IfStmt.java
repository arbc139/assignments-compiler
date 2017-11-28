package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class IfStmt extends Stmt {

    public Expr eAST;
    public Stmt thenAST, elseAST;

    public IfStmt (Expr eAST, Stmt thenAST, SourcePos pos) {
	super (pos);
	this.eAST = eAST;
	this.thenAST = thenAST;
	this.elseAST = null;
    }

    public IfStmt (Expr eAST, Stmt thenAST, Stmt elseAST, SourcePos pos) {
	super (pos);
	this.eAST = eAST;
	this.thenAST = thenAST;
	this.elseAST = elseAST;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
