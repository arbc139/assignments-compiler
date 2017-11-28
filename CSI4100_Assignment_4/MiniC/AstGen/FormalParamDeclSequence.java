package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class FormalParamDeclSequence extends Decl {

    public Decl lAST, rAST;

    public FormalParamDeclSequence (Decl lAST, Decl rAST, SourcePos pos) {
	super (pos);
        this.lAST = lAST;
        this.rAST = rAST;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }
}
