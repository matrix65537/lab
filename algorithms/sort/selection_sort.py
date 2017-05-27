#!/usr/bin/python
#coding:utf8

import lib

@lib.timeit
def selection_sort(a):
    length = len(a)
    for i in range(length):
        min_index = i
        for j in range(i + 1, length):
            if a[j] < a[min_index]:
                min_index = j
        a[i], a[min_index] = a[min_index], a[i]

def main():
    lib.test_sort(selection_sort)

if __name__ == '__main__':
    main()
