--- __tmp_ans.txt	2017-11-22 21:01:18.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:18.000000000 +0900
@@ -19,17 +19,39 @@
                   VarDecl
                      IntType
                      ID: i
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
                      IntExpr
                         IntLiteral: 17
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                   EmptyDecl
                StmtSequence
                   WhileStmt
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: i
-                        Operator: >
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              ExprSequence
+                                 ExprSequence
                         IntExpr
                            IntLiteral: 0
+                                    EmptyExpr
+                                 EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                      CompoundStmt
                         EmptyDecl
                         StmtSequence
@@ -38,19 +60,42 @@
                                  ID: foo
                                  ActualParamSequence
                                     ActualParam
-                                       VarExpr
+                                       ExprSequence
+                                          ExprSequence
+                                             ExprSequence
+                                                ExprSequence
+                                                   ExprSequence
+                                                      CallExpr
                                           ID: i
+                                                         EmptyExpr
+                                                      EmptyExpr
+                                                   EmptyExpr
+                                                EmptyExpr
+                                             EmptyExpr
+                                          EmptyExpr
                                     EmptyActualParam
                            StmtSequence
                               AssignStmt
                                  VarExpr
                                     ID: i
-                                 BinaryExpr
-                                    VarExpr
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
+                                             ExprSequence
+                                                CallExpr
                                        ID: i
-                                    Operator: -
+                                                   EmptyExpr
+                                                EmptyExpr
+                                             ExprSequence
+                                                ExprSequence
                                     IntExpr
                                        IntLiteral: 1
+                                                   EmptyExpr
+                                                EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
                               EmptyStmt
                   EmptyStmt
          EmptyDecl
