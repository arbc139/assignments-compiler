package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class IntExpr extends Expr {

    public IntLiteral astIL;

    public IntExpr (IntLiteral astIL, SourcePos pos) {
	super (pos);
	this.astIL = astIL;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}