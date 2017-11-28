package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public abstract class Terminal extends AST {

    public String Lexeme;

    public Terminal (SourcePos pos) {
	super (pos);
    }

}