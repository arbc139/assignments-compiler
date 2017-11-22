--- __tmp_ans.txt	2017-11-22 21:10:01.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:01.000000000 +0900
@@ -8,20 +8,23 @@
             EmptyDecl
             StmtSequence
                WhileStmt
-                  BinaryExpr
-                     VarExpr
+                  ExprSequence
+                     CallExpr
                         ID: a
-                     Operator: >=
-                     VarExpr
+                        EmptyExpr
+                     CallExpr
                         ID: b
+                        EmptyExpr
                   AssignStmt
                      VarExpr
                         ID: y
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        CallExpr
                            ID: y
-                        Operator: +
+                           EmptyExpr
+                        ExprSequence
                         IntExpr
                            IntLiteral: 3
+                           EmptyExpr
                EmptyStmt
       EmptyDecl
