--- __tmp_ans.txt	2017-11-22 21:10:00.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:00.000000000 +0900
@@ -8,10 +8,10 @@
             EmptyDecl
             StmtSequence
                IfStmt
-                  BinaryExpr
-                     VarExpr
+                  ExprSequence
+                     CallExpr
                         ID: i
-                     Operator: ==
+                        EmptyExpr
                      IntExpr
                         IntLiteral: 22
                   CompoundStmt
