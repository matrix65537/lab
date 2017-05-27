#!/usr/bin/python
#coding:utf8

from algorithms.common.sortbase import Key, IntNode, SortBase

class MergeSort(SortBase):

    #使用辅助数组进行merge [l, mid], [mid+1, h]
    @staticmethod
    def merge(a, aux, l, mid, h):
        for k in range(l, h + 1):
            aux[k] = a[k]
        i, j = l, mid + 1
        for k in range(l, h + 1):
            if i > mid:
                a[k] = aux[j]
                j += 1
            elif j > h:
                a[k] = aux[i]
                i += 1
            elif SortBase.less(aux[i], aux[j]):
                a[k] = aux[i]
                i += 1
            else:
                a[k] = aux[j]
                j += 1

    @staticmethod
    def merge_sort(a, aux, l, h):
        if l == h:
            return
        mid = (l + h) / 2
        MergeSort.merge_sort(a, aux, l, mid)
        MergeSort.merge_sort(a, aux, mid + 1, h)
        MergeSort.merge(a, aux, l, mid, h)

    @staticmethod
    def sort(a):
        aux = a[::]
        MergeSort.merge_sort(a, aux, 0, len(a) - 1)


class MergeSortBU(MergeSort):

    #自底向上的归并
    @staticmethod
    def sort(a):
        length = len(a)
        aux = a[::]
        n = 1
        while n < length:
            for i in range(0, length - n, 2*n):
                MergeSortBU.merge(a, aux, i, i + n - 1, min(i + 2*n - 1, length-1))
            n *= 2

def main():
    pass

if __name__ == '__main__':
    main()
