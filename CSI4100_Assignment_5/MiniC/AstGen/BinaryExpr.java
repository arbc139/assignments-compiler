package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class BinaryExpr extends Expr {

    public Expr lAST, rAST;
    public Operator oAST;

    public BinaryExpr (Expr lAST, Operator oAST, Expr rAST, SourcePos pos) {
	super (pos);
        this.lAST = lAST;
        this.rAST = rAST;
        this.oAST = oAST;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }
}
