#!/bin/sh
#
# Copyright 2014 jerome pilliet <pilliet@univ-mlv.fr>
#

QUIET=
while getopts "q" option
do
  case $option in
    q) QUIET=-q;;
  esac
done
shift $((OPTIND-1))

RETVAL=0
for d in `find . -maxdepth 1 -type d -name "[0-9]*" | tail -n +2 | sort`;
do
  ./run_test.sh $QUIET $d
  RETVAL=$(($RETVAL + $?))
done
exit $RETVAL
