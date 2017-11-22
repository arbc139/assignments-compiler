--- __tmp_ans.txt	2017-11-22 21:10:00.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:00.000000000 +0900
@@ -8,19 +8,20 @@
             EmptyDecl
             StmtSequence
                IfStmt
-                  BinaryExpr
-                     VarExpr
+                  ExprSequence
+                     CallExpr
                         ID: x
-                     Operator: <=
+                        EmptyExpr
                      IntExpr
                         IntLiteral: 2
                   IfStmt
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        CallExpr
                            ID: x
-                        Operator: >=
-                        VarExpr
+                           EmptyExpr
+                        CallExpr
                            ID: a
+                           EmptyExpr
                      AssignStmt
                         VarExpr
                            ID: y
