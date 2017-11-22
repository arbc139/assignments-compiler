--- __tmp_ans.txt	2017-11-22 21:01:15.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:15.000000000 +0900
@@ -10,23 +10,53 @@
                AssignStmt
                   VarExpr
                      ID: a
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
                   IntExpr
                      IntLiteral: 1
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
+                     EmptyExpr
                StmtSequence
                   AssignStmt
                      VarExpr
                         ID: b
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
                      BoolExpr
                         BoolLiteral: false
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                   StmtSequence
                      AssignStmt
                         VarExpr
                            ID: c
-                        BinaryExpr
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
                            IntExpr
                               IntLiteral: 2
-                           Operator: *
-                           VarExpr
+                                       ExprSequence
+                                          CallExpr
                               ID: a
+                                             EmptyExpr
+                                          EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
                      EmptyStmt
       EmptyDecl
