package MiniC.CodeGen;

import java.io.*;
import MiniC.AstGen.*;
import MiniC.StdEnvironment;
import MiniC.ErrorReporter;

public class Emitter implements Visitor {

    private ErrorReporter reporter;
    private FileWriter fstream;
    private BufferedWriter out;
    private String ClassName;
    private int indent;
    private final int INDENT_LEVEL = 3; //amount of indentation per level
    private Frame frame;
    private int MaxOperandStackSize = 150;
    // Upper bound for the maximum operand stack height for a MiniC function.
    // The actual stack height can be determined by interpreting the function's
    // bytecode. 
    private int LabelIndent;
    private boolean isMain; // true if we are generating code for "main".
    private boolean GlobalScope; // true if we are in the outermost "global" scope.


    public Emitter (String infile, ErrorReporter reporter) {
        try {
           this.isMain = false;
           this.GlobalScope= true;
           this.reporter = reporter;
           LabelIndent = 1;
           String outfile;
           String namepart;
           // Create output file name:
           File f = new File(infile);
           namepart = f.getName(); // strip directory part
           int l = namepart.length();
           if ( namepart.charAt(l-3) == '.'
                && namepart.charAt(l-2) == 'm'
                && namepart.charAt(l-1) == 'c') {
              ClassName = new String(namepart.substring(0, l-3));
              outfile = new String(namepart.substring(0, l-2));
              outfile = outfile.concat("j");
           } else {
              ClassName = new String(namepart);
              outfile= new String(namepart);
              outfile = outfile.concat(".j");
           }
           // Create output file: 
           fstream = new FileWriter(outfile);
           out = new BufferedWriter(fstream);
	   indent = 0;
       } catch (Exception e) {
            //Catch exception if any:
           System.err.println("Error: " + e.getMessage());
           System.exit(1);
       }
    }

    // top-level routine, called by the compiler driver:
    public void genCode(Program progAST) {
        visit(progAST);
        try {
           out.close();
        } catch (Exception e) {
           //Catch exception if any
           System.err.println("Error: " + e.getMessage());
           System.exit(1);
        }
    }

    /*
     * emit* routines output Jasmin assembly code of various sorts.
     */

    // Emit a single string using indentation:
    private void emit (String s) {
	try {
	    for (int i = 1; i <= indent*INDENT_LEVEL; i++) {
	       out.write(" ");
	    }
	    out.write(s + "\n");
	} catch (Exception e) {
	    System.err.println("Error: " + e.getMessage());
	    System.exit(1);
	}
    }

    // Emit a single string, but do not indent.
    private void emitNoIndent (String s) {
	try {
	    out.write(s + "\n");
	} catch (Exception e) {
	    System.err.println("Error: " + e.getMessage());
	    System.exit(1);
	}
    }

    private void emit (String s, int value) {
        emit (s + " " + value);
    }

    private void emit (String s, float value) {
        emit (s + " " + value);
    }

    // For a given label nr, return the string representation
    // of that label:
    private String getLabelString(int label) {
	return new String("Label" + label);
    }

    // Emit the defining occurrence of a label:
    private void emitLabel(int label) {
       assert(label >= 0);
       String Ind = new String(" ");
       for (int i = 1; i <= LabelIndent; i++) {
          Ind = Ind.concat(" ");
       }
          emitNoIndent (Ind + "Label" + label + ":");
    }

    // Emit an integer constant:
    private void emitICONST(int value) {
      if(value == -1) {
         emit(JVM.ICONST_M1);
      } else if(value >= 0 && value <= 5) {
         emit(JVM.ICONST + "_" + value);
      } else if(value >= -128 && value <= 127) {
         emit(JVM.BIPUSH, value);
      } else if(value >= -32768 && value <= 32767) {
         emit(JVM.SIPUSH, value);
      } else {
         emit(JVM.LDC, value);
      }
    }

