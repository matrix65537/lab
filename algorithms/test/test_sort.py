#!/usr/bin/python
#coding:utf8

import algorithms.sort.bubble_sort as bubble_sort
import algorithms.sort.selection_sort as selection_sort
import algorithms.sort.insert_sort as insert_sort
import algorithms.sort.shell_sort as shell_sort
import algorithms.sort.merge_sort as merge_sort
import algorithms.sort.quick_sort as quick_sort

import testlib

def main():
    testlib.test_sort(bubble_sort.bubble_sort, "bubble sort")
    testlib.test_sort(selection_sort.selection_sort, "selection sort")
    testlib.test_sort(insert_sort.insert_sort, "insert  sort")
    testlib.test_sort(shell_sort.shell_sort1, "shell sort1")
    testlib.test_sort(shell_sort.shell_sort2, "shell sort2")
    testlib.test_sort(shell_sort.shell_sort3, "shell sort3")
    testlib.test_sort(merge_sort.merge_sort1, "merge sort1")
    testlib.test_sort(merge_sort.merge_sort2, "merge sort2")
    testlib.test_sort(quick_sort.quick_sort, "quick sort")

if __name__ == '__main__':
    main()
