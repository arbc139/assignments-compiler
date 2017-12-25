; Jassmin assembly code
; MiniC v. 1.0
.class public euclid
.super java/lang/Object

.method static <clinit>()V
   .limit stack 1
   .limit locals 0
   return
.end method

.method public <init>()V
   .limit stack 1
   .limit locals 1
   .var 0 is this Leuclid; from Label0 to Label1

  Label0:
   aload_0
   invokespecial java/lang/Object/<init>()V
  Label1:
   return
.end method

.method public gcd(II)I
  Label0:
   ; WhileStmt, line 6
  Label2:
   iload_2
   iconst_0
   if_icmpne Label4
   iconst_0
   goto Label5
  Label4:
   iconst_1
  Label5:
   ifeq Label3
   ; IfStmt, line 11
   iload_1
   iload_2
   if_icmpgt Label6
   iconst_0
   goto Label7
  Label6:
   iconst_1
  Label7:
   ifeq Label8
   ; AssignStmt, line 8
   iload_1
   iload_2
   isub
   istore_1
   goto Label9
  Label8:
   ; AssignStmt, line 10
   iload_2
   iload_1
   isub
   istore_2
  Label9:
   goto Label2
  Label3:
   ; ReturnStmt, line 13
   iload_1
   ireturn
  Label1:
   .limit locals 3
   .limit stack 150
.end method

.method public static main([Ljava/lang/String;)V
  Label0:
   new euclid
   dup
   invokespecial euclid/<init>()V
   astore_1
   ; AssignStmt, line 19
   ; CallExpr
   ; "this"-pointer is the first ActualParam with instance methods:
   aload_1
   ; ActualParam
   bipush 22
   ; ActualParam
   bipush 12
   invokevirtual euclid/gcd(II)I
   istore_2
   ; CallStmt, line 20
   ; CallExpr
   ; ActualParam
   iload_2
   invokestatic lang/System/putInt(I)V
   ; CallStmt, line 21
   ; CallExpr
   invokestatic lang/System/putLn()V
  Label1:
   return
   .limit locals 3
   .limit stack 150
.end method
