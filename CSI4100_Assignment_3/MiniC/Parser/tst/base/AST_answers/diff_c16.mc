--- __tmp_ans.txt	2017-11-22 21:01:14.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:14.000000000 +0900
@@ -12,8 +12,19 @@
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
                         EmptyActualParam
                EmptyStmt
       EmptyDecl
