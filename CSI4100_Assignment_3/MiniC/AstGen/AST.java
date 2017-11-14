package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public abstract class AST {

    public AST (SourcePos pos) {
        position = new SourcePos();
	position.StartCol = pos.StartCol;
	position.EndCol = pos.EndCol;
	position.StartLine = pos.StartLine;
	position.EndLine = pos.EndLine;
    }

    public SourcePos getPosition() {
	return position;
    }

    public abstract void accept(Visitor v);

    public SourcePos position;
}