    // Emit a floating point constant:
    private void emitFCONST(float value) {
      if(value == 0.0) {
         emit(JVM.FCONST_0);
      } else if(value == 1.0) {
         emit(JVM.FCONST_1);
      } else if(value == 2.0) {
         emit(JVM.FCONST_2);
      } else {
         emit(JVM.LDC, value);
      }
    }

    // Emit a boolean constant:
    private void emitBCONST(boolean value) {
      if(value) {
         emit(JVM.ICONST_1); // true = 1 with the JVM
      } else {
         emit(JVM.ICONST_0);
      }
    }

    // Emit an integer load instruction:
    private void emitILOAD(int LocalVarIndex) {
      if(LocalVarIndex == 0)
         emit(JVM.ILOAD_0);
      else if(LocalVarIndex == 1)
         emit(JVM.ILOAD_1);
      else if(LocalVarIndex == 2)
         emit(JVM.ILOAD_2);
      else if(LocalVarIndex == 3)
         emit(JVM.ILOAD_3);
      else
         emit(JVM.ILOAD, LocalVarIndex);
    }

    // Emit an integer store instruction:
    private void emitISTORE(int LocalVarIndex) {
      if(LocalVarIndex == 0)
         emit(JVM.ISTORE_0);
      else if(LocalVarIndex == 1)
         emit(JVM.ISTORE_1);
      else if(LocalVarIndex == 2)
         emit(JVM.ISTORE_2);
      else if(LocalVarIndex == 3)
         emit(JVM.ISTORE_3);
      else
         emit(JVM.ISTORE, LocalVarIndex);
    }

    // Emit a floating point load instruction:
    private void emitFLOAD(int LocalVarIndex) {
      if(LocalVarIndex == 0)
         emit(JVM.FLOAD_0);
      else if(LocalVarIndex == 1)
         emit(JVM.FLOAD_1);
      else if(LocalVarIndex == 2)
         emit(JVM.FLOAD_2);
      else if(LocalVarIndex == 3)
         emit(JVM.FLOAD_3);
      else
         emit(JVM.FLOAD, LocalVarIndex);
    }

    // Emit a floating point store instruction:
    private void emitFSTORE(int LocalVarIndex) {
      if(LocalVarIndex == 0)
         emit(JVM.FSTORE_0);
      else if(LocalVarIndex == 1)
         emit(JVM.FSTORE_1);
      else if(LocalVarIndex == 2)
         emit(JVM.FSTORE_2);
      else if(LocalVarIndex == 3)
         emit(JVM.FSTORE_3);
      else
         emit(JVM.FSTORE, LocalVarIndex);
    }

    // Emit a return statement of a given type:
    private void emitRETURN(Type T){
        if (T.Tequal(StdEnvironment.intType)
            || T.Tequal(StdEnvironment.boolType))
	    {
		emit(JVM.IRETURN);
	    } else if(T.Tequal(StdEnvironment.floatType)) {
	    emit(JVM.FRETURN);
	} else if(T.Tequal(StdEnvironment.voidType)) {
	    emit(JVM.RETURN);
	}
    }

    // Emit the constructor for the class of our MiniC program:
    private void emitConstructor() {
        emit ("\n.method public <init>()V");
        indent++;
        emit (".limit stack 1");
        emit (".limit locals 1");
        emit (".var 0 is this L" + ClassName + "; from Label0 to Label1\n");
        emitLabel (0);
        emit ("aload_0");
        emit ("invokespecial java/lang/Object/<init>()V");
        emitLabel (1);
        emit ("return");
        indent--;
        emit (".end method");
    }

    // Emit declarations for the static class variables. Static class variables
    // correspont to MiniC global variables.
    // This function recursively traverses the declarations in the global
    // block of the program.
    private void emitStaticClassVariableDeclaration(Decl d) {
       assert (d != null);
       if (d instanceof DeclSequence) {
          DeclSequence SD = (DeclSequence) d;
          emitStaticClassVariableDeclaration(SD.D1);
          emitStaticClassVariableDeclaration(SD.D2);
       } else if (d instanceof VarDecl) {
          VarDecl D = (VarDecl) d;
          assert (d.isGlobal());
          Type T= typeOfDecl (D);
          emit (".field static " + D.idAST.Lexeme + " "
                + getTypeDescriptorLabel(T));         
       }
    }

