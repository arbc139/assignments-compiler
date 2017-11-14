package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class StringType extends Type {

    public StringType (SourcePos pos) {
	super (pos);
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
