package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public abstract class Type extends AST {

    public Type (SourcePos pos) {
	super (pos);
    }

    public abstract boolean Tequal(Type t);

    public abstract boolean AssignableTo(Type t);

}