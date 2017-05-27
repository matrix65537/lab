#!/usr/bin/python
#coding:utf8

import lib
import random

#挖坑填数
def partition(a, low, high):
    x = a[low]
    i, j = low, high
    while i < j:
        while i < j and a[j] > x:
            j -= 1
        if i < j:
            a[i] = a[j]
            i += 1

        while i < j and a[i] < x:
            i += 1
        if i < j:
            a[j] = a[i]
            j -= 1
    a[i] = x
    return i


#双指针移动
def partition(a, low, high):
    x = a[low]
    i, j = low, high + 1
    while True:
        while True:
            i += 1
            if a[i] >= x or i == high:
                break
        while True:
            j -= 1
            if a[j] <= x or j == low:
                break
        if i >= j:
            break
        else:
            a[i], a[j] = a[j], a[i]
    a[low], a[j] = a[j], a[low]
    return j


def qsort(a, low, high):
    if low >= high:
        return
    j = partition(a, low, high)
    qsort(a, low, j - 1)
    qsort(a, j + 1, high)

@lib.timeit
def quick_sort(a):
    length = len(a)
    qsort(a, 0, length - 1)


def main():
    lib.test_sort(quick_sort)

if __name__ == '__main__':
    main()
