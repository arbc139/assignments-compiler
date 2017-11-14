package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class Operator extends Terminal {

    public Operator (String Lexeme, SourcePos pos) {
	super (pos);
	this.Lexeme = Lexeme;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
