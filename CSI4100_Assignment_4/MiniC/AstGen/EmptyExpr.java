package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class EmptyExpr extends Expr {

    public EmptyExpr (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}