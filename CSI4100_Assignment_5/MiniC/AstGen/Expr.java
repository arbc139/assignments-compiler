package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public abstract class Expr extends AST {

    public Type type;

    public Expr (SourcePos pos) {
	super (pos);
    }

}