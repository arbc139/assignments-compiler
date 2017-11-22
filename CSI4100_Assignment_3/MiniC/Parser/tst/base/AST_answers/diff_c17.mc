--- __tmp_ans.txt	2017-11-22 21:01:15.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:15.000000000 +0900
@@ -12,16 +12,47 @@
                      ID: foo
                      ActualParamSequence
                         ActualParam
-                           VarExpr
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          CallExpr
                               ID: a
+                                             EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
                         ActualParamSequence
                            ActualParam
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
                               IntExpr
                                  IntLiteral: 2
+                                             EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
                            ActualParamSequence
                               ActualParam
+                                 ExprSequence
+                                    ExprSequence
+                                       ExprSequence
+                                          ExprSequence
+                                             ExprSequence
                                  FloatExpr
                                     FloatLiteral: 2.0
+                                                EmptyExpr
+                                             EmptyExpr
+                                          EmptyExpr
+                                       EmptyExpr
+                                    EmptyExpr
                               EmptyActualParam
                EmptyStmt
       EmptyDecl
