--- __tmp_ans.txt	2017-11-22 21:10:03.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:03.000000000 +0900
@@ -15,14 +15,20 @@
                   EmptyExpr
                DeclSequence
                   VarDecl
+                     ArrayType
                      IntType
+                        IntExpr
+                           IntLiteral: 10
                      ID: j
                      EmptyExpr
                   DeclSequence
                      VarDecl
                         ArrayType
+                           ArrayType
                            IntType
                            IntExpr
+                                 IntLiteral: 10
+                           IntExpr
                               IntLiteral: 3
                         ID: k
                         ExprSequence
