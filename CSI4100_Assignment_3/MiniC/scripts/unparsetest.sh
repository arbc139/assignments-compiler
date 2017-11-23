#!/bin/bash

#
# Note: this script must be run from the MiniC directory, otherwise the
# hardcoded paths won't work!
#
TOPDIR=`pwd`
tst=$TOPDIR/Parser/tst/base/AST_testcases
sol=$TOPDIR/Parser/tst/base/AST_solutions_unparsed
ans=$TOPDIR/Parser/tst/base/AST_answers
report=$ans/report.txt
all=0
ok=0
classpath=$TOPDIR/../build/classes/main
minic=$TOPDIR/../build/libs/MiniC-AstGen.jar

mkdir -p $ans
echo "Parser Test Report" > $report
echo "generated "`date` >> $report
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
    unparse_f=$f".u"
    java -jar $minic -u $ans/ans_$unparse_f $file > $ans/ans_log_$unparse_f
    compile_result=$(tail -n1 < "$ans"/ans_log_"$unparse_f")
    if [ "$compile_result" != "Compilation was successful." ]
    then
        echo -n "-"
        echo "$f failed" >> $report
        continue
    fi
    # Alternative invocation via classpath:
    # java -cp $classpath MiniC.MiniC $file > $ans/s_$f
    cat $ans/ans_$unparse_f > $TMP_SOL
    cat $sol/$unparse_f > $TMP_ANS
    diff -u --ignore-all-space --ignore-blank-lines $TMP_ANS $TMP_SOL > $ans/diff_$f
    if [ "$?" -eq 1 ]
    then
        echo -n "-"
        echo "$f failed" >> $report
    else
        echo -n "+"
        echo "$f succeded" >> $report
        rm -rf $ans/diff_$f $ans/ans_$unparse_f $ans/ans_log_$unparse_f
        ok=$(( $ok + 1 ))
    fi
    rm -f $TMP_SOL $TMP_ANS
done
echo
echo "Testing finished, pls. consult the test report in ${report}"
echo "$ok out of $all testcases succeeded."
