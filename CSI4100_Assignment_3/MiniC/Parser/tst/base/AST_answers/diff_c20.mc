--- __tmp_ans.txt	2017-11-22 21:01:15.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:15.000000000 +0900
@@ -10,6 +10,11 @@
                AssignStmt
                   VarExpr
                      ID: b
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
                   UnaryExpr
                      Operator: +
                      UnaryExpr
@@ -22,5 +27,10 @@
                                  Operator: +
                                  FloatExpr
                                     FloatLiteral: 1.2
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
+                     EmptyExpr
                EmptyStmt
       EmptyDecl
