package MiniC.AstGen;

public interface Visitor {

    // Program...
    void visit(Program x);

    // ... and other bits and pieces...
    void visit(EmptyDecl x);
    void visit(FunDecl x);

    void visit(VarDecl x);
    void visit(TypeDecl x);
    void visit(FormalParamDecl x);
    void visit(FormalParamDeclSequence x);
    void visit(EmptyFormalParamDecl x);
    void visit(DeclSequence x);

    void visit (AssignStmt x);
    void visit(IfStmt x);
    void visit(WhileStmt x);
    void visit(ForStmt x);
    void visit(ReturnStmt x);
    void visit(CompoundStmt x);
    void visit(EmptyCompoundStmt x);
    void visit(EmptyStmt x);
    void visit(StmtSequence x);
    void visit(CallStmt x);

    void visit(VarExpr x);
    void visit(AssignExpr x);
    void visit(IntExpr x);
    void visit(FloatExpr x);
    void visit(BoolExpr x);
    void visit(ArrayExpr x);
    void visit(StringExpr x);
    void visit(BinaryExpr x);
    void visit(UnaryExpr x);
    void visit(EmptyExpr x);
    void visit(ActualParam x);
    void visit(EmptyActualParam x);
    void visit(ActualParamSequence x);
    void visit(CallExpr x);
    void visit(ExprSequence x);
    void visit(ID x);
    void visit(Operator x);
    void visit(IntLiteral x);
    void visit(FloatLiteral x);
    void visit(BoolLiteral x);
    void visit(StringLiteral x);
    void visit(IntType x);
    void visit(FloatType x);
    void visit(BoolType x);
    void visit(VoidType x);
    void visit(StringType x);
    void visit(ArrayType x);
    void visit(ErrorType x);

}
