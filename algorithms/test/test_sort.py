#!/usr/bin/python
#coding:utf8

import algorithms.sort.bubble_sort as bubble_sort
import algorithms.sort.selection_sort as selection_sort
import algorithms.sort.insert_sort as insert_sort
import algorithms.sort.shell_sort as shell_sort
import algorithms.sort.merge_sort as merge_sort
import algorithms.sort.quick_sort as quick_sort
import algorithms.heap.heap_sort as heap_sort

import testlib

def main():
    #testlib.test_sort(bubble_sort.BubbleSort, "bubble sort")
    #testlib.test_sort(selection_sort.SelectionSort, "selection sort")
    #testlib.test_sort(insert_sort.InsertSort, "insert sort")
    #testlib.test_sort(shell_sort.ShellSort, "shell sort")
    testlib.test_sort(merge_sort.MergeSort, "merge sort")
    testlib.test_sort(merge_sort.MergeSortBU, "merge sort BU")
    testlib.test_sort(quick_sort.QuickSort, "quick sort")

if __name__ == '__main__':
    main()
