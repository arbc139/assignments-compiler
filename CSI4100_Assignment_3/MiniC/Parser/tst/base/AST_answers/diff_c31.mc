--- __tmp_ans.txt	2017-11-22 21:01:17.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:17.000000000 +0900
@@ -8,12 +8,25 @@
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
                   CompoundStmt
                      DeclSequence
                         VarDecl
@@ -25,16 +38,30 @@
                         AssignStmt
                            VarExpr
                               ID: y
-                           BinaryExpr
-                              BinaryExpr
-                                 VarExpr
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          CallExpr
                                     ID: y
-                                 Operator: +
-                                 VarExpr
+                                             EmptyExpr
+                                          EmptyExpr
+                                       ExprSequence
+                                          ExprSequence
+                                             CallExpr
                                     ID: i
-                              Operator: -
+                                                EmptyExpr
+                                             EmptyExpr
+                                          ExprSequence
+                                             ExprSequence
                               IntExpr
                                  IntLiteral: 2
+                                                EmptyExpr
+                                             EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
                         EmptyStmt
                EmptyStmt
       EmptyDecl
