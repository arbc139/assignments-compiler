package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class WhileStmt extends Stmt {

    public Expr eAST;
    public Stmt stmtAST;

    public WhileStmt (Expr eAST, Stmt stmtAST, SourcePos pos) {
	super (pos);
	this.eAST = eAST;
	this.stmtAST = stmtAST;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
