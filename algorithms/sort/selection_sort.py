#!/usr/bin/python
#coding:utf8

import lib

@lib.timeit
def selection_sort(array):
    length = len(array)
    for i in range(length):
        min_index = i
        for j in range(i + 1, length):
            if array[j] < array[min_index]:
                min_index = j
        array[i], array[min_index] = array[min_index], array[i]

def main():
    lib.test_sort(selection_sort)

if __name__ == '__main__':
    main()
