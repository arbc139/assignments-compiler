package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class StringExpr extends Expr {

    public StringLiteral astSL;

    public StringExpr (StringLiteral astSL, SourcePos pos) {
	super (pos);
	this.astSL = astSL;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
