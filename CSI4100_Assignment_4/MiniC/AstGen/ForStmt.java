package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class ForStmt extends Stmt {

    public Expr e1AST, e2AST, e3AST;
    public Stmt stmtAST;

    public ForStmt (Expr e1AST, Expr e2AST, Expr e3AST, Stmt stmtAST, SourcePos pos) {
	super (pos);
	this.e1AST = e1AST;
	this.e2AST = e2AST;
	this.e3AST = e3AST;
        this.stmtAST = stmtAST;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
