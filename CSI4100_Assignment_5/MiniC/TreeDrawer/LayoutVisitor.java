package MiniC.TreeDrawer;

import java.awt.*;

import MiniC.AstGen.Visitor;
import MiniC.AstGen.*;
import MiniC.StdEnvironment;

public class LayoutVisitor implements Visitor {

    public DrawingTree Tree;
    public boolean IsGlobal;

    private final int BORDER = 5;
    private final int PARENT_SEP = 30;
    private FontMetrics fontMetrics;
    private boolean TypeInfo; // true if we include type information in the AST.

    public LayoutVisitor (FontMetrics fontMetrics) {
	this.fontMetrics = fontMetrics;
	this.IsGlobal = true;
	this.TypeInfo = true; // TBD: make this a constructor argument!
    }

    private String TypeTag (Type t) {
	String l = new String("");
	if (t == null) {
	    l = new String("<?>");
	} else if (t.Tequal(StdEnvironment.intType)) {
	    l = new String ("<int>");
	} else if (t.Tequal(StdEnvironment.boolType)) {
	    l = new String ("<bool>");
	} else if (t.Tequal(StdEnvironment.floatType)) {
	    l = new String ("<float>");
	} else if (t.Tequal(StdEnvironment.stringType)) {
	    l = new String ("<string>");
	} else if (t.Tequal(StdEnvironment.voidType)) {
	    l = new String ("<void>");
        } else if (t instanceof ArrayType) {
	    l = new String ("<array>");
	} else if (t instanceof ErrorType) { //.Tequal(StdEnvironment.errorType)) {
	    l = new String ("<error>");
	} else {
	    assert(false);
	}
	return l;
    }

    public void visit(Program x) {
        Tree = layoutUnary("Program", x.D);
    }

    public void visit(EmptyDecl x) {
	Tree = layoutNullary("EmptyDecl");
    }

    public void visit(FunDecl x) {
        IsGlobal = false;
	Tree = layoutQuaternary("FunDecl", x.tAST, x.idAST, x.paramsAST, x.stmtAST);
	IsGlobal = true;
    }

    public void visit(TypeDecl x) {
	Tree = layoutUnary("TypeDecl", x.tAST);
    }

    public void visit(FormalParamDecl x) {
        Tree = layoutBinary("FormalParamDecl", x.astType, x.astIdent);
    }

    public void visit(FormalParamDeclSequence x) {
        Tree = layoutBinary("FormalParamDeclSeq", x.lAST, x.rAST);
    }

    public void visit(EmptyFormalParamDecl x) {
	Tree = layoutNullary("EmptyFormalParamDecl");
    }

    public void visit(StmtSequence x) {
        Tree = layoutBinary("StmtSeq", x.s1AST, x.s2AST);
    }

    public void visit(AssignStmt x) {
	Tree = layoutBinary("AssignStmt", x.lAST, x.rAST);
    }

    public void visit(IfStmt x) {
	if (x.elseAST == null) {
	    Tree = layoutBinary("IfStmt", x.eAST, x.thenAST);
	} else { 
	    Tree = layoutTernary("IfStmt", x.eAST, x.thenAST, x.elseAST);
	}
    }

    public void visit(WhileStmt x) {
	Tree = layoutBinary ("WhileStmt", x.eAST, x.stmtAST);
    }

    public void visit(ForStmt x) {
	Tree = layoutQuaternary("ForStmt", x.e1AST, x.e2AST, x.e3AST, x.stmtAST);
    }

    public void visit(ReturnStmt x) {
	Tree = layoutUnary ("ReturnStmt", x.eAST);
    }

    public void visit(CompoundStmt x) {
	Tree = layoutBinary("CompoundStmt", x.astDecl, x.astStmt);
    }

    public void visit(EmptyStmt x) {
	Tree = layoutNullary("EmptyStmt");
    }

    public void visit(EmptyCompoundStmt x) {
	Tree = layoutNullary("EmptyCompoundStmt");
    }

    public void visit(CallStmt x) {
	Tree = layoutUnary ("CallStmt", x.eAST);
    }

    public void visit(VarDecl x) {
        String l = "VarDecl";
        if (IsGlobal) {
	    l = "G." + l; 
	} else {
	    l = "L." + l;
	}
	Tree = layoutTernary(l, x.tAST, x.idAST, x.eAST);
    }

    public void visit(DeclSequence x){
	Tree = layoutBinary("DeclSeq", x.D1, x.D2);
    }

