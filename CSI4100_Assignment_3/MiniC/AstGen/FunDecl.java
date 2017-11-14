package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class FunDecl extends Decl {

    public Type tAST;
    public ID idAST;
    public Decl paramsAST;
    public Stmt stmtAST;

    public FunDecl (Type tAST, ID idAST, Decl paramsAST,
                    Stmt stmtAST, SourcePos pos) {
	super (pos);
	this.tAST = tAST;
	this.idAST = idAST;
	this.paramsAST = paramsAST;
        this.stmtAST = stmtAST;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }

}