; Jassmin assembly code
; MiniC v. 1.0
.class public HelloWorld
.super java/lang/Object

.method static <clinit>()V
   .limit stack 1
   .limit locals 0
   return
.end method

.method public <init>()V
   .limit stack 1
   .limit locals 1
   .var 0 is this LHelloWorld; from Label0 to Label1

  Label0:
   aload_0
   invokespecial java/lang/Object/<init>()V
  Label1:
   return
.end method

.method public static main([Ljava/lang/String;)V
  Label0:
   new HelloWorld
   dup
   invokespecial HelloWorld/<init>()V
   astore_1
   ; CallStmt, line 3
   ; CallExpr
   ; ActualParam
   ldc "Hallo MiniC-Welt!\n"
   invokestatic lang/System/putString(Ljava/lang/String;)V
  Label1:
   return
   .limit locals 2
   .limit stack 150
.end method