    public void visit(VarExpr x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutUnary("VarExpr" + l, x.Ident);
    }

    public void visit(AssignExpr x) {
	Tree = layoutBinary("AssignExpr", x.lAST, x.rAST);
    }

    public void visit(IntExpr x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutUnary("IntExpr" + l, x.astIL);
    }

    public void visit(FloatExpr x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutUnary("FloatExpr" + l, x.astFL);
    }

    public void visit(BoolExpr x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutUnary("BoolExpr" + l, x.astBL);
    }

    public void visit(StringExpr x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutUnary("StringExpr" + l, x.astSL);
    }

    public void visit(ArrayExpr x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutBinary("ArrayExpr" + l, x.idAST, x.indexAST);
    }

    public void visit(BinaryExpr x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutTernary("BinaryExpr" + l, x.lAST, x.oAST, x.rAST);
    }

    public void visit(UnaryExpr x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutBinary("UnaryExpr" + l, x.oAST, x.eAST);
    }

    public void visit(EmptyExpr x) {
	Tree = layoutNullary("EmptyExpr");
    }

    public void visit(ActualParam x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutUnary("ActualParam" + l, x.pAST);
    }

    public void visit(EmptyActualParam x) {
	Tree = layoutNullary("EmptyActualParam");
    }

    public void visit(ActualParamSequence x) {
	Tree = layoutBinary("ActualParamSeq", x.lAST, x.rAST);
    }

    public void visit(CallExpr x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutBinary("CallExpr" + l, x.idAST, x.paramAST);
    }

    public void visit(ExprSequence x) {
	Tree = layoutBinary("ExprSeq", x.lAST, x.rAST);
    }

    public void visit(ID x) {
	Tree = layoutNullary(x.Lexeme);
    }

    public void visit(Operator x) {
	String l = new String("");
	if (TypeInfo) {
	    l = TypeTag(x.type);
	}
	Tree = layoutNullary(x.Lexeme + l);
    } 

    public void visit(IntLiteral x) {
	Tree = layoutNullary(x.Lexeme);
    } 

    public void visit(FloatLiteral x) {
	Tree = layoutNullary(x.Lexeme);
    } 

    public void visit(BoolLiteral x) {
	Tree = layoutNullary(x.Lexeme);
    } 

    public void visit(StringLiteral x) {
	Tree = layoutNullary(x.Lexeme);
    } 

    public void visit(IntType x) {
	Tree = layoutNullary("int");
    }

    public void visit(FloatType x) {
	Tree = layoutNullary("float");
    }

    public void visit(BoolType x) {
	Tree = layoutNullary("bool");
    }

    public void visit(StringType x) {
	Tree = layoutNullary("String");
    }

    public void visit(VoidType x) {
	Tree = layoutNullary("void");
    }

    public void visit(ArrayType x) {
	Tree = layoutBinary("Array", x.astType, x.astExpr);
    }

    public void visit(ErrorType x) {
	Tree = layoutNullary("Error");
    }

    private DrawingTree layoutCaption (String name) {
	int w = fontMetrics.stringWidth(name) + 4;
	int h = fontMetrics.getHeight() + 4;
	return new DrawingTree(name, w, h);
    }

    private DrawingTree layoutNullary (String name) {
	DrawingTree dt = layoutCaption(name);
	dt.contour.upper_tail = new Polyline(0, dt.height + 2 * BORDER, null);
	dt.contour.upper_head = dt.contour.upper_tail;
	dt.contour.lower_tail = new Polyline(-dt.width - 2 * BORDER, 0, null);
	dt.contour.lower_head = new Polyline(0, dt.height + 2 * BORDER, dt.contour.lower_tail);
	return dt;
    }

    private DrawingTree layoutUnary (String name, AST child1) {
	DrawingTree dt = layoutCaption(name);
	child1.accept(this);
	DrawingTree d1 = Tree;
	dt.setChildren(new DrawingTree[] {d1});
	attachParent(dt, join(dt));
	return dt;
    }

    private DrawingTree layoutBinary (String name, AST child1, AST child2) {
	DrawingTree dt = layoutCaption(name);
	child1.accept(this);
	DrawingTree d1 = Tree;
	child2.accept(this);
	DrawingTree d2 = Tree;
	dt.setChildren(new DrawingTree[] {d1, d2});
	attachParent(dt, join(dt));
	return dt;
    }

