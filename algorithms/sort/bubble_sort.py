#!/usr/bin/python
#coding:utf8

from algorithms.common.sortbase import Key, IntNode, SortBase

class BubbleSort(SortBase):

    @staticmethod
    def sort(a):
        length = len(a)
        for i in range(length):
            flag = True
            for j in range(length - i - 1):
                if SortBase.less(a[j+1], a[j]):
                    SortBase.exch(a, j, j+1)
                    flag = False
            if flag:
                break

def main():
    pass

if __name__ == '__main__':
    main()
