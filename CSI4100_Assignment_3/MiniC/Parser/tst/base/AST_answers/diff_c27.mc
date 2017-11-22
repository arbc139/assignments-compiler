--- __tmp_ans.txt	2017-11-22 21:01:16.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:16.000000000 +0900
@@ -8,28 +8,73 @@
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
                         ID: x
-                     Operator: <=
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           ExprSequence
+                              ExprSequence
                      IntExpr
                         IntLiteral: 2
+                                 EmptyExpr
+                              EmptyExpr
+                        EmptyExpr
+                     EmptyExpr
                   IfStmt
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: x
-                        Operator: >=
-                        VarExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: a
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                      AssignStmt
                         VarExpr
                            ID: y
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
                         IntExpr
                            IntLiteral: 1
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
                      AssignStmt
                         VarExpr
                            ID: y
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
                         IntExpr
                            IntLiteral: 2
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
                EmptyStmt
       EmptyDecl
