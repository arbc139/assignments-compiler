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

    public boolean Tequal (Type t) {
	if (t != null && t instanceof ErrorType)
	    return true;
        else
            return false;
    }

    public boolean AssignableTo (Type t) {
        assert (t != null);
	if (t instanceof ArrayType) {
            // Arrays we consider "assignable" if they have the same
            // element type and the same size.
            ArrayType arrT = (ArrayType)t;
            boolean eTypeSame = this.astType.Tequal(arrT.astType);
            return eTypeSame && (arrT.GetRange() == this.GetRange());
        }
	return false;
    }

    public int GetRange() {
       assert (astExpr instanceof IntExpr);
       return ((IntExpr)astExpr).GetValue();
    }

}
