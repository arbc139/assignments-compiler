--- __tmp_ans.txt	2017-11-22 21:09:59.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:09:59.000000000 +0900
@@ -10,15 +10,19 @@
                AssignStmt
                   VarExpr
                      ID: b
-                  BinaryExpr
-                     BinaryExpr
-                        VarExpr
+                  ExprSequence
+                     ExprSequence
+                        CallExpr
                            ID: i
-                        Operator: +
+                           EmptyExpr
+                        ExprSequence
                         IntExpr
                            IntLiteral: 100
-                     Operator: /
-                     VarExpr
+                           EmptyExpr
+                     ExprSequence
+                        CallExpr
                         ID: k
+                           EmptyExpr
+                        EmptyExpr
                EmptyStmt
       EmptyDecl
