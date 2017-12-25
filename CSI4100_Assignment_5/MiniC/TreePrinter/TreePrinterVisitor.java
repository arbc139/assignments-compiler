package MiniC.TreePrinter;

import java.io.*;
import MiniC.AstGen.*;

public class TreePrinterVisitor implements Visitor {

    private BufferedWriter out;
    private int indent;
    private final int INDENT_LEVEL = 3; //amount of indentation per level

    public TreePrinterVisitor(BufferedWriter out) {
	this.out = out;
	indent = 0;
    }


    private void write (String s) {
	try {
	    for (int i = 1; i <= indent*INDENT_LEVEL; i++) {
	       out.write (" ");
	    }
	    out.write (s);
	} catch (Exception e) {
	    System.err.println("Error: " + e.getMessage());
	    System.exit(1);
	}
    }

    public void visit(Program x) {
	write("Program\n");
	indent++;
	x.D.accept(this);
    }

    public void visit(EmptyDecl x) {
	write("EmptyDecl\n");
    }

    public void visit(FunDecl x) {
	write("FunDecl\n");
	indent++;
        x.tAST.accept(this);
	x.idAST.accept(this);
	x.paramsAST.accept(this);
	x.stmtAST.accept(this);
	indent--;
    }

    public void visit(TypeDecl x) {
        assert(false); // Can only occur in the StdEnvironment AST!
   }

    public void visit(FormalParamDecl x) {
	write("FormalParamDecl\n");
	indent++;
        x.astType.accept(this);
        x.astIdent.accept(this);
	indent--;
    }

    public void visit(FormalParamDeclSequence x) {
	write("FormalParamDeclSequence\n");
	indent++;
	x.lAST.accept(this);
	x.rAST.accept(this);
	indent--;
    }

    public void visit(EmptyFormalParamDecl x) {
	write("EmptyFormalParamDecl\n");
    }

    public void visit(StmtSequence x) {
	write("StmtSequence\n");
	indent++;
	x.s1AST.accept(this);
	x.s2AST.accept(this);
	indent--;
    }

    public void visit(AssignStmt x) {
	write("AssignStmt\n");
	indent++;
	x.lAST.accept(this);
	x.rAST.accept(this);
	indent--;
    }

    public void visit(IfStmt x) {
	write("IfStmt\n");
	indent++;
	x.eAST.accept(this);
	x.thenAST.accept(this);
	if(x.elseAST != null) {
	    x.elseAST.accept(this);
	}
	indent--;
    }

    public void visit(WhileStmt x) {
	write ("WhileStmt\n");
	indent++;
	x.eAST.accept(this);
	x.stmtAST.accept(this);
	indent--;
    }

    public void visit(ForStmt x) {
	write ("ForStmt\n");
	indent++;
	x.e1AST.accept(this);
        x.e2AST.accept(this);
	x.e3AST.accept(this);
	x.stmtAST.accept(this);
	indent--;
    }

    public void visit(ReturnStmt x) {
	write("ReturnStmt\n");
	indent++;
        x.eAST.accept(this);
	indent--;
    }

    public void visit(CompoundStmt x) {
	write("CompoundStmt\n");
	indent++;
	x.astDecl.accept(this);
	x.astStmt.accept(this);
	indent--;
    }

    public void visit(EmptyStmt x) {
	write("EmptyStmt\n");
    }

    public void visit(EmptyCompoundStmt x) {
	write("EmptyCompoundStmt\n");
    }

    public void visit(CallStmt x) {
	write("CallStmt\n");
	indent++;
	x.eAST.accept(this);
	indent--;
    }

    public void visit(VarDecl x) {
	write("VarDecl\n");
	indent++;
        x.tAST.accept(this);
        x.idAST.accept(this);
	x.eAST.accept(this);
	indent--;
    }

    public void visit(DeclSequence x){
	write("DeclSequence\n");
	indent++;
	x.D1.accept(this);
	x.D2.accept(this);
	indent--;
    }

    public void visit(VarExpr x) {
	write("VarExpr\n");
	indent++;
	x.Ident.accept(this);
	indent--;
    }

    public void visit(AssignExpr x) {
	write("AssignExpr\n");
	indent++;
	x.lAST.accept(this);
	x.rAST.accept(this);
	indent--;
    }

    public void visit(IntExpr x) {
	write("IntExpr\n");
	indent++;
	x.astIL.accept(this);
	indent--;
    }

    public void visit(FloatExpr x) {
	write("FloatExpr\n");
	indent++;
	x.astFL.accept(this);
	indent--;
    }

    public void visit(BoolExpr x) {
	write("BoolExpr\n");
	indent++;
	x.astBL.accept(this);
	indent--;
    }

    public void visit(StringExpr x) {
	write("StringExpr\n");
	indent++;
	x.astSL.accept(this);
	indent--;
    }

    public void visit(ArrayExpr x) {
	write("ArrayExpr\n");
	indent++;
	x.idAST.accept(this);
	x.indexAST.accept(this);
	indent--;
    }

    public void visit(BinaryExpr x) {
	write("BinaryExpr\n");
	indent++;
	x.lAST.accept(this);
	x.oAST.accept(this);
	x.rAST.accept(this);
	indent--;
    }

    public void visit(UnaryExpr x) {
	write("UnaryExpr\n");
	indent++;
	x.oAST.accept(this);
	x.eAST.accept(this);
	indent--;
    }

    public void visit(EmptyExpr x) {
	write("EmptyExpr\n");
    }

    public void visit(ActualParam x) {
	write("ActualParam\n");
	indent++;
	x.pAST.accept(this);
	indent--;
    }

    public void visit(EmptyActualParam x) {
	write("EmptyActualParam\n");
    }

    public void visit(ActualParamSequence x) {
	write("ActualParamSequence\n");
	indent++;
	x.lAST.accept(this);
	x.rAST.accept(this);
	indent--;
    }

    public void visit(CallExpr x) {
	write("CallExpr\n");
	indent++;
	x.idAST.accept(this);
	x.paramAST.accept(this);
	indent--;
    }

    public void visit(ExprSequence x) {
	write("ExprSequence\n");
	indent++;
	x.lAST.accept(this);
	x.rAST.accept(this);
	indent--;
    }

    public void visit(ID x) {
	write("ID: " + x.Lexeme + "\n");
    }

    public void visit(Operator x) {
	write("Operator: " + x.Lexeme + "\n");
    } 

    public void visit(IntLiteral x) {
	write("IntLiteral: " + x.Lexeme + "\n");
    } 

    public void visit(FloatLiteral x) {
	write("FloatLiteral: " + x.Lexeme + "\n");
    } 

    public void visit(BoolLiteral x) {
	write("BoolLiteral: " + x.Lexeme + "\n");
    } 

    public void visit(StringLiteral x) {
	write("StringLiteral: " + x.Lexeme + "\n");
    } 

    public void visit(IntType x) {
	write("IntType\n");
    }

    public void visit(FloatType x) {
	write("FloatType\n");
    }

    public void visit(BoolType x) {
	write("BoolType\n");
    }

    public void visit(StringType x) {
	write("StringType\n");
    }

    public void visit(VoidType x) {
	write("VoidType\n");
    }

    public void visit(ArrayType x) {
	write("ArrayType\n");
    }

    public void visit(ErrorType x) {
	write("ErrorType\n");
    }

}
