--- __tmp_ans.txt	2017-11-22 21:01:16.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:16.000000000 +0900
@@ -8,12 +8,24 @@
             EmptyDecl
             StmtSequence
                IfStmt
-                  BinaryExpr
-                     VarExpr
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 CallExpr
                         ID: i
-                     Operator: ==
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           ExprSequence
+                              ExprSequence
                      IntExpr
                         IntLiteral: 22
+                                 EmptyExpr
+                              EmptyExpr
+                        EmptyExpr
+                     EmptyExpr
                   CompoundStmt
                      DeclSequence
                         VarDecl
@@ -25,8 +37,18 @@
                         AssignStmt
                            VarExpr
                               ID: j
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
                            IntExpr
                               IntLiteral: 1
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
                         EmptyStmt
                EmptyStmt
       EmptyDecl
