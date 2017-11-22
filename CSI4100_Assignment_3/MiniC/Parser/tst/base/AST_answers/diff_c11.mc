--- __tmp_ans.txt	2017-11-22 21:01:14.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:14.000000000 +0900
@@ -10,19 +10,49 @@
                AssignStmt
                   VarExpr
                      ID: i
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
                            ID: k
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
                         FloatExpr
                            FloatLiteral: 3.0
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
                      EmptyStmt
       EmptyDecl
