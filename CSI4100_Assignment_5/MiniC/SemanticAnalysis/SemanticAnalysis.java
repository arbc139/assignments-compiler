package MiniC.SemanticAnalysis;

import MiniC.ErrorReporter;
import MiniC.AstGen.*;

public class SemanticAnalysis implements Visitor {

  //
  // NOTE:
  //
  // This is a dummy semantic analysis implementation that has the only
  // purpose to make the MiniC framework compileable.
  //
  // You must replace this file with your own semantic analyzer from Assignment 4.


  public SemanticAnalysis(ErrorReporter reporter) {
    System.out.println("ERROR: empty semantic analyzer implementation used!");
    System.out.println("Please provide your own SemanticAnalysis.java implementation");
    System.out.println("from Assignment 4.");
    System.exit(1);
  }

  public void check(Program progAST){}

  public void visit(Program x){}
  public void visit(EmptyDecl x){}
  public void visit(FunDecl x){}
  public void visit(VarDecl x){}
  public void visit(TypeDecl x){}
  public void visit(FormalParamDecl x){}
  public void visit(FormalParamDeclSequence x){}
  public void visit(EmptyFormalParamDecl x){}
  public void visit(DeclSequence x){}
  public void visit (AssignStmt x){}
  public void visit(IfStmt x){}
  public void visit(WhileStmt x){}
  public void visit(ForStmt x){}
  public void visit(ReturnStmt x){}
  public void visit(CompoundStmt x){}
  public void visit(EmptyCompoundStmt x){}
  public void visit(EmptyStmt x){}
  public void visit(StmtSequence x){}
  public void visit(CallStmt x){}
  public void visit(VarExpr x){}
  public void visit(AssignExpr x){}
  public void visit(IntExpr x){}
  public void visit(FloatExpr x){}
  public void visit(BoolExpr x){}
  public void visit(ArrayExpr x){}
  public void visit(StringExpr x){}
  public void visit(BinaryExpr x){}
  public void visit(UnaryExpr x){}
  public void visit(EmptyExpr x){}
  public void visit(ActualParam x){}
  public void visit(EmptyActualParam x){}
  public void visit(ActualParamSequence x){}
  public void visit(CallExpr x){}
  public void visit(ExprSequence x){}
  public void visit(ID x){}
  public void visit(Operator x){}
  public void visit(IntLiteral x){}
  public void visit(FloatLiteral x){}
  public void visit(BoolLiteral x){}
  public void visit(StringLiteral x){}
  public void visit(IntType x){}
  public void visit(FloatType x){}
  public void visit(BoolType x){}
  public void visit(VoidType x){}
  public void visit(StringType x){}
  public void visit(ArrayType x){}
  public void visit(ErrorType x){}

}
