#!/bin/bash

#
# Note: this script must be run from the MiniC directory, otherwise the
# hardcoded paths won't work!
#
TOPDIR=`pwd`
tst=$TOPDIR/Scanner/tst/base/testcases
sol=$TOPDIR/Scanner/tst/base/solutions
ans=$TOPDIR/Scanner/tst/base/answer
classpath=$TOPDIR/../build/classes/main
minic=$TOPDIR/../build/libs/MiniC-Scanner.jar

mkdir -p $ans
for file in $tst/c*.txt
do
     f=`basename $file`
     echo -n "."
     java -jar $minic $file > $ans/s_$f
     # Alternative invocation via classpath:
     # java -cp $classpath MiniC.MiniC $file > $ans/s_$f
     diff -u --ignore-all-space --ignore-blank-lines $ans/s_$f $sol/s_$f > /dev/null
     if [ "$?" -ne 0 ]
     then
		 echo
		 echo -e "\nfile $f:"
		 echo -e "test failed\n"
		 exit
     fi
done

echo -e "\nTest complete. All test-cases succeeded."
