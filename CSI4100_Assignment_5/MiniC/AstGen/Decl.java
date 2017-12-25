package MiniC.AstGen;

import MiniC.Scanner.SourcePos;

public abstract class Decl extends AST {

    private boolean global;
    public int index;

    public Decl (SourcePos pos) {
        super (pos);
        global = false;
        index = -1;
    }

    public void setGlobal() {
        global = true;
    }

    public boolean isGlobal() {
        return global;
    }
}
