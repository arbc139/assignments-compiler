package MiniC.Unparser;

import java.io.*;
import MiniC.AstGen.*;

public class UnparseVisitor implements Visitor {

    private BufferedWriter out;
    private int indent;
    private final int INDENT_LEVEL = 3; //amount of indentation per level
    public boolean IsGlobal;
    public boolean IsFirst;


    public UnparseVisitor(BufferedWriter out) {
	this.out = out;
	indent = 0;
	IsGlobal = true;
	IsFirst = true;
    }

    private void newline() {
	write("\n");
	for (int i = 1; i <= indent*INDENT_LEVEL; i++) {
	    write (" ");
	}
    }

    private void write (String s) {
	try {
	    out.write (s);
	} catch (Exception e) {
	    System.err.println("Error: " + e.getMessage());
	    System.exit(1);
	}
    }

    public void visit(Program x) {
        if (x.D.getClass() != EmptyDecl.class) {
	    x.D.accept(this);
        }
	write("\n");
    }

    public void visit(EmptyDecl x) {
        assert (false);
    }

    public void visit(FunDecl x) {
	if (!IsFirst) {
	    newline();
	    newline();
	}
	IsFirst = false;
        IsGlobal = false;
        x.tAST.accept(this);
	write(" ");
	x.idAST.accept(this);
        write ("(");
        if (x.paramsAST.getClass() == FormalParamDeclSequence.class) {
	    x.paramsAST.accept(this);
        }
	write (")");
	x.stmtAST.accept(this);
	IsGlobal = true;
    }

    public void visit(FormalParamDecl x) {
        if (x.astType instanceof ArrayType) {
	    ((ArrayType)x.astType).astType.accept(this);
	    write (" ");
	    x.astIdent.accept(this);
	    write("[");
	    ((ArrayType)x.astType).astExpr.accept(this);
	    write("]");
	} else {
	    x.astType.accept(this);
	    write(" ");
	    x.astIdent.accept(this);
	}
    }

    public void visit(FormalParamDeclSequence x) {
	x.lAST.accept(this);
        if (x.rAST.getClass() == FormalParamDeclSequence.class) {
	    write(", ");
	    x.rAST.accept(this);
        }
    }

    public void visit(EmptyFormalParamDecl x) {
        assert (false);
    }

    public void visit(StmtSequence x) {
	x.s1AST.accept(this);
        if (x.s2AST.getClass() == StmtSequence.class) {
	    x.s2AST.accept(this);
        }
    }

    public void visit(AssignStmt x) {
	newline();
	x.lAST.accept(this);
	write (" = ");
	x.rAST.accept(this);
	write (";");
    }

    public void visit(IfStmt x) {
	newline();
	write("if (");
	x.eAST.accept(this);
	write(")");
        if(!(x.thenAST instanceof CompoundStmt)) {
	    indent++;
	}
	x.thenAST.accept(this);
        if(!(x.thenAST instanceof CompoundStmt)) {
	    indent--;
	}
	if(x.elseAST != null) {
	    newline();
	    write("else");
            if(!(x.elseAST instanceof CompoundStmt)) {
		indent++;
	    }
	    x.elseAST.accept(this);
            if(!(x.elseAST instanceof CompoundStmt)) {
		indent--;
	    }
	}
    }

    public void visit(WhileStmt x) {
	newline();
	write ("while (");
	x.eAST.accept(this);
	write(")");
	x.stmtAST.accept(this);
    }

    public void visit(ForStmt x) {
	newline();
	write ("for (");
        if (x.e1AST.getClass() != EmptyExpr.class) {
	x.e1AST.accept(this);
        }
	write(";");
	if(!(x.e2AST instanceof EmptyExpr)) {
	    write(" ");
	    x.e2AST.accept(this);
	}
	write(";");
	if(!(x.e3AST instanceof EmptyExpr)) {
	    write(" ");
	    x.e3AST.accept(this);
	}
	write(")");
	x.stmtAST.accept(this);
    }

    public void visit(ReturnStmt x) {
	newline();
	write("return");
	if (!(x.eAST instanceof EmptyExpr)) {
	    write (" ");
	    x.eAST.accept(this);
	}
	write(";");
    }

    public void visit(CompoundStmt x) {
	newline();
	write("{");
	indent++;
        if (x.astDecl.getClass() == DeclSequence.class) {
	    x.astDecl.accept(this);
        }
        if (x.astStmt.getClass() == StmtSequence.class) {
	    x.astStmt.accept(this);
        }
	indent--;
	newline();
	write("}");
    }

