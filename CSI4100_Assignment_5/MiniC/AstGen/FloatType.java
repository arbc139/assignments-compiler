package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class FloatType extends Type {

    public FloatType (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

    public boolean Tequal (Type t) {
	if (t != null && t instanceof ErrorType)
	    return true;
	else
	    return (t != null && t instanceof FloatType);
    }

    public boolean AssignableTo (Type t) {
	//FloatType assignable to t ?
	if (t != null && t instanceof ErrorType)
	    return true;
	else
	    return (t != null && (t instanceof FloatType));
    }

}