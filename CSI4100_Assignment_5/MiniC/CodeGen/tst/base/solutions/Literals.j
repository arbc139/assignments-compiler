; Jassmin assembly code
; MiniC v. 1.0
.class public Literals
.super java/lang/Object

.method static <clinit>()V
   .limit stack 1
   .limit locals 0
   return
.end method

.method public <init>()V
   .limit stack 1
   .limit locals 1
   .var 0 is this LLiterals; from Label0 to Label1

  Label0:
   aload_0
   invokespecial java/lang/Object/<init>()V
  Label1:
   return
.end method

.method public static main([Ljava/lang/String;)V
  Label0:
   new Literals
   dup
   invokespecial Literals/<init>()V
   astore_1
   ; CallStmt, line 3
   ; CallExpr
   ; ActualParam
   iconst_1
   invokestatic lang/System/putInt(I)V
   ; CallStmt, line 4
   ; CallExpr
   invokestatic lang/System/putLn()V
   ; CallStmt, line 5
   ; CallExpr
   ; ActualParam
   iconst_1
   invokestatic lang/System/putBool(Z)V
   ; CallStmt, line 6
   ; CallExpr
   invokestatic lang/System/putLn()V
   ; CallStmt, line 7
   ; CallExpr
   ; ActualParam
   fconst_1
   invokestatic lang/System/putFloat(F)V
   ; CallStmt, line 8
   ; CallExpr
   invokestatic lang/System/putLn()V
   ; CallStmt, line 9
   ; CallExpr
   ; ActualParam
   ldc "string 1"
   invokestatic lang/System/putString(Ljava/lang/String;)V
   ; CallStmt, line 10
   ; CallExpr
   invokestatic lang/System/putLn()V
  Label1:
   return
   .limit locals 2
   .limit stack 150
.end method
