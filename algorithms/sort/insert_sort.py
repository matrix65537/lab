#!/usr/bin/python
#coding:utf8

from algorithms.common.sortbase import Key, IntNode, SortBase

class InsertSort(SortBase):

    @staticmethod
    def sort(a):
        length = len(a)
        #假设0号元素已经有序，把后续的元素依次插入到前面已有序的序列中
        for i in range(1, length):
            j = i
            #如果当前元素比前一个元素小，则进行交换
            while j > 0 and SortBase.less(a[j], a[j-1]):
                SortBase.exch(a, j, j-1)
                j -= 1

def main():
    pass

if __name__ == '__main__':
    main()
