#!/bin/bash

#
# Note: this script must be run from the MiniC directory, otherwise the
# hardcoded paths won't work!
#
TOPDIR=`pwd`
tst=$TOPDIR/Parser/tst/base/AST_testcases
all=0
ok=0
classpath=$TOPDIR/../build/classes/main
minic=$TOPDIR/../build/libs/MiniC-AstGen.jar

echo "Parser Test Report"
echo "generated "`date`
#
# Run testcases:
#
echo "Testing the parser..."
for file in $tst/c*.mc
do
     all=$(( $all + 1 ))
     f=`basename $file`
     echo $file
     java -jar $minic $file
     # Alternative invocation via classpath:
     # java -cp $classpath MiniC.MiniC $file > $ans/s_$f
done
echo
