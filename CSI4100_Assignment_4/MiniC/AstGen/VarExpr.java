package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class VarExpr extends Expr {

    public ID Ident;

    public VarExpr (ID Ident, SourcePos pos) {
	super (pos);
        this.Ident = Ident;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }
}
