#!/bin/sh
#
# Copyright 2014 jerome pilliet <pilliet@univ-mlv.fr>
#

while getopts "q" option
do
  case $option in
    q) QUIET=1;;
  esac
done
shift $((OPTIND-1))

ADB=$PWD/../out/host/linux-x86/bin/adb
DX=$PWD/../out/host/linux-x86/bin/dx

ROOT=$PWD
cd $1/src
mkdir ../out
if [ -d "../lib" ]; then
  dir="out/ lib/"; lib="-cp .:../lib"
else
  dir="out/"; lib=
fi
javac $lib -d ../out `find . -type f -name "*.java"`
cd ..
$DX --dex --output=Test.jar $dir
nohup $ADB push Test.jar /sdcard/ > /dev/null 2>&1
echo "`$ADB shell dalvikvm -Xint:portable -cp /sdcard/Test.jar Main`" > out.txt 2>&1
tr -d '\015' < out.txt > output.txt
if [ -z "$QUIET" ]; then
  diff -u expected.txt output.txt
else
  diff -uq expected.txt output.txt > /dev/null 2>&1
fi
RET=$?
if [ "$RET" != "0" ]; then
  echo "`basename $1`: error" 1>&2
else
  echo "`basename $1`: ok" 1>&2
fi
$ADB shell rm /sdcard/Test.jar
rm -Rf out/ Test.jar output.txt out.txt
cd $ROOT
exit $RET
