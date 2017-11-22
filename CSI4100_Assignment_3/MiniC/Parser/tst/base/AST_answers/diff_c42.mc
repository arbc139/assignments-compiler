--- __tmp_ans.txt	2017-11-22 21:01:19.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:19.000000000 +0900
@@ -8,46 +8,128 @@
             EmptyDecl
             StmtSequence
                IfStmt
-                  BinaryExpr
-                     BinaryExpr
-                        VarExpr
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
+                                             ExprSequence
+                                                CallExpr
                            ID: a
-                        Operator: ==
+                                                   EmptyExpr
+                                                EmptyExpr
+                                             EmptyExpr
+                                          ExprSequence
+                                             ExprSequence
                         IntExpr
                            IntLiteral: 1
-                     Operator: ||
-                     BinaryExpr
-                        BinaryExpr
-                           VarExpr
+                                                EmptyExpr
+                                             EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
+                                             ExprSequence
+                                                ExprSequence
+                                                   CallExpr
                               ID: b
-                           Operator: ==
+                                                      EmptyExpr
+                                                   EmptyExpr
+                                                EmptyExpr
+                                             ExprSequence
+                                                ExprSequence
                            IntExpr
                               IntLiteral: 2
-                        Operator: &&
+                                                   EmptyExpr
+                                                EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
                         UnaryExpr
                            Operator: !
-                           BinaryExpr
-                              VarExpr
+                                          ExprSequence
+                                             ExprSequence
+                                                ExprSequence
+                                                   ExprSequence
+                                                      ExprSequence
+                                                         CallExpr
                                  ID: a
-                              Operator: ==
+                                                            EmptyExpr
+                                                         EmptyExpr
+                                                      EmptyExpr
+                                                   ExprSequence
+                                                      ExprSequence
                               IntExpr
                                  IntLiteral: 22
+                                                         EmptyExpr
+                                                      EmptyExpr
+                                                EmptyExpr
+                                             EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                        EmptyExpr
                   AssignStmt
                      VarExpr
                         ID: a
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: a
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
                   ReturnStmt
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: b
-                        Operator: +
+                                       EmptyExpr
+                                    EmptyExpr
+                                 ExprSequence
+                                    ExprSequence
                         IntExpr
                            IntLiteral: 2
+                                       EmptyExpr
+                                    EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                StmtSequence
                   ReturnStmt
                      EmptyExpr
