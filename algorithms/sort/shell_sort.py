#!/usr/bin/python
#coding:utf8

import lib

def sort1(array):
    length = len(array)
    gap = length / 2
    while gap > 0:
        for k in range(0, gap):
            for i in range(k + gap, length, gap):
                v = array[i]
                j = i
                while j > 0 and v < array[j-gap]:
                    array[j] = array[j-gap]
                    j -= gap
                array[j] = v
        gap /= 2

@lib.timeit
def sort2(array):
    length = len(array)
    gap = length / 2
    while gap > 0:
        for i in range(gap, length):
            v = array[i]
            j = i
            while j > 0 and v < array[j-gap]:
                array[j] = array[j-gap]
                j -= gap
            array[j] = v
        gap /= 2

def main():
    lib.test_sort(sort2)

if __name__ == '__main__':
    main()
