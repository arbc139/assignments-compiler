--- __tmp_ans.txt	2017-11-22 21:01:19.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:19.000000000 +0900
@@ -10,39 +10,93 @@
                AssignStmt
                   VarExpr
                      ID: a
-                  BinaryExpr
-                     ArrayExpr
-                        VarExpr
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 CallExpr
                            ID: b
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
+                                             ExprSequence
+                                                ExprSequence
                         IntExpr
                            IntLiteral: 10
-                     Operator: +
-                     VarExpr
+                                                   EmptyExpr
+                                                EmptyExpr
+                                             EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                 EmptyExpr
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                         ID: c
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
+                     EmptyExpr
                StmtSequence
                   AssignStmt
                      ArrayExpr
                         VarExpr
                            ID: b
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
                         IntExpr
                            IntLiteral: 11
-                     ArrayExpr
-                        VarExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: b
-                        BinaryExpr
-                           BinaryExpr
-                              BinaryExpr
-                                 VarExpr
+                                       ExprSequence
+                                          ExprSequence
+                                             ExprSequence
+                                                ExprSequence
+                                                   ExprSequence
+                                                      CallExpr
                                     ID: c
-                                 Operator: -
+                                                         EmptyExpr
+                                                      EmptyExpr
+                                                   ExprSequence
+                                                      ExprSequence
                                  IntExpr
                                     IntLiteral: 1
-                              Operator: +
+                                                         EmptyExpr
+                                                      ExprSequence
+                                                         ExprSequence
                               IntExpr
                                  IntLiteral: 21
-                           Operator: -
+                                                            EmptyExpr
+                                                         ExprSequence
+                                                            ExprSequence
                            CallExpr
                               ID: foo
                               EmptyActualParam
+                                                               EmptyExpr
+                                                            EmptyExpr
+                                                EmptyExpr
+                                             EmptyExpr
+                                          EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                   EmptyStmt
       EmptyDecl
