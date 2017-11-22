--- __tmp_ans.txt	2017-11-22 21:10:00.000000000 +0900
+++ __tmp_sol.txt	2017-11-22 21:10:00.000000000 +0900
@@ -8,20 +8,20 @@
             EmptyDecl
             StmtSequence
                IfStmt
-                  BinaryExpr
-                     VarExpr
+                  ExprSequence
+                     CallExpr
                         ID: i
-                     Operator: ==
+                        EmptyExpr
                      IntExpr
                         IntLiteral: 22
                   CompoundStmt
                      EmptyDecl
                      StmtSequence
                         IfStmt
-                           BinaryExpr
-                              VarExpr
+                           ExprSequence
+                              CallExpr
                                  ID: j
-                              Operator: >
+                                 EmptyExpr
                               IntExpr
                                  IntLiteral: 1
                            AssignStmt
@@ -31,10 +31,10 @@
                                  IntLiteral: 1
                         EmptyStmt
                   IfStmt
-                     BinaryExpr
-                        VarExpr
+                     ExprSequence
+                        CallExpr
                            ID: j
-                        Operator: ==
+                           EmptyExpr
                         IntExpr
                            IntLiteral: 1
                      AssignStmt
