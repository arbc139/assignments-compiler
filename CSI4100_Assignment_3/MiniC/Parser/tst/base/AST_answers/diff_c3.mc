--- __tmp_ans.txt	2017-11-22 21:01:17.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:01:17.000000000 +0900
@@ -8,12 +8,32 @@
          VarDecl
             BoolType
             ID: b
+            ExprSequence
+               ExprSequence
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
             BoolExpr
                BoolLiteral: true
+                           EmptyExpr
+                        EmptyExpr
+                     EmptyExpr
+                  EmptyExpr
+               EmptyExpr
          DeclSequence
             VarDecl
                FloatType
                ID: pi
+               ExprSequence
+                  ExprSequence
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
                FloatExpr
                   FloatLiteral: 3.1415
+                              EmptyExpr
+                           EmptyExpr
+                        EmptyExpr
+                     EmptyExpr
+                  EmptyExpr
             EmptyDecl
