--- __tmp_ans.txt	2017-11-22 21:10:01.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:01.000000000 +0900
@@ -13,24 +13,28 @@
                   AssignExpr
                      VarExpr
                         ID: j
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        CallExpr
                            ID: j
-                        Operator: +
+                           EmptyExpr
+                        ExprSequence
                         IntExpr
                            IntLiteral: 1
+                           EmptyExpr
                   CompoundStmt
                      EmptyDecl
                      StmtSequence
                         AssignStmt
                            VarExpr
                               ID: y
-                           BinaryExpr
-                              VarExpr
+                           ExprSequence
+                              CallExpr
                                  ID: y
-                              Operator: +
+                                 EmptyExpr
+                              ExprSequence
                               IntExpr
                                  IntLiteral: 3
+                                 EmptyExpr
                         EmptyStmt
                EmptyStmt
       EmptyDecl