package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class IntType extends Type {

    public IntType (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

    public boolean Tequal (Type t) {
	if (t != null && t instanceof ErrorType)
	    return true;
	else
	    return (t != null && t instanceof IntType);
    }

    public boolean AssignableTo (Type t) {
	//IntType assignable to t ?
	if (t != null && t instanceof ErrorType)
	    return true;
	else
	    return (t != null &&
		    ((t instanceof IntType) || (t instanceof FloatType)));
    }

}