    // Emit initializers for the static class variables.
    // This function recursively traverses the declarations in the global
    // block of the program.
    private void emitInitializer(Decl d) {
       assert (d != null);
       if (d instanceof DeclSequence) {
          DeclSequence SD = (DeclSequence) d;
          emitInitializer(SD.D1);
          emitInitializer(SD.D2);
       } else if (d instanceof VarDecl) {
          VarDecl D = (VarDecl) d;
          assert (d.isGlobal());
          Type T= typeOfDecl (D);
          if (T.Tequal(StdEnvironment.intType)
              || T.Tequal(StdEnvironment.boolType)) {
             emit(JVM.ICONST_0);
          } else if(T.Tequal(StdEnvironment.floatType)) {
             emit(JVM.FCONST_0);
          } else {
             assert(false);
          }
          emitStaticVariableReference(D.idAST, D.tAST, true); 
       }
    }

    // Emit a class initializer method for the global MiniC variables.
    // Global MiniC variables correspond to static Java class variables
    // in our code generation model. Our MiniC assembly code needs one
    // class initializer where all class variables are initialized. 
    private void emitClassInitializer(Decl d) {
       emit ("\n.method static <clinit>()V");
       indent++;
       emit (".limit stack 1");
       emit (".limit locals 0");
       emitInitializer(d);
       emit (JVM.RETURN);
       indent--;
       emit (".end method");
    }

    // Get the JVM type descriptor for a given MiniC type:
    private String getTypeDescriptorLabel(Type t) {
        String l = new String("");
        assert((t != null) && !(t instanceof ErrorType));
        if (t.Tequal(StdEnvironment.intType)) {
            l = new String ("I");
        } else if (t.Tequal(StdEnvironment.boolType)) {
            l = new String ("Z");
        } else if (t.Tequal(StdEnvironment.floatType)) {
            l = new String ("F");
        } else if (t.Tequal(StdEnvironment.stringType)) {
            l = new String ("Ljava/lang/String;");
        } else if (t.Tequal(StdEnvironment.voidType)) {
            l = new String ("V");
        } else {
            assert(false);
        }
        return l;
    }

    // Get the type of a given declaration:
    private Type typeOfDecl(AST d) {
        Type retType;
        Type T;
        assert(d != null);
        assert ((d instanceof FunDecl) || (d instanceof VarDecl)
                || (d instanceof FormalParamDecl));
        if (d instanceof FunDecl) {
            T = ((FunDecl) d).tAST;
        } else if (d instanceof VarDecl) {
            T = ((VarDecl) d).tAST;
        } else {
            T = ((FormalParamDecl) d).astType;
        }
        if (T instanceof ArrayType) {
            reporter.reportError("Arrays not implemented", "", d.pos);
            retType = ((ArrayType) T).astType;
        } else {
            retType = T;
        }
        return retType;
    }

    // Global MiniC variables become static variables in the Jasmine assembly code.
    // References to those variables are generated using this function.
    // The boolean "write" value determines between read access (write=false) and
    // write access (write=true).
    private void emitStaticVariableReference(ID Ident, Type T, boolean write) {
       String ref;
       if(write)
         ref = new String(JVM.PUTSTATIC);
       else
         ref = new String(JVM.GETSTATIC);
       ref = ref.concat(" " + ClassName + "." + Ident.Lexeme + " "
             + getTypeDescriptorLabel (T));
       emit (ref);
    }

