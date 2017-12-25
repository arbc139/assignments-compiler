package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class CallExpr extends Expr {

    public ID idAST;
    public Expr paramAST;

    public CallExpr (ID idAST, Expr paramAST, SourcePos pos) {
	super (pos);
	this.idAST = idAST;
        this.paramAST = paramAST;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }
}