    public void visit(EmptyCompoundStmt x) {
	newline();
	write("{}");
    }

    public void visit(EmptyStmt x) {
        assert (false);
    }

    public void visit(CallStmt x) {
	newline();
	x.eAST.accept(this);
	write(";");
    }

    public void visit(VarDecl x) {
	if (IsGlobal && !IsFirst) {
  	    newline();
            newline();
	} else if (!IsGlobal && !IsFirst) {
	    newline();
	}
	IsFirst = false;
        if (x.tAST instanceof ArrayType) {
	    ((ArrayType)x.tAST).astType.accept(this);
	    write (" ");
	    x.idAST.accept(this);
	    write("[");
	    ((ArrayType)x.tAST).astExpr.accept(this);
	    write("]");
            if (x.eAST.getClass() == ExprSequence.class) {
                write(" = { ");
                x.eAST.accept(this);
                write(" }");
            } else if (x.eAST.getClass() != EmptyExpr.class) {
		write(" = ");
		x.eAST.accept(this);
	    }
	} else {
	    x.tAST.accept(this);
	    write (" ");
	    x.idAST.accept(this);
            if (x.eAST.getClass() == ExprSequence.class) {
                write(" = { ");
                x.eAST.accept(this);
                write(" }");
            } else if (x.eAST.getClass() != EmptyExpr.class) {
		write(" = ");
		x.eAST.accept(this);
	    }
	}
	write(";");
    }

    public void visit(DeclSequence x){
	x.D1.accept(this);
        if (x.D2.getClass() == DeclSequence.class) {
	   x.D2.accept(this);
        }
    }

    public void visit(VarExpr x) {
	x.Ident.accept(this);
    }

    public void visit(AssignExpr x) {
	x.lAST.accept(this);
	write (" = ");
	x.rAST.accept(this);
    }

    public void visit(IntExpr x) {
	x.astIL.accept(this);
    }

    public void visit(FloatExpr x) {
	x.astFL.accept(this);
    }

    public void visit(BoolExpr x) {
	x.astBL.accept(this);
    }

    public void visit(StringExpr x) {
	x.astSL.accept(this);
    }

    public void visit(ArrayExpr x) {
	x.idAST.accept(this);
	write("[");
	x.indexAST.accept(this);
	write("]");
    }

    public void visit(BinaryExpr x) {
	write("(");
	x.lAST.accept(this);
	write(" ");
	x.oAST.accept(this);
	write(" ");
	x.rAST.accept(this);
	write(")");
    }

    public void visit(UnaryExpr x) {
        write("(");
	x.oAST.accept(this);
	x.eAST.accept(this);
	write(")");
    }

    public void visit(EmptyExpr x) {
        assert (false);
    }

    public void visit(ActualParam x) {
	x.pAST.accept(this);
    }

    public void visit(EmptyActualParam x) {
        assert (false);
    }

    public void visit(ActualParamSequence x) {
	x.lAST.accept(this);
        if (x.rAST.getClass() == ActualParamSequence.class) {
	    write(", ");
	    x.rAST.accept(this);
        }
    }

    public void visit(CallExpr x) {
	x.idAST.accept(this);
	write("(");
        if (x.paramAST.getClass() == ActualParamSequence.class) {
	    x.paramAST.accept(this);
        }
	write(")");
    }

    public void visit(ExprSequence x) {
	x.lAST.accept(this);
        if (x.rAST.getClass() == ExprSequence.class) {
	   write(", ");
	   x.rAST.accept(this);
        }
    }

    public void visit(ID x) {
	write(x.Lexeme);
    }

    public void visit(Operator x) {
	write(x.Lexeme);
    } 

    public void visit(IntLiteral x) {
	write(x.Lexeme);
    } 

    public void visit(FloatLiteral x) {
	write(x.Lexeme);
    } 

    public void visit(BoolLiteral x) {
	write(x.Lexeme);
    } 

    public void visit(StringLiteral x) {
	write("\"" + x.Lexeme + "\"");
    } 

    public void visit(IntType x) {
	write ("int");
    }

    public void visit(FloatType x) {
	write ("float");
    }

    public void visit(BoolType x) {
	write ("bool");
    }

    public void visit(StringType x) {
	assert(false);
    }

    public void visit(VoidType x) {
	write ("void");
    }

    public void visit(ArrayType x) {
	assert(false); //never called directly.
    }

    public void visit(ErrorType x) {
	write ("ErrorType");
    }

}
