--- __tmp_ans.txt	2017-11-22 21:01:19.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:19.000000000 +0900
@@ -14,14 +14,34 @@
                   VarDecl
                      BoolType
                      ID: b
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
                      BoolExpr
                         BoolLiteral: true
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
                   DeclSequence
                      VarDecl
                         FloatType
                         ID: pi
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
+                                    ExprSequence
                         FloatExpr
                            FloatLiteral: 3.1415
+                                       EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
                      EmptyDecl
             EmptyStmt
       EmptyDecl
