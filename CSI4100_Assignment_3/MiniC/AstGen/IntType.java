package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class IntType extends Type {

    public IntType (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}