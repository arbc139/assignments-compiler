--- __tmp_ans.txt	2017-11-22 21:10:04.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:04.000000000 +0900
@@ -10,15 +10,16 @@
                AssignStmt
                   VarExpr
                      ID: a
-                  BinaryExpr
-                     ArrayExpr
-                        VarExpr
+                  ExprSequence
+                     CallExpr
                            ID: b
                         IntExpr
                            IntLiteral: 10
-                     Operator: +
-                     VarExpr
+                     ExprSequence
+                        CallExpr
                         ID: c
+                           EmptyExpr
+                        EmptyExpr
                StmtSequence
                   AssignStmt
                      ArrayExpr
@@ -26,23 +27,22 @@
                            ID: b
                         IntExpr
                            IntLiteral: 11
-                     ArrayExpr
-                        VarExpr
+                     CallExpr
                            ID: b
-                        BinaryExpr
-                           BinaryExpr
-                              BinaryExpr
-                                 VarExpr
+                        ExprSequence
+                           CallExpr
                                     ID: c
-                                 Operator: -
+                              EmptyExpr
+                           ExprSequence
                                  IntExpr
                                     IntLiteral: 1
-                              Operator: +
+                              ExprSequence
                               IntExpr
                                  IntLiteral: 21
-                           Operator: -
+                                 ExprSequence
                            CallExpr
                               ID: foo
                               EmptyActualParam
+                                    EmptyExpr
                   EmptyStmt
       EmptyDecl
