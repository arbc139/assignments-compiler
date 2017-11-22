--- __tmp_ans.txt	2017-11-22 21:10:04.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:04.000000000 +0900
@@ -8,46 +8,52 @@
             EmptyDecl
             StmtSequence
                IfStmt
-                  BinaryExpr
-                     BinaryExpr
-                        VarExpr
+                  ExprSequence
+                     ExprSequence
+                        CallExpr
                            ID: a
-                        Operator: ==
+                           EmptyExpr
                         IntExpr
                            IntLiteral: 1
-                     Operator: ||
-                     BinaryExpr
-                        BinaryExpr
-                           VarExpr
+                     ExprSequence
+                        ExprSequence
+                           ExprSequence
+                              CallExpr
                               ID: b
-                           Operator: ==
+                                 EmptyExpr
                            IntExpr
                               IntLiteral: 2
-                        Operator: &&
+                           ExprSequence
                         UnaryExpr
                            Operator: !
-                           BinaryExpr
-                              VarExpr
+                                 ExprSequence
+                                    CallExpr
                                  ID: a
-                              Operator: ==
+                                       EmptyExpr
                               IntExpr
                                  IntLiteral: 22
+                              EmptyExpr
+                        EmptyExpr
                   AssignStmt
                      VarExpr
                         ID: a
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        CallExpr
                            ID: a
-                        Operator: +
+                           EmptyExpr
+                        ExprSequence
                         IntExpr
                            IntLiteral: 1
+                           EmptyExpr
                   ReturnStmt
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        CallExpr
                            ID: b
-                        Operator: +
+                           EmptyExpr
+                        ExprSequence
                         IntExpr
                            IntLiteral: 2
+                           EmptyExpr
                StmtSequence
                   ReturnStmt
                      EmptyExpr
