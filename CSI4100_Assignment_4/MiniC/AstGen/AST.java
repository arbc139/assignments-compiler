package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public abstract class AST {

    public SourcePos pos;

    public AST (SourcePos pos) {
        this.pos = new SourcePos();
        this.pos.StartCol = pos.StartCol;
        this.pos.EndCol = pos.EndCol;
        this.pos.StartLine = pos.StartLine;
        this.pos.EndLine = pos.EndLine;
    }

    public SourcePos getPosition() {
	return pos;
    }

    public abstract void accept(Visitor v);
}
