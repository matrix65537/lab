#!/usr/bin/python
#coding:utf8

from algorithms.common.sortbase import Key, IntNode, SortBase
import heap

less = lambda x, y: x < y

#2k+1, 2k+2, (k-1)/2
#N指的是从0开始的最大索引值
def sink(a, k, N):
    while True:
        if 2*k + 2 > N:
            break
        j = 2 * k + 1
        #找到左右子节点中的较大者
        if j < N and less(a[j], a[j + 1]):
            j += 1
        #如果子节点比当前节点小，停止下沉
        if less(a[j], a[k]):
            break
        #当前节点与较大的子节点进行交换
        a[k], a[j] = a[j], a[k]
        k = j

def heap_sort(a):
    length = len(a)
    k = (length - 2) / 2
    #所有非叶子节点倒序开始，依次下沉，构造堆有序
    while k >= 0:
        sink(a, k, length - 1)
        k -= 1
    #取得0索引处最大和尾部交换，然后0索引下沉
    j = length - 1
    while j > 0:
        a[0], a[j] = a[j], a[0]
        j -= 1
        sink(a, 0, j)

def main():
    pass

if __name__ == '__main__':
    main()
