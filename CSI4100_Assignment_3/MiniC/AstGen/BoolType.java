package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class BoolType extends Type {

    public BoolType (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}