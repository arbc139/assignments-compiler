package MiniC.SemanticAnalysis;

import MiniC.AstGen.Decl;

public class IdEntry {

    protected String id;
    protected Decl declAST;
    protected int level;
    protected IdEntry previous;

    public IdEntry (String id, Decl declAST, int level, IdEntry previous) {
	this.id = id;
	this.declAST = declAST;
	this.level = level;
	this.previous = previous;
    }

}