#!/bin/bash

#
# Note: this script must be run from the MiniC directory, otherwise the
# hardcoded paths won't work!
#
TOPDIR=`pwd`
tst=$TOPDIR/SemanticAnalysis/tst/base/testcases
sol=$TOPDIR/SemanticAnalysis/tst/base/solutions
ans=$TOPDIR/SemanticAnalysis/tst/base/answers
report=$ans/report.txt
all=0
ok=0
classpath=$TOPDIR/../build/classes/java/main
minic=$TOPDIR/../build/libs/MiniC-SemAnalysis.jar

mkdir -p $ans
echo "Parser Test Report" >$report
echo "generated "`date` >>$report
#
# Run testcases:
#
TMP_SOL=__tmp_sol.txt
TMP_ANS=__tmp_ans.txt
echo "Testing the parser..."
for file in $tst/c*.mc
do
    all=$(( $all + 1 ))
    f=`basename $file`
    java -jar $minic $file > $ans/$f.sol 
    # Uncomment to use classpath
    #java -cp $classpath MiniC.MiniC $file > $ans/$f.sol
    cat $ans/$f.sol | grep -o "ERROR: #.*:" > $TMP_SOL
    cat $sol/$f.sol | grep -o "ERROR: #.*:" > $TMP_ANS
    diff -u $TMP_ANS $TMP_SOL > $ans/diff_$f
    if [ "$?" -eq 1 ]
    then
        echo -n "-"
        echo "$f failed" >> $report
    else
        echo -n "+"
        echo "$f succeded" >> $report
        rm -rf $ans/diff_$f $ans/$f.sol
        ok=$(( $ok + 1 ))
    fi
    rm -f $TMP_SOL $TMP_ANS
done
echo
echo "Testing finished, pls. consult the test report in ${report}"
echo "$ok out of $all testcases succeeded."
