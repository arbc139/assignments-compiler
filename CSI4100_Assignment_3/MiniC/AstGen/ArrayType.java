package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class ArrayType extends Type {

    public Type astType;
    public Expr astExpr;

    public ArrayType (Type astType, Expr astExpr, SourcePos pos) {
	super (pos);
        this.astType = astType;
        this.astExpr = astExpr;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
