package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class EmptyCompoundStmt extends CompoundStmt {

    public EmptyCompoundStmt (SourcePos pos) {
	super (null, null, pos);
  }

    public void accept(Visitor v) {
	v.visit(this);
    }

}