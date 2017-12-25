package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class IntExpr extends Expr {

    public IntLiteral astIL;

    public IntExpr (IntLiteral astIL, SourcePos pos) {
	super (pos);
        assert (astIL != null);
	this.astIL = astIL;
    }

    public int GetValue() {
       return astIL.GetValue();
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
