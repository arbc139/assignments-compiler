--- __tmp_ans.txt	2017-11-22 21:01:17.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:17.000000000 +0900
@@ -8,20 +8,45 @@
             EmptyDecl
             StmtSequence
                WhileStmt
-                  BinaryExpr
-                     VarExpr
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 CallExpr
                         ID: a
-                     Operator: >=
-                     VarExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           ExprSequence
+                              ExprSequence
+                                 CallExpr
                         ID: b
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                        EmptyExpr
+                     EmptyExpr
                   AssignStmt
                      VarExpr
                         ID: y
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    CallExpr
                            ID: y
-                        Operator: +
+                                       EmptyExpr
+                                    EmptyExpr
+                                 ExprSequence
+                                    ExprSequence
                         IntExpr
                            IntLiteral: 3
+                                       EmptyExpr
+                                    EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                EmptyStmt
       EmptyDecl
