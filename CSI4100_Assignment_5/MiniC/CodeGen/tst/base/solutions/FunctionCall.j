; Jassmin assembly code
; MiniC v. 1.0
.class public FunctionCall
.super java/lang/Object

.method static <clinit>()V
   .limit stack 1
   .limit locals 0
   return
.end method

.method public <init>()V
   .limit stack 1
   .limit locals 1
   .var 0 is this LFunctionCall; from Label0 to Label1

  Label0:
   aload_0
   invokespecial java/lang/Object/<init>()V
  Label1:
   return
.end method

.method public foo(IZ)V
  Label0:
   ; CallStmt, line 2
   ; CallExpr
   ; ActualParam
   ldc "foo's arguments: "
   invokestatic lang/System/putString(Ljava/lang/String;)V
   ; CallStmt, line 3
   ; CallExpr
   ; ActualParam
   iload_1
   invokestatic lang/System/putInt(I)V
   ; CallStmt, line 4
   ; CallExpr
   ; ActualParam
   ldc ", "
   invokestatic lang/System/putString(Ljava/lang/String;)V
   ; CallStmt, line 5
   ; CallExpr
   ; ActualParam
   iload_2
   invokestatic lang/System/putBool(Z)V
   ; CallStmt, line 6
   ; CallExpr
   invokestatic lang/System/putLn()V
   ; ReturnStmt, line 7
   return
  Label1:
   .limit locals 3
   .limit stack 150
.end method

.method public static main([Ljava/lang/String;)V
  Label0:
   new FunctionCall
   dup
   invokespecial FunctionCall/<init>()V
   astore_1
   ; CallStmt, line 12
   ; CallExpr
   ; "this"-pointer is the first ActualParam with instance methods:
   aload_1
   ; ActualParam
   sipush 300
   ; ActualParam
   iconst_1
   invokevirtual FunctionCall/foo(IZ)V
  Label1:
   return
   .limit locals 2
   .limit stack 150
.end method
