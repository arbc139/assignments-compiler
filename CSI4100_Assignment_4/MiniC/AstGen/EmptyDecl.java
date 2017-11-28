package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public class EmptyDecl extends Decl {

  public EmptyDecl (SourcePos pos) {
    super (pos);
  }

    public void accept(Visitor v) {
	v.visit(this);
    }

}