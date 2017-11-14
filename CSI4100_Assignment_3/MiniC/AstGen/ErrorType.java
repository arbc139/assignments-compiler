package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class ErrorType extends Type {

    public ErrorType (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