    // Returns true if the function declaration passed as parameter must become a static
    // method (aka class method) in the generated Jasmine assembly code.
    // The methods belonging to this category are:
    //  - all functions from the StdEnvironment
    //  - main()
    private boolean isStaticMethod(FunDecl f) {
       String N = f.idAST.Lexeme;
       if (N.equals(StdEnvironment.getInt.idAST.Lexeme) ||
           N.equals(StdEnvironment.putInt.idAST.Lexeme) ||
           N.equals(StdEnvironment.getBool.idAST.Lexeme) ||
           N.equals(StdEnvironment.putBool.idAST.Lexeme) ||
           N.equals(StdEnvironment.getFloat.idAST.Lexeme) ||
           N.equals(StdEnvironment.putFloat.idAST.Lexeme) ||
           N.equals(StdEnvironment.getString.idAST.Lexeme) ||
           N.equals(StdEnvironment.putString.idAST.Lexeme) ||
           N.equals(StdEnvironment.putLn.idAST.Lexeme) ||
           N.equals("main")) {
          return true;
       } else {
          return false;
       }
    }

    // Given a function declaration FunDecl, this method returns the number
    // of formal parameters. E.g., for the following function
    //
    //    void foo (int a, bool b){}
    //
    // the return value will be 2.
    // Note: this function assumes the AST tree layout from Assignment 3.
    private int GetNrOfFormalParams(FunDecl f) {
        int NrArgs = 0;
        Decl D = f.paramsAST;
        assert ((D instanceof EmptyFormalParamDecl) ||
                (D instanceof FormalParamDeclSequence));
        if(D instanceof EmptyFormalParamDecl)
            return 0;
        while (D instanceof FormalParamDeclSequence) {
            NrArgs++;
            D = ((FormalParamDeclSequence) D).rAST;
            assert ((D instanceof EmptyFormalParamDecl) ||
                    (D instanceof FormalParamDeclSequence));
        }
        return NrArgs;
    }

    // Given a function declaration FunDecl, this method returns the AST for 
    // the formal parameter nr (nr is the number of the parameter).
    // E.g., for the following function and nr=2,
    //
    //    void foo (int a, bool b){}
    //
    // the AST returned will be "bool b".
    // Note: this function assumes the AST tree layout from Assignment 3.
    private FormalParamDecl GetFormalParam (FunDecl f, int nr) {
        int fArgs = GetNrOfFormalParams(f);
        assert(fArgs >= 0);
        assert (nr <= fArgs);
        FormalParamDeclSequence S = (FormalParamDeclSequence) f.paramsAST;
        for (int i = 1; i < nr; i++) {
            assert(S.rAST instanceof FormalParamDeclSequence);
            S = (FormalParamDeclSequence) S.rAST;
        }
        assert(S.lAST instanceof FormalParamDecl);
        return (FormalParamDecl) S.lAST;
    }

    // Construct the descriptor for a given function declaration.
    private String getDescriptor(FunDecl f) {
        String ret = new String ("(");
        for (int arg = 1; arg <= GetNrOfFormalParams(f); arg ++) {
            FormalParamDecl D = GetFormalParam (f, arg);
            ret = ret.concat(getTypeDescriptorLabel(typeOfDecl(D)));
        }
        ret = ret.concat(")");
        ret = ret.concat(getTypeDescriptorLabel(f.tAST));
        return ret;
    }

    /*
     *
     * Here the Visitor methods for our code generator start:
     *
     *
     */

    public void visit(Program x) {
	emit("; Jassmin assembly code");
	emit("; MiniC v. 1.0");
        emit(".class public " + ClassName);
        emit(".super java/lang/Object");
	//emit("; Program");
        if(x.D instanceof VarDecl) {
          ((VarDecl) x.D).setGlobal();
        }
        emitStaticClassVariableDeclaration(x.D);
        emitClassInitializer(x.D);
        emitConstructor();
	x.D.accept(this);
    }

    public void visit(EmptyDecl x) {
	//emit("; EmptyDecl");
    }

