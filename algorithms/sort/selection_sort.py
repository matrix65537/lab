#!/usr/bin/python
#coding:utf8

from algorithms.common.sortbase import Key, IntNode, SortBase

class SelectionSort(SortBase):

    @staticmethod
    def sort(a):
        length = len(a)
        for i in range(length):
            #将首号元素标记位最小
            min_index = i
            #从下一个元素开始遍历记录最小的索引
            for j in range(i + 1, length):
                if SortBase.less(a[j], a[min_index]):
                    min_index = j
            #交换当前元素和最小元素
            SortBase.exch(a, i, min_index)

def main():
    pass

if __name__ == '__main__':
    main()
