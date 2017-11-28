package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class ExprSequence extends Expr {

    public Expr lAST, rAST;

    public ExprSequence (Expr lAST, Expr rAST, SourcePos pos) {
	super (pos);
        this.lAST = lAST;
        this.rAST = rAST;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }
}
