#!/usr/bin/python
#coding:utf8

import lib

#使用辅助数组进行merge [a, b], [b+1, c]
def merge(array, aux, a, b, c):
    for k in range(a, c + 1):
        aux[k] = array[k]
    i = a
    j = b + 1
    for k in range(a, c + 1):
        if i > b:
            array[k] = aux[j]
            j += 1
        elif j > c:
            array[k] = aux[i]
            i += 1
        elif aux[i] < aux[j]:
            array[k] = aux[i]
            i += 1
        else:
            array[k] = aux[j]
            j += 1

#插入排序，在归并排序过程中，用于小数组排序
def insert_sort(array, a, b):
    length = (b - a) + 1
    for i in range(a + 1, b + 1):
        v = array[i]
        j = i
        while j > a and v < array[j-1]:
            array[j] = array[j-1]
            j -= 1
        array[j] = v

def m_sort(array, aux, a, c):
    if a == c:
        return
    length = (c - a) + 1
    if length <= 0x0F:
        insert_sort(array, a, c)
    else:
        mid = (a + c) / 2
        m_sort(array, aux, a, mid)
        m_sort(array, aux, mid + 1, c)
        if array[mid] > array[mid+1]:
            merge(array, aux, a, mid, c)

#自顶向下的归并
@lib.timeit
def merge_sort1(array):
    aux = array[::]
    m_sort(array, aux, 0, len(array) - 1)

#自底向上的归并
@lib.timeit
def merge_sort2(array):
    length = len(array)
    aux = array[::]
    n = 1
    while n < length:
        for i in range(0, length - n, 2*n):
            merge(array, aux, i, i + n - 1, min(i + 2*n - 1, length-1))
        n *= 2

def main():
    lib.test_sort(merge_sort1)
    lib.test_sort(merge_sort2)

if __name__ == '__main__':
    main()
