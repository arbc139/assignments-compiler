--- __tmp_ans.txt	2017-11-22 21:01:16.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:16.000000000 +0900
@@ -11,35 +11,81 @@
                   AssignExpr
                      VarExpr
                         ID: j
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
                      IntExpr
                         IntLiteral: 1
-                  BinaryExpr
-                     VarExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 CallExpr
                         ID: j
-                     Operator: <
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           ExprSequence
+                              ExprSequence
                      IntExpr
                         IntLiteral: 100
+                                 EmptyExpr
+                              EmptyExpr
+                        EmptyExpr
+                     EmptyExpr
                   AssignExpr
                      VarExpr
                         ID: j
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: j
-                        Operator: +
+                                       EmptyExpr
+                                    EmptyExpr
+                                 ExprSequence
+                                    ExprSequence
                         IntExpr
                            IntLiteral: 1
+                                       EmptyExpr
+                                    EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                   CompoundStmt
                      EmptyDecl
                      StmtSequence
                         AssignStmt
                            VarExpr
                               ID: y
-                           BinaryExpr
-                              VarExpr
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          CallExpr
                                  ID: y
-                              Operator: +
+                                             EmptyExpr
+                                          EmptyExpr
+                                       ExprSequence
+                                          ExprSequence
                               IntExpr
                                  IntLiteral: 3
+                                             EmptyExpr
+                                          EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
                         EmptyStmt
                EmptyStmt
       EmptyDecl
