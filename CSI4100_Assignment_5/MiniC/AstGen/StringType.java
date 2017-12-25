package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class StringType extends Type {

    public StringType (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

    public boolean Tequal (Type t) {
	if (t != null && t instanceof ErrorType)
	    return true;
	else
	    return (t != null && t instanceof StringType);
    }

    public boolean AssignableTo (Type t) {
	if (t != null && t instanceof ErrorType)
	    return true;
	else
	    return (t != null && (t instanceof StringType));
    }

}
