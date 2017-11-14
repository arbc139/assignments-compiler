package MiniC.TreePrinter;

import java.io.*;
import MiniC.AstGen.*;
import MiniC.Scanner.SourcePos;

public class TreePrinterVisitor implements Visitor {

    private BufferedWriter out;
    private int indent;
    private final int INDENT_LEVEL = 3; //amount of indentation per level
    private boolean draw_pos = false;

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

    private String FormatPosition(AST n) {
        if (draw_pos) {
           SourcePos pos = n.getPosition();
           return " "
               + Integer.toString (pos.StartLine)
               + "(" + Integer.toString (pos.StartCol) + ").."
               +  Integer.toString (pos.EndLine)
               + "(" + Integer.toString (pos.EndCol) + ")"
               + "\n";
        } else {
            return "\n";
        }
    }

    public void visit(Program x) {
	write("Program" + FormatPosition(x));
	indent++;
	x.D.accept(this);
    }

    public void visit(EmptyDecl x) {
	write("EmptyDecl\n");
    }

    public void visit(FunDecl x) {
	write("FunDecl" + FormatPosition(x));
	indent++;
        x.tAST.accept(this);
	x.idAST.accept(this);
	x.paramsAST.accept(this);
	x.stmtAST.accept(this);
	indent--;
    }

    public void visit(FormalParamDecl x) {
	write("FormalParamDecl" + FormatPosition(x));
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
	write("AssignStmt" + FormatPosition(x));
	indent++;
	x.lAST.accept(this);
	x.rAST.accept(this);
	indent--;
    }

    public void visit(IfStmt x) {
	write("IfStmt" + FormatPosition(x));
	indent++;
	x.eAST.accept(this);
	x.thenAST.accept(this);
	if(x.elseAST != null) {
	    x.elseAST.accept(this);
	}
	indent--;
    }

    public void visit(WhileStmt x) {
	write ("WhileStmt" + FormatPosition(x));
	indent++;
	x.eAST.accept(this);
	x.stmtAST.accept(this);
	indent--;
    }

    public void visit(ForStmt x) {
	write ("ForStmt" + FormatPosition(x));
	indent++;
	x.e1AST.accept(this);
        x.e2AST.accept(this);
	x.e3AST.accept(this);
	x.stmtAST.accept(this);
	indent--;
    }

    public void visit(ReturnStmt x) {
	write("ReturnStmt" + FormatPosition(x));
	indent++;
        x.eAST.accept(this);
	indent--;
    }

    public void visit(CompoundStmt x) {
	write("CompoundStmt" + FormatPosition(x));
	indent++;
	x.astDecl.accept(this);
	x.astStmt.accept(this);
	indent--;
    }

    public void visit(EmptyCompoundStmt x) {
	write("EmptyCompoundStmt\n");
    }

    public void visit(EmptyStmt x) {
	write("EmptyStmt\n");
    }

    public void visit(CallStmt x) {
	write("CallStmt" + FormatPosition(x));
	indent++;
	x.eAST.accept(this);
	indent--;
    }

    public void visit(VarDecl x) {
	write("VarDecl" + FormatPosition(x));
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
	write("VarExpr" + FormatPosition(x));
	indent++;
	x.Ident.accept(this);
	indent--;
    }

    public void visit(AssignExpr x) {
	write("AssignExpr" + FormatPosition(x));
	indent++;
	x.lAST.accept(this);
	x.rAST.accept(this);
	indent--;
    }

    public void visit(IntExpr x) {
	write("IntExpr" + FormatPosition(x));
	indent++;
	x.astIL.accept(this);
	indent--;
    }

    public void visit(FloatExpr x) {
	write("FloatExpr" + FormatPosition(x));
	indent++;
	x.astFL.accept(this);
	indent--;
    }

    public void visit(BoolExpr x) {
	write("BoolExpr" + FormatPosition(x));
	indent++;
	x.astBL.accept(this);
	indent--;
    }

    public void visit(StringExpr x) {
	write("StringExpr" + FormatPosition(x));
	indent++;
	x.astSL.accept(this);
	indent--;
    }

    public void visit(ArrayExpr x) {
	write("ArrayExpr" + FormatPosition(x));
	indent++;
	x.idAST.accept(this);
	x.indexAST.accept(this);
	indent--;
    }

    public void visit(BinaryExpr x) {
	write("BinaryExpr" + FormatPosition(x));
	indent++;
	x.lAST.accept(this);
	x.oAST.accept(this);
	x.rAST.accept(this);
	indent--;
    }

    public void visit(UnaryExpr x) {
	write("UnaryExpr" + FormatPosition(x));
	indent++;
	x.oAST.accept(this);
	x.eAST.accept(this);
	indent--;
    }

    public void visit(EmptyExpr x) {
	write("EmptyExpr\n");
    }

    public void visit(ActualParam x) {
	write("ActualParam" + FormatPosition(x));
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
	write("CallExpr" + FormatPosition(x));
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
	write("ID: " + x.Lexeme + FormatPosition(x));
    }

    public void visit(Operator x) {
	write("Operator: " + x.Lexeme + FormatPosition(x));
    } 

    public void visit(IntLiteral x) {
	write("IntLiteral: " + x.Lexeme + FormatPosition(x));
    } 

    public void visit(FloatLiteral x) {
	write("FloatLiteral: " + x.Lexeme + FormatPosition(x));
    } 

    public void visit(BoolLiteral x) {
	write("BoolLiteral: " + x.Lexeme + FormatPosition(x));
    } 

    public void visit(StringLiteral x) {
	write("StringLiteral: " + x.Lexeme + FormatPosition(x));
    } 

    public void visit(IntType x) {
	write("IntType" + FormatPosition(x));
    }

    public void visit(FloatType x) {
	write("FloatType" + FormatPosition(x));
    }

    public void visit(BoolType x) {
	write("BoolType" + FormatPosition(x));
    }

    public void visit(StringType x) {
	write("StringType" + FormatPosition(x));
    }

    public void visit(VoidType x) {
	write("VoidType" + FormatPosition(x));
    }

    public void visit(ArrayType x) {
	write("ArrayType" + FormatPosition(x));
        indent++;
        x.astType.accept(this);
        x.astExpr.accept(this);
        indent--;
    }

    public void visit(ErrorType x) {
	write("ErrorType" + FormatPosition(x));
    }

}
