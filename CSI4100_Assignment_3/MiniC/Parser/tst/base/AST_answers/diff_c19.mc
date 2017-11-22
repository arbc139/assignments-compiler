--- __tmp_ans.txt	2017-11-22 21:09:58.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:09:58.000000000 +0900
@@ -22,11 +22,13 @@
                      AssignStmt
                         VarExpr
                            ID: c
-                        BinaryExpr
+                        ExprSequence
                            IntExpr
                               IntLiteral: 2
-                           Operator: *
-                           VarExpr
+                           ExprSequence
+                              CallExpr
                               ID: a
+                                 EmptyExpr
+                              EmptyExpr
                      EmptyStmt
       EmptyDecl
