--- __tmp_ans.txt	2017-11-22 21:01:18.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:18.000000000 +0900
@@ -9,24 +9,60 @@
          EmptyExpr
       DeclSequence
          VarDecl
+            ArrayType
             IntType
+               IntExpr
+                  IntLiteral: 10
             ID: j
             EmptyExpr
          DeclSequence
             VarDecl
                ArrayType
+                  ArrayType
                   IntType
                   IntExpr
+                        IntLiteral: 10
+                  IntExpr
                      IntLiteral: 3
                ID: k
                ExprSequence
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
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
                   ExprSequence
                      IntExpr
                         IntLiteral: 2
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              ExprSequence
+                                 ExprSequence
                      ExprSequence
                         IntExpr
                            IntLiteral: 3
                         EmptyExpr
+                                    EmptyExpr
+                                 EmptyExpr
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
             EmptyDecl
