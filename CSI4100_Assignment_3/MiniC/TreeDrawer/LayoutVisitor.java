package MiniC.TreeDrawer;

import java.awt.*;

import MiniC.AstGen.Visitor;
import MiniC.AstGen.*;

import MiniC.Scanner.SourcePos;

public class LayoutVisitor implements Visitor {

  public DrawingTree Tree;
  public boolean IsGlobal;

  private final int BORDER = 5;
  private final int PARENT_SEP = 30;
  private FontMetrics fontMetrics;
  private boolean draw_pos;

  public LayoutVisitor (FontMetrics fontMetrics, boolean draw_pos) {
    this.fontMetrics = fontMetrics;
    this.IsGlobal = true;
    this.draw_pos = draw_pos;
  }



    public void visit(Program x) {
        String P = FormatPosition(x.getPosition());
        Tree = layoutUnary("Program" + P, x.D);
    }

    public void visit(EmptyDecl x) {
       Tree = layoutNullary("EmptyDecl");
    }

    public void visit(FunDecl x) {
        String P = FormatPosition(x.getPosition());
        IsGlobal = false;
	Tree = layoutQuaternary("FunDecl" + P,
                                 x.tAST, x.idAST, x.paramsAST, x.stmtAST);
	IsGlobal = true;
    }

    public void visit(FormalParamDecl x) {
        String P = FormatPosition(x.getPosition());
        Tree = layoutBinary("FormalParamDecl" + P, x.astType, x.astIdent);
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
        String P = FormatPosition(x.getPosition());
	Tree = layoutBinary("AssignStmt" + P, x.lAST, x.rAST);
    }

    public void visit(IfStmt x) {
        String P = FormatPosition(x.getPosition());
	if (x.elseAST == null) {
	    Tree = layoutBinary("IfStmt" + P, x.eAST, x.thenAST);
	} else { 
	    Tree = layoutTernary("IfStmt" + P, x.eAST, x.thenAST, x.elseAST);
	}
    }

    public void visit(WhileStmt x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutBinary ("WhileStmt" + P, x.eAST, x.stmtAST);
    }

    public void visit(ForStmt x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutQuaternary("ForStmt" + P, x.e1AST, x.e2AST, x.e3AST, x.stmtAST);
    }

    public void visit(ReturnStmt x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutUnary ("ReturnStmt" + P, x.eAST);
    }

    public void visit(CompoundStmt x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutBinary("CompoundStmt" + P, x.astDecl, x.astStmt);
    }

    public void visit(EmptyCompoundStmt x) {
	Tree = layoutNullary("EmptyCompoundStmt");
    }

    public void visit(EmptyStmt x) {
       Tree = layoutNullary("EmptyStmt");
    }

    public void visit(CallStmt x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutUnary ("CallStmt" + P, x.eAST);
    }

    public void visit(VarDecl x) {
        String P = FormatPosition(x.getPosition());
        String l = "VarDecl";
        if (IsGlobal) {
	    l = "G." + l; 
	} else {
	    l = "L." + l;
	}
        l = l + P;
	Tree = layoutTernary(l, x.tAST, x.idAST, x.eAST);
    }

    public void visit(DeclSequence x){
	Tree = layoutBinary("DeclSeq", x.D1, x.D2);
    }

    public void visit(VarExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutUnary("VarExpr" + P, x.Ident);
    }

    public void visit(AssignExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutBinary("AssignExpr" + P, x.lAST, x.rAST);
    }

    public void visit(IntExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutUnary("IntExpr" + P, x.astIL);
    }

    public void visit(FloatExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutUnary("FloatExpr" + P, x.astFL);
    }

    public void visit(BoolExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutUnary("BoolExpr" + P, x.astBL);
    }

    public void visit(StringExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutUnary("StringExpr" + P, x.astSL);
    }

    public void visit(ArrayExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutBinary("ArrayExpr" + P, x.idAST, x.indexAST);
    }

    public void visit(BinaryExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutTernary("BinaryExpr" + P, x.lAST, x.oAST, x.rAST);
    }

    public void visit(UnaryExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutBinary("UnaryExpr" + P, x.oAST, x.eAST);
    }

    public void visit(EmptyExpr x) {
	Tree = layoutNullary("EmptyExpr");
    }

    public void visit(ActualParam x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutUnary("ActualParam" + P, x.pAST);
    }

    public void visit(EmptyActualParam x) {
	Tree = layoutNullary("EmptyActualParam");
    }

    public void visit(ActualParamSequence x) {
	Tree = layoutBinary("ActualParamSeq", x.lAST, x.rAST);
    }

    public void visit(CallExpr x) {
        String P = FormatPosition(x.getPosition());
	Tree = layoutBinary("CallExpr" + P, x.idAST, x.paramAST);
    }

    public void visit(ExprSequence x) {
	Tree = layoutBinary("ExprSeq", x.lAST, x.rAST);
    }

    public void visit(ID x) {
	Tree = layoutNullary(x.Lexeme);
    }

    public void visit(Operator x) {
	Tree = layoutNullary(x.Lexeme);
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
        String P = FormatPosition(x.getPosition());
	Tree = layoutBinary("ArrayType" + P, x.astType, x.astExpr);
    }

    public void visit(ErrorType x) {
	Tree = layoutNullary("Error");
    }

    private String FormatPosition(SourcePos pos) {
	if (draw_pos) {
  	   return " "
               + Integer.toString (pos.StartLine)
               + "(" + Integer.toString (pos.StartCol) + ").."
	       +  Integer.toString (pos.EndLine)
	       + "(" + Integer.toString (pos.EndCol) + ")";
	} else {
	    return "";
	}
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
