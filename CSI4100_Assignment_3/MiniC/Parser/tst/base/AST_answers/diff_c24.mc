--- __tmp_ans.txt	2017-11-22 21:01:16.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:16.000000000 +0900
@@ -8,24 +8,56 @@
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
                   AssignStmt
                      VarExpr
                         ID: j
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
                      IntExpr
                         IntLiteral: 1
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                   CallStmt
                      CallExpr
                         ID: foo
                         ActualParamSequence
                            ActualParam
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
                               IntExpr
                                  IntLiteral: 123
+                                             EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
                            EmptyActualParam
                EmptyStmt
       EmptyDecl
