package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class FloatLiteral extends Terminal {

    public FloatLiteral (String Lexeme, SourcePos pos) {
	super (pos);
	this.Lexeme = Lexeme;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