    public void visit(FunDecl x) {
        GlobalScope = false;
        //Allocate a frame for this function:
        isMain = x.idAST.Lexeme.equals("main");
        if(isMain) {
           frame = new Frame(true);
           emit ("\n.method public static main([Ljava/lang/String;)V");
           // .var for main"s "this" pointer:
           //emit (".var 0 is this L" + ClassName + "; from Label0 to Label1");
           // .var for main's String[] argument:
           //emit (".var 1 is arg0 [Ljava/lang/String; from Label0 to Label1");

        } else {
           frame = new Frame(false);
           emit ("\n.method public " + x.idAST.Lexeme
                 + getDescriptor(x));
	   x.paramsAST.accept(this); // process formal parameters to adjust the
                                     // local variable count.
        }
	indent++;
        int L0 = frame.getNewLabel();
        int L1 = frame.getNewLabel();
        emitLabel(L0);
	if(isMain) {
           emit("new " + ClassName);
           emit("dup");
           emit("invokespecial " + ClassName + "/<init>()V");
           emit("astore_1");
	}
        //x.tAST.accept(this);
	//x.idAST.accept(this);
	x.stmtAST.accept(this);
        emitLabel(L1);
	if(isMain) {
          emit(JVM.RETURN);
	}
        emit(".limit locals " + frame.getNewLocalVarIndex());
        emit(".limit stack " + MaxOperandStackSize);
	indent--;
        emit(".end method");
        GlobalScope = true;
    }

    public void visit(TypeDecl x) {
        assert(false); // Can only occur in the StdEnvironment AST!
   }

    public void visit(FormalParamDecl x) {
	//emit("; FormalParamDecl");
        //TBD: here you need to allocate a new local variable index to the
        //     formal parameter.
        //     Relevant: x.index, frame.getNewLocalVarIndex();

    }

    public void visit(FormalParamDeclSequence x) {
	//emit("; FormalParamDeclSequence");
	x.lAST.accept(this);
	x.rAST.accept(this);
    }

    public void visit(EmptyFormalParamDecl x) {
	//emit("; EmptyFormalParamDecl");
    }

    public void visit(StmtSequence x) {
	x.s1AST.accept(this);
	x.s2AST.accept(this);
    }

    public void visit(AssignStmt x) {
	emit("; AssignStmt, line " + x.pos.StartLine);
	//x.lAST.accept(this);
	x.rAST.accept(this);
        if (x.lAST instanceof VarExpr) {
           VarExpr V = (VarExpr) x.lAST;
           Decl D = (Decl) V.Ident.declAST; 
           Type T = typeOfDecl(D);
           //TBD: here you have to distinguish between local and global MiniC variables.
           //     Local variables are kept in the JVM's local variable array.
           //     Global variables are kept as static JVM class variables.
           //     The code for the right-hand side of the assignment statement has already
           //     been generated by the x.rAST.accept(). Now the result of the right-hand side
           //     of the expression needs to be written back from the stack to the left-hand
           //     side variable.
           //
           //     Relevant functions: isGlobal()
           //                         emitStaticVariableReference()
           //                         emitISTORE()
           //                         emitFSTORE()
           //

        } else {
           assert(false); // Arrays not implemented.
        }
    }

    public void visit(IfStmt x) {
	emit("; IfStmt, line " + x.pos.StartLine);
        // The following code evaluates the condition of the if statement.
        // After execution of this code, the stack will contain 0 if the condition
        // evaluated to false, and 1 if the condition evaluated to true.
        // You should apply the template for if statements from the lecture slides.
        x.eAST.accept(this);
        // Allocate 2 new labes for this if statement.
        int L1 = frame.getNewLabel();
        int L2 = frame.getNewLabel();
        //TBD: your code goes here...
        x.thenAST.accept(this);
        //TBD: your code goes here...
        if(x.elseAST != null) {
            x.elseAST.accept(this);
        }
        //TBD: your code goes here...

    }

    public void visit(WhileStmt x) {
	emit("; WhileStmt, line " + x.pos.StartLine);
        // You should apply the template for while loops from the lecture slides.
        // TBD:

    }

    public void visit(ForStmt x) {
	emit ("; ForStmt, line " + x.pos.StartLine);
        // No template was given for "for" loops, but you can find out by compiling a
        // Java "for" loop to bytecode, use "dejasmin" and look how it is done there.
        // TBD:

    }

