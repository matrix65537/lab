#!/usr/bin/python
#coding:utf8

from algorithms.common.sortbase import Key, IntNode, SortBase

class ShellSort(SortBase):

    @staticmethod
    def sort(a):
        length = len(a)
        gap = length / 2
        while gap > 0:
            for k in range(0, gap):
                for i in range(k + gap, length, gap):
                    v = a[i]
                    j = i
                    while j > 0 and v < a[j-gap]:
                        a[j] = a[j-gap]
                        j -= gap
                    a[j] = v
            gap /= 2

    @staticmethod
    def sort(a):
        length = len(a)
        gap = length / 2
        while gap > 0:
            for i in range(gap, length):
                v = a[i]
                j = i
                while j > 0 and v < a[j-gap]:
                    a[j] = a[j-gap]
                    j -= gap
                a[j] = v
            gap /= 2

    @staticmethod
    def sort(a):
        length = len(a)
        gap = 1
        while gap < length / 3:
            gap = 3 * gap + 1
        while gap >= 1:
            for i in range(gap, length):
                v = a[i]
                j = i
                while j > 0 and v < a[j-gap]:
                    a[j] = a[j-gap]
                    j -= gap
                a[j] = v
            gap /= 3

def main():
    pass

if __name__ == '__main__':
    main()
