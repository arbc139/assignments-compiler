--- __tmp_ans.txt	2017-11-22 21:10:03.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:03.000000000 +0900
@@ -30,21 +30,23 @@
                         ID: i
                      IntExpr
                         IntLiteral: 1
-                  BinaryExpr
-                     VarExpr
+                  ExprSequence
+                     CallExpr
                         ID: i
-                     Operator: <
+                        EmptyExpr
                      IntExpr
                         IntLiteral: 100
                   AssignExpr
                      VarExpr
                         ID: i
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        CallExpr
                            ID: i
-                        Operator: +
+                           EmptyExpr
+                        ExprSequence
                         IntExpr
                            IntLiteral: 1
+                           EmptyExpr
                   CompoundStmt
                      EmptyDecl
                      StmtSequence
@@ -56,17 +58,19 @@
                               EmptyActualParam
                         StmtSequence
                            IfStmt
-                              BinaryExpr
-                                 VarExpr
+                              ExprSequence
+                                 CallExpr
                                     ID: tmp
-                                 Operator: >
-                                 VarExpr
+                                    EmptyExpr
+                                 CallExpr
                                     ID: max
+                                    EmptyExpr
                               AssignStmt
                                  VarExpr
                                     ID: max
-                                 VarExpr
+                                 CallExpr
                                     ID: tmp
+                                    EmptyExpr
                            EmptyStmt
                EmptyStmt
       EmptyDecl
