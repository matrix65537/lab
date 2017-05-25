#!/usr/bin/python
#coding:utf8

import lib

@lib.timeit
def insert_sort(array):
    length = len(array)
    for i in range(1, length):
        v = array[i]
        j = i
        while j > 0 and v < array[j-1]:
            array[j] = array[j-1]
            j -= 1
        array[j] = v


def main():
    lib.test_sort(insert_sort)

if __name__ == '__main__':
    main()
