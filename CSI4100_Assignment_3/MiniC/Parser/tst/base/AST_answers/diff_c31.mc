--- __tmp_ans.txt	2017-11-22 21:10:01.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:01.000000000 +0900
@@ -8,12 +8,13 @@
             EmptyDecl
             StmtSequence
                WhileStmt
-                  BinaryExpr
-                     VarExpr
+                  ExprSequence
+                     CallExpr
                         ID: a
-                     Operator: >=
-                     VarExpr
+                        EmptyExpr
+                     CallExpr
                         ID: b
+                        EmptyExpr
                   CompoundStmt
                      DeclSequence
                         VarDecl
@@ -25,16 +26,18 @@
                         AssignStmt
                            VarExpr
                               ID: y
-                           BinaryExpr
-                              BinaryExpr
-                                 VarExpr
+                           ExprSequence
+                              CallExpr
                                     ID: y
-                                 Operator: +
-                                 VarExpr
+                                 EmptyExpr
+                              ExprSequence
+                                 CallExpr
                                     ID: i
-                              Operator: -
+                                    EmptyExpr
+                                 ExprSequence
                               IntExpr
                                  IntLiteral: 2
+                                    EmptyExpr
                         EmptyStmt
                EmptyStmt
       EmptyDecl
