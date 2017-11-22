--- __tmp_ans.txt	2017-11-22 21:01:16.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:16.000000000 +0900
@@ -8,44 +8,110 @@
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
                      EmptyDecl
                      StmtSequence
                         IfStmt
-                           BinaryExpr
-                              VarExpr
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          CallExpr
                                  ID: j
-                              Operator: >
+                                             EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                    ExprSequence
+                                       ExprSequence
                               IntExpr
                                  IntLiteral: 1
+                                          EmptyExpr
+                                       EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
                            AssignStmt
                               VarExpr
                                  ID: k
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
                               IntExpr
                                  IntLiteral: 1
+                                             EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
                         EmptyStmt
                   IfStmt
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: j
-                        Operator: ==
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              ExprSequence
+                                 ExprSequence
                         IntExpr
                            IntLiteral: 1
+                                    EmptyExpr
+                                 EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                      AssignStmt
                         VarExpr
                            ID: j
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
                      AssignStmt
                         VarExpr
                            ID: j
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
                         IntExpr
                            IntLiteral: 3
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
                EmptyStmt
       EmptyDecl