    public void visit(ReturnStmt x) {
	emit("; ReturnStmt, line " + x.pos.StartLine);
        x.eAST.accept(this);
        if(x.eAST instanceof EmptyExpr) {
	    emitRETURN(StdEnvironment.voidType);
	} else {
	    emitRETURN(x.eAST.type);
	}
    }

    public void visit(CompoundStmt x) {
	x.astDecl.accept(this);
	x.astStmt.accept(this);
    }

    public void visit(EmptyStmt x) {
	//emit("; EmptyStmt");
    }

    public void visit(EmptyCompoundStmt x) {
	//emit("; EmptyCompoundStmt");
    }

    public void visit(CallStmt x) {
	emit("; CallStmt, line " + x.pos.StartLine);
	x.eAST.accept(this);
    }

    public void visit(VarDecl x) {
        x.tAST.accept(this);
        x.idAST.accept(this);
	x.eAST.accept(this);
        //TBD: if this variable declaration declares a local variable, then
        //     you have to allocate a new local variable index from "frame"
        //     and assign it to x.index.
        //     Relevant functions:
        //                        isGlobal()
        //                        frame.getNewLocalVarIndex

    }

    public void visit(DeclSequence x){
        if((x.D1 instanceof VarDecl) && GlobalScope) {
          ((VarDecl) x.D1).setGlobal();
        }
        if((x.D2 instanceof VarDecl) && GlobalScope) {
          ((VarDecl) x.D2).setGlobal();
        }
	x.D1.accept(this);
	x.D2.accept(this);
    }

    public void visit(VarExpr x) {
        //Here we are dealing with read-accesses of applied occurrences of variables.
        //Why only read-access? Basically, no left-hand side of an assignment statement
        //will occur here, because we do not invoke accept() on left-hand sides of
        //assignment statements. This means that left-hand sides of assignments are not
        //traversed; they are handled right at the visit method for AssignStmt.
        //
        // Example1: a = b + 1
        // The left-hand side of the assignment won't be traversed ("a"). But the
        // right-hand side ("b+1") will.
        //
        // Example2: foo(247+x)
        // "x" will be another VarExpr, again a read-access.
        //
        //What you should do:
        // - if x is global, emit a static variable access to push the static
        //   variable onto the stack.
        // - if x is a local variable, you need to emit an ILOAD or an FLOAD,
        //   depending on the type of variable (ILOAD for int and bool).
        //     Relevant functions: isGlobal()
        //                         emitStaticVariableReference()
        //                         emitILOAD(), emitFLOAD
        Decl D = (Decl) x.Ident.declAST;
        Type T = typeOfDecl (D);
        //TBD: your code goes here...

    }

    public void visit(AssignExpr x) {
	emit("; AssignExpr");
	//x.lAST.accept(this);
	//x.rAST.accept(this);
	x.rAST.accept(this);
        if (x.lAST instanceof VarExpr) {
           VarExpr V = (VarExpr) x.lAST;
           Decl D = (Decl) V.Ident.declAST; 
           Type T = typeOfDecl(D);
           if(D.isGlobal()) {
              emitStaticVariableReference(V.Ident, typeOfDecl(V.Ident.declAST), true);
           } else {
              if(T.Tequal(StdEnvironment.intType)
                 || T.Tequal(StdEnvironment.boolType)) {
                 emitISTORE(D.index);
              } else if (T.Tequal(StdEnvironment.floatType)) {
                 emitFSTORE(D.index);
              } else {
                 assert(false);
              }
           } 
        } else {
           assert(false); // Arrays not implemented.
        }
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
	emit("; ArrayExpr");
	x.idAST.accept(this);
	x.indexAST.accept(this);
    }

