--- __tmp_ans.txt	2017-11-22 21:01:18.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:18.000000000 +0900
@@ -19,54 +19,132 @@
                      VarDecl
                         IntType
                         ID: max
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
                         CallExpr
                            ID: ReadInt
                            EmptyActualParam
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
                      EmptyDecl
             StmtSequence
                ForStmt
                   AssignExpr
                      VarExpr
                         ID: i
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
                         ID: i
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
                         ID: i
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: i
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
                               ID: tmp
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
                            CallExpr
                               ID: ReadInt
                               EmptyActualParam
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
                         StmtSequence
                            IfStmt
-                              BinaryExpr
-                                 VarExpr
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
+                                             CallExpr
                                     ID: tmp
-                                 Operator: >
-                                 VarExpr
+                                                EmptyExpr
+                                             EmptyExpr
+                                          EmptyExpr
+                                       ExprSequence
+                                          ExprSequence
+                                             CallExpr
                                     ID: max
+                                                EmptyExpr
+                                             EmptyExpr
+                                          EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
                               AssignStmt
                                  VarExpr
                                     ID: max
-                                 VarExpr
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
+                                             ExprSequence
+                                                CallExpr
                                     ID: tmp
+                                                   EmptyExpr
+                                                EmptyExpr
+                                             EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
                            EmptyStmt
                EmptyStmt
       EmptyDecl
