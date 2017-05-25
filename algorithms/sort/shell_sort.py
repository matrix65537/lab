#!/usr/bin/python
#coding:utf8

import lib

@lib.timeit
def shell_sort1(array):
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
def shell_sort2(array):
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

@lib.timeit
def shell_sort3(array):
    length = len(array)
    gap = 1
    while gap < length / 3:
        gap = 3 * gap + 1
    while gap >= 1:
        for i in range(gap, length):
            v = array[i]
            j = i
            while j > 0 and v < array[j-gap]:
                array[j] = array[j-gap]
                j -= gap
            array[j] = v
        gap /= 3

def main():
    lib.test_sort(shell_sort1)
    lib.test_sort(shell_sort2)
    lib.test_sort(shell_sort3)

if __name__ == '__main__':
    main()
