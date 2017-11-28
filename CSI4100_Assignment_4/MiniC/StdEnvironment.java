package MiniC;

import MiniC.Scanner.SourcePos;
import MiniC.AstGen.*;

public final class StdEnvironment {

    // The pre-defined language environment for MiniC:


    // ASTs representing the MiniC standard type declarations:

    public static TypeDecl intTypeDecl, boolTypeDecl, floatTypeDecl,
	stringTypeDecl, voidTypeDecl, errorTypeDecl;

    // ASTs representing the MiniC standard types:

    public static Type intType, boolType, floatType, stringType, voidType,errorType;


    // ASTs representing the declarations of our pre-defined MiniC functions:

    public static FunDecl getInt, putInt, getBool, putBool, getFloat, putFloat;
    public static FunDecl getString, putString;
    public static FunDecl putLn;

    public Program AST;
    private static SourcePos dummyPos = new SourcePos();

    public StdEnvironment () {
	DeclSequence D;
        FormalParamDecl pDecl;
        FormalParamDeclSequence pSeq;

	/*
	 * Generate the declarations for the StdEnvironment,
	 * generate an AST, so that it can be traversed and printed:
	 *
	 */
	intType = new IntType(dummyPos);
	boolType = new BoolType(dummyPos);
	floatType = new FloatType(dummyPos);
	stringType = new StringType(dummyPos);
	voidType = new VoidType(dummyPos);
	errorType = new ErrorType(dummyPos);

        putLn = new FunDecl(voidType,
                             new ID("putLn", dummyPos),
			     new EmptyFormalParamDecl(dummyPos),
			     new EmptyCompoundStmt(dummyPos),
			     dummyPos);
	D = new DeclSequence (putLn, new EmptyDecl(dummyPos), dummyPos);

        pDecl = new FormalParamDecl(stringType,
                                    new ID("s", dummyPos),
				    dummyPos);
        pSeq = new FormalParamDeclSequence(pDecl,
                                           new EmptyFormalParamDecl(dummyPos),
                                           dummyPos);
        putString = new FunDecl(voidType,
                             new ID("putString", dummyPos),
                             pSeq,
			     new EmptyCompoundStmt(dummyPos),
			     dummyPos);
	D = new DeclSequence (putString, D, dummyPos);

        getString = new FunDecl(stringType,
				new ID("getSting", dummyPos),
				new EmptyFormalParamDecl(dummyPos),
				new EmptyCompoundStmt(dummyPos),
				dummyPos);
	D = new DeclSequence (getString, D, dummyPos);

        pDecl = new FormalParamDecl(floatType,
                                    new ID("f", dummyPos),
				    dummyPos);
        pSeq = new FormalParamDeclSequence(pDecl,
                                           new EmptyFormalParamDecl(dummyPos),
                                           dummyPos);
        putFloat = new FunDecl(voidType,
                             new ID("putFloat", dummyPos),
                             pSeq,
			     new EmptyCompoundStmt(dummyPos),
			     dummyPos);
	D = new DeclSequence (putFloat, D, dummyPos);

        getFloat = new FunDecl(floatType,
                               new ID("getFloat", dummyPos),
			       new EmptyFormalParamDecl(dummyPos),
			       new EmptyCompoundStmt(dummyPos),
			       dummyPos);
	D = new DeclSequence (getFloat, D, dummyPos);

        pDecl = new FormalParamDecl(boolType,
                                    new ID("b", dummyPos),
				    dummyPos);
        pSeq = new FormalParamDeclSequence(pDecl,
                                           new EmptyFormalParamDecl(dummyPos),
                                           dummyPos);
        putBool = new FunDecl(voidType,
                             new ID("putBool", dummyPos),
                             pSeq,
			     new EmptyCompoundStmt(dummyPos),
			     dummyPos);
	D = new DeclSequence (putBool, D, dummyPos);

        getBool = new FunDecl(boolType,
                              new ID("getBool", dummyPos),
		  	      new EmptyFormalParamDecl(dummyPos),
			      new EmptyCompoundStmt(dummyPos),
			      dummyPos);
	D = new DeclSequence (getBool, D, dummyPos);


        pDecl = new FormalParamDecl(intType,
                                    new ID("i", dummyPos),
				    dummyPos);
        pSeq = new FormalParamDeclSequence(pDecl,
                                           new EmptyFormalParamDecl(dummyPos),
                                           dummyPos);
        putInt = new FunDecl(voidType,
                             new ID("putInt", dummyPos),
                             pSeq,
			     new EmptyCompoundStmt(dummyPos),
			     dummyPos);
	D = new DeclSequence (putInt, D, dummyPos);

        getInt = new FunDecl(intType,
                             new ID("getInt", dummyPos),
			     new EmptyFormalParamDecl(dummyPos),
			     new EmptyCompoundStmt(dummyPos),
                             dummyPos);
	D = new DeclSequence (getInt, D, dummyPos);

	errorTypeDecl = new TypeDecl(errorType, dummyPos);
	D = new DeclSequence (errorTypeDecl, D, dummyPos);
        voidTypeDecl = new TypeDecl(voidType, dummyPos);
	D = new DeclSequence (voidTypeDecl, D, dummyPos);
        stringTypeDecl = new TypeDecl(stringType, dummyPos);
	D = new DeclSequence (stringTypeDecl, D, dummyPos);
	floatTypeDecl = new TypeDecl(floatType, dummyPos);
	D = new DeclSequence (floatTypeDecl, D, dummyPos);
	boolTypeDecl = new TypeDecl(boolType, dummyPos);
	D = new DeclSequence (boolTypeDecl, D, dummyPos);
	intTypeDecl = new TypeDecl(intType, dummyPos);
	D = new DeclSequence (intTypeDecl, D, dummyPos);
	
	AST = new Program (D, dummyPos);

    }

}
