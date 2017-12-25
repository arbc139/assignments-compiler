package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class VoidType extends Type {

    public VoidType (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

    public boolean Tequal (Type t) {
	if (t != null && t instanceof ErrorType)
	    return true;
	else
	    return (t != null && t instanceof VoidType);
    }

    public boolean AssignableTo (Type t) {
	return false;
    }

}