package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class Operator extends Terminal {

    public Type type;

    public Operator (String Lexeme, SourcePos pos) {
	super (pos);
	this.Lexeme = Lexeme;
	this.type = null;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