    public void visit(BinaryExpr x) {
	//emit("; BinaryExpr");
        String Op = new String(x.oAST.Lexeme);
        if(Op.equals("&&")) {
            int L1 = frame.getNewLabel();
            int L2 = frame.getNewLabel();
            //TBD: implement the code template for && short circuit evaluation
            //     from the lecture slides.

            return;
        }
        if(Op.equals("||")) {
            //TBD: implement || short circuit evaluation.
            //     Similar to &&, you may use a Java example to figure it out..

            return;
        }
        /*
         * Here we treat +, -, *, / >, >=, <, <=, ==, !=
         * See the code templates in the lecture slides. Remaining cases are
         * similar, you can check how the javac compiler does it.
         */
        x.lAST.accept(this);
        x.rAST.accept(this);
        //TBD:

    }

    public void visit(UnaryExpr x) {
	//emit("; UnaryExpr");
        String Op = new String(x.oAST.Lexeme);
        x.eAST.accept(this);
        // Here we treat the following cases:
        //   unary "-": emit JFM.INEG for integers
        //   unary "+": do nothing
        //   "!": you can use the following code template:
        //
        //    !E  =>    [[E]]
        //              ifne Label1
        //              iconst_1
        //              goto Label2
        //           Label1:
        //              iconst_0
        //           Label2:
        //TBD:

    }

    public void visit(EmptyExpr x) {
	// emit("; EmptyExpr");
    }

    public void visit(ActualParam x) {
	emit("; ActualParam");
	x.pAST.accept(this);
    }

    public void visit(EmptyActualParam x) {
	// emit("; EmptyActualParam");
    }

    public void visit(ActualParamSequence x) {
	// emit("; ActualParamSequence");
	x.lAST.accept(this);
	x.rAST.accept(this);
    }

    public void visit(CallExpr x) {
        emit("; CallExpr");
	//x.idAST.accept(this);
	assert(x.idAST.declAST instanceof FunDecl);
	FunDecl F = (FunDecl )x.idAST.declAST;
        if(!isStaticMethod(F)) {
            emit("; \"this\"-pointer is the first ActualParam with instance methods:");
	    if(isMain)
	       emit(JVM.ALOAD_1);
	    else
	       emit(JVM.ALOAD_0);
	}
	x.paramAST.accept(this);
        if(isStaticMethod(F)) {
	    emit(JVM.INVOKESTATIC + " lang/System/" +
                 x.idAST.Lexeme + getDescriptor(F));
	} else {
        //TBD: in case of an instance method, you need emit an JVM.INVOKEVIRTUAL instruction.
        //     the name of the function consists of <ClassName>/<functionname><functiondescriptor>.
        //      Relevant variables/functions: see above for static methods.

	}
    }

    public void visit(ExprSequence x) {
	// emit("; ExprSequence");
	x.lAST.accept(this);
	x.rAST.accept(this);
    }

    public void visit(ID x) {
	// emit("; ID: " + x.Lexeme);
    }

    public void visit(Operator x) {
	// emit("; Operator: " + x.Lexeme);
    } 

    public void visit(IntLiteral x) {
	//emit("; IntLiteral: " + x.Lexeme + "\n");
        //TBD: here you have to emit an ICONST instruction to load the integer literal
        //     onto the JVM stack. (see emitICONST).

    } 

    public void visit(FloatLiteral x) {
	//emit("; FloatLiteral: " + x.Lexeme + "\n");
       //TBD: same for float

    } 

    public void visit(BoolLiteral x) {
	//emit("; BoolLiteral: " + x.Lexeme + "\n");
        //TBD: and bool...

    } 

    public void visit(StringLiteral x) {
	//emit("; StringLiteral: " + x.Lexeme);
	emit(JVM.LDC + " \"" + x.Lexeme +"\"");
    } 

    public void visit(IntType x) {
	//emit("; IntType");
    }

    public void visit(FloatType x) {
	//emit("; FloatType");
    }

    public void visit(BoolType x) {
	//emit("; BoolType");
    }

    public void visit(StringType x) {
	//emit("; StringType");
    }

    public void visit(VoidType x) {
	//emit("; VoidType");
    }

    public void visit(ArrayType x) {
	//emit("; ArrayType");
    }

    public void visit(ErrorType x) {
	emit("; ErrorType");
        assert(false);
    }

}
