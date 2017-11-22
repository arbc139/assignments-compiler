--- __tmp_ans.txt	2017-11-22 21:10:03.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:03.000000000 +0900
@@ -24,10 +24,10 @@
                   EmptyDecl
                StmtSequence
                   WhileStmt
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        CallExpr
                            ID: i
-                        Operator: >
+                           EmptyExpr
                         IntExpr
                            IntLiteral: 0
                      CompoundStmt
@@ -38,19 +38,22 @@
                                  ID: foo
                                  ActualParamSequence
                                     ActualParam
-                                       VarExpr
+                                       CallExpr
                                           ID: i
+                                          EmptyExpr
                                     EmptyActualParam
                            StmtSequence
                               AssignStmt
                                  VarExpr
                                     ID: i
-                                 BinaryExpr
-                                    VarExpr
+                                 ExprSequence
+                                    CallExpr
                                        ID: i
-                                    Operator: -
+                                       EmptyExpr
+                                    ExprSequence
                                     IntExpr
                                        IntLiteral: 1
+                                       EmptyExpr
                               EmptyStmt
                   EmptyStmt
          EmptyDecl
