--- __tmp_ans.txt	2017-11-22 21:10:00.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:00.000000000 +0900
@@ -13,33 +13,37 @@
                         ID: j
                      IntExpr
                         IntLiteral: 1
-                  BinaryExpr
-                     VarExpr
+                  ExprSequence
+                     CallExpr
                         ID: j
-                     Operator: <
+                        EmptyExpr
                      IntExpr
                         IntLiteral: 100
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