    private DrawingTree layoutTernary (String name, AST child1, AST child2,
				       AST child3) {
	DrawingTree dt = layoutCaption(name);
	child1.accept(this);
	DrawingTree d1 = Tree;
	child2.accept(this);
	DrawingTree d2 = Tree;
	child3.accept(this);
	DrawingTree d3 = Tree;  
	dt.setChildren(new DrawingTree[] {d1, d2, d3});
	attachParent(dt, join(dt));
	return dt;
    }

    private DrawingTree layoutQuaternary (String name, AST child1, AST child2,
					  AST child3, AST child4) {
	DrawingTree dt = layoutCaption(name);
	child1.accept(this);
	DrawingTree d1 = Tree;
	child2.accept(this);
	DrawingTree d2 = Tree;
	child3.accept(this);
	DrawingTree d3 = Tree;
	child4.accept(this);
	DrawingTree d4 = Tree;
	dt.setChildren(new DrawingTree[] {d1, d2, d3, d4});
	attachParent(dt, join(dt));
	return dt;
    }

    private void attachParent(DrawingTree dt, int w) {
	int y = PARENT_SEP;
	int x2 = (w - dt.width) / 2 - BORDER;
	int x1 = x2 + dt.width + 2 * BORDER - w;

	dt.children[0].offset.y = y + dt.height;
	dt.children[0].offset.x = x1;
	dt.contour.upper_head = new Polyline(0, dt.height,
					     new Polyline(x1, y, dt.contour.upper_head));
	dt.contour.lower_head = new Polyline(0, dt.height,
					     new Polyline(x2, y, dt.contour.lower_head));
    }

    private int join (DrawingTree dt) {
	int w, sum;

	dt.contour = dt.children[0].contour;
	sum = w = dt.children[0].width + 2 * BORDER;

	for (int i = 1; i < dt.children.length; i++) {
	    int d = merge(dt.contour, dt.children[i].contour);
	    dt.children[i].offset.x = d + w;
	    dt.children[i].offset.y = 0;
	    w = dt.children[i].width + 2 * BORDER;
	    sum += d + w;
	}
	return sum;
    }

    private int merge(Polygon c1, Polygon c2) {
	int x, y, total, d;
	Polyline lower, upper, b;

	x = y = total = 0;
	upper = c1.lower_head;
	lower = c2.upper_head;

	while (lower != null && upper != null) {
	    d = offset(x, y, lower.dx, lower.dy, upper.dx, upper.dy);
	    x += d;
	    total += d;

	    if (y + lower.dy <= upper.dy) {
		x += lower.dx;
		y += lower.dy;
		lower = lower.link;
	    } else {
		x -= upper.dx;
		y -= upper.dy;
		upper = upper.link;
	    }
	}

	if (lower != null) {
	    b = bridge(c1.upper_tail, 0, 0, lower, x, y);
	    c1.upper_tail = (b.link != null) ? c2.upper_tail : b;
	    c1.lower_tail = c2.lower_tail;
	} else {
	    b = bridge(c2.lower_tail, x, y, upper, 0, 0);
	    if (b.link == null) {
		c1.lower_tail = b;
	    }
	}

	c1.lower_head = c2.lower_head;

	return total;
    }

    private int offset (int p1, int p2, int a1, int a2, int b1, int b2) {
	int d, s, t;

	if (b2 <= p2 || p2 + a2 <= 0) {
	    return 0;
	}

	t = b2 * a1 - a2 * b1;
	if (t > 0) {
	    if (p2 < 0) {
		s = p2 * a1;
		d = s / a2 - p1;
	    } else if (p2 > 0) {
		s = p2 * b1;
		d = s / b2 - p1;
	    } else {
		d = -p1;
	    }
	} else if (b2 < p2 + a2) {
	    s = (b2 - p2) * a1;
	    d = b1 - (p1 + s / a2);
	} else if (b2 > p2 + a2) {
	    s = (a2 + p2) * b1;
	    d = s / b2 - (p1 + a1);
	} else {
	    d = b1 - (p1 + a1);
	}

	if (d > 0) {
	    return d;
	} else {
	    return 0;
	}
    }

    private Polyline bridge (Polyline line1, int x1, int y1,
			     Polyline line2, int x2, int y2) {
	int dy, dx, s;
	Polyline r;

	dy = y2 + line2.dy - y1;
	if (line2.dy == 0) {
	    dx = line2.dx;
	} else {
	    s = dy * line2.dx;
	    dx = s / line2.dy;
	}

	r = new Polyline(dx, dy, line2.link);
	line1.link = new Polyline(x2 + line2.dx - dx - x1, 0, r);

	return r;
    }


}
