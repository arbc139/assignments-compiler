; Jassmin assembly code
; MiniC v. 1.0
.class public Shortcircuit
.super java/lang/Object

.method static <clinit>()V
   .limit stack 1
   .limit locals 0
   return
.end method

.method public <init>()V
   .limit stack 1
   .limit locals 1
   .var 0 is this LShortcircuit; from Label0 to Label1

  Label0:
   aload_0
   invokespecial java/lang/Object/<init>()V
  Label1:
   return
.end method

.method public f()Z
  Label0:
   ; CallStmt, line 2
   ; CallExpr
   ; ActualParam
   ldc "Error: && shortcircuit evaluation not done!\n"
   invokestatic lang/System/putString(Ljava/lang/String;)V
   ; ReturnStmt, line 3
   iconst_0
   ireturn
  Label1:
   .limit locals 1
   .limit stack 150
.end method

.method public ff()Z
  Label0:
   ; CallStmt, line 7
   ; CallExpr
   ; ActualParam
   ldc "Error: || shortcircuit evaluation not done!\n"
   invokestatic lang/System/putString(Ljava/lang/String;)V
   ; ReturnStmt, line 8
   iconst_0
   ireturn
  Label1:
   .limit locals 1
   .limit stack 150
.end method

.method public static main([Ljava/lang/String;)V
  Label0:
   new Shortcircuit
   dup
   invokespecial Shortcircuit/<init>()V
   astore_1
   ; IfStmt, line 13
   iconst_0
   ifeq Label2
   ; CallExpr
   ; "this"-pointer is the first ActualParam with instance methods:
   aload_1
   invokevirtual Shortcircuit/f()Z
   ifeq Label2
   iconst_1
   goto Label3
  Label2:
   iconst_0
  Label3:
   ifeq Label4
   goto Label5
  Label4:
  Label5:
   ; IfStmt, line 15
   iconst_1
   ifne Label6
   ; CallExpr
   ; "this"-pointer is the first ActualParam with instance methods:
   aload_1
   invokevirtual Shortcircuit/ff()Z
   ifne Label6
   iconst_0
   goto Label7
  Label6:
   iconst_1
  Label7:
   ifeq Label8
   goto Label9
  Label8:
  Label9:
  Label1:
   return
   .limit locals 2
   .limit stack 150
.end method
