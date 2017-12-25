package MiniC.SemanticAnalysis;

import MiniC.AstGen.*;

final public class ScopeStack {

    private int level;
    private IdEntry latest;

    public ScopeStack () {
	level = 1;  // MiniC's global scope is on level 1.
	latest = null;
    }

    // Opens a new level in the scope stack, 1 higher than the
    // current topmost level.

    public void openScope () {
	level ++;
    }

    // Closes the topmost level in the scope stack, discarding
    // all entries belonging to that level.

    public void closeScope () {

	IdEntry entry;

	// Presumably, idTable.level > 0:
	assert (this.level > 0);
	entry = this.latest;
	while (entry.level == this.level) {
	    /*
	      local = entry;
	      entry = local.previous;
	    */
	    assert(entry.previous != null);
	    entry = entry.previous;
	}
	this.level--;
	this.latest = entry;
    }

    // Makes a new entry in the scope stack for the given identifier
    // and attribute. The new entry belongs to the current level.
    // Returns false iff there is already an entry for the
    // same identifier at the current level.

    public boolean enter (String id, Decl declAST) {

	IdEntry entry = this.latest;
	boolean searching = true;

	// Check for duplicate entry ...
	while (searching) {
	    if (entry == null || entry.level < this.level)
		searching = false;
	    else if (entry.id.equals(id)) {
		// duplicate entry dedected:
		return false;
	    } else
		entry = entry.previous;
	}

	// "id" does not exist on this scope level, add new entry for "id":...
	entry = new IdEntry(id, declAST, this.level, this.latest);
	this.latest = entry;
	return true;
    }

    // Finds an entry for the given identifier in the scope stack,
    // if any. If there are several entries for that identifier, finds the
    // entry at the highest level, in accordance with the scope rules.
    // Returns null iff no entry is found.
    // Otherwise returns the declAST field of the scope stack entry found.

    public Decl retrieve (String id) {

	IdEntry entry;
	Decl declAST = null;
	boolean searching = true;

	entry = this.latest;
	while (searching) {
	    if (entry == null)
		searching = false;
	    else if (entry.id.equals(id)) {
		searching = false;
		declAST = entry.declAST;
	    } else
		entry = entry.previous;
	}
	return declAST;
    }

}
