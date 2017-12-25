package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class IntLiteral extends Terminal {

    int value;

    public IntLiteral (String Lexeme, SourcePos pos) {
	super (pos);
	this.Lexeme = Lexeme;
        this.value = Integer.parseInt(Lexeme);
    }

    public int GetValue() {
        return value;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}
