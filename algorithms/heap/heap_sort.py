#!/usr/bin/python
#coding:utf8

import sort.lib as lib
import heap

@lib.timeit
def heap_sort(a):
    h = heap.Heap(less_func = lambda x, y: x < y)

def main():
    lib.test_sort(heap_sort)

if __name__ == '__main__':
    main()
