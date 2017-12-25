#!/bin/bash
#
# Note: this script must be run from the MiniC directory, otherwise the
# hardcoded paths won't work!
#
TOPDIR=`pwd`
tst=$TOPDIR/CodeGen/tst/base/testcases
sol=$TOPDIR/CodeGen/tst/base/solutions
ans=$TOPDIR/CodeGen/results
report=$ans/report.txt
all=0
ok=0
classpath=$TOPDIR/../build/classes/java/main:.
jasmin=$TOPDIR/../resources/jasmin_2.4/jasmin.jar
minic=$TOPDIR/../build/libs/MiniC-CodeGen.jar

rm -rf $ans
mkdir -p $ans
echo "Codge Generation Test Report" >$report
echo "generated "`date` >>$report
#
# Run testcases:
#
echo "Testing code generation..."
for file in $tst/*.mc
do
     all=$(( $all + 1 ))
     f=`basename $file .mc`
     rm -f $f.j $f.class
     java -ea -jar $minic $file > /dev/null
     if [ -f $f.j ]
     then
          # We produced $f.j, assemble to classfile and run:
          java -jar $jasmin $f.j > /dev/null
          if [ -f $f.class ]
          then
             java -ea -cp $classpath $f >$ans/res_$f
             diff -u --ignore-all-space --ignore-blank-lines $sol/${f}.txt $ans/res_$f > $ans/diff_$f
             if [ "$?" -eq 0 ]
             then
                 echo -n "+"
                 echo "$f succeded" >> $report
                 rm -rf $ans/res_$f $ans/diff_$f $f.j $f.class
                 ok=$(( $ok + 1 ))
                 continue
             fi
          else
             echo "Could not generate $f.class"
          fi
     else
          echo "Could not generate $f.j"
     fi
     echo "$f failed" >> $report
done
echo
echo "Testing finished, pls. consult the test report in $ans."
echo "$ok out of $all testcases succeeded."
