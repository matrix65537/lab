#!/usr/bin/python
#coding:utf8

from algorithms.common.sortbase import Key, IntNode, SortBase

class QuickSort(SortBase):

    #挖坑填数
    @staticmethod
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
    @staticmethod
    def partition2(a, low, high):
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

    @staticmethod
    def quick_sort(a, low, high):
        if low >= high:
            return
        j = QuickSort.partition(a, low, high)
        QuickSort.quick_sort(a, low, j - 1)
        QuickSort.quick_sort(a, j + 1, high)

    @staticmethod
    def sort(a):
        length = len(a)
        QuickSort.quick_sort(a, 0, length - 1)

def main():
    pass

if __name__ == '__main__':
    main()
