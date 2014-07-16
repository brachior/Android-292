#!/bin/sh
#
# Copyright 2014 jerome pilliet <pilliet@univ-mlv.fr>
#

#if [ "`java -version 2>&1 | head -1 | grep \"java.*1.7\"`" == "" ]; then
#  echo "JAVA_HOME not set to a JDK 7 home" 1>&2
#fi

ROOT=$PWD
cd $1/src
mkdir ../out
if [ -d "../lib" ]; then
  cp -R ../lib/* ../out/
  lib="-cp .:../lib"
else
  lib=
fi
javac $lib -d ../out `find . -type f -name "*.java"`
cd ..
jar cf Test.jar -C out/ .
echo "`java -cp Test.jar Main`" > expected.txt 2>&1
rm -Rf out/ Test.jar
cd $ROOT
