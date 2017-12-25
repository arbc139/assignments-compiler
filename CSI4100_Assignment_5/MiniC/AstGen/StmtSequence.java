package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class StmtSequence extends Stmt {

    public Stmt s1AST, s2AST;

    public StmtSequence (Stmt s1AST, Stmt s2AST, SourcePos pos) {
	super (pos);
        this.s1AST = s1AST;
        this.s2AST = s2AST;
    }

    public void accept(Visitor v) {
	v.visit(this);
    }
}
