package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class DeclSequence extends Decl {

    public Decl D1, D2;

    public DeclSequence (Decl d1AST, Decl d2AST, SourcePos pos) {
	super (pos);
	D1 = d1AST;
	D2 = d2AST;
    }

    public Decl GetLeftSubtree () {
        return D1;
    }

    public Decl GetRightSubtree () {
        return D2;
    }


    public void SetLeftSubtree (Decl D) {
        D1 = D;
    }

    public void SetRightSubtree (Decl D) {
        D2 = D;
    }

    public DeclSequence GetRightmostDeclSequenceNode () {
        assert (D2 != null);
        if (D2.getClass() == DeclSequence.class) {
           return ((DeclSequence)D2).GetRightmostDeclSequenceNode();
        } else {
           return this;
        } 
    }

    public void accept(Visitor v) {
	v.visit(this);
    }
 
}
