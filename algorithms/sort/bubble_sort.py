#!/usr/bin/python
#coding:utf8

import lib

@lib.timeit
def bubble_sort(array):
    length = len(array)
    for i in range(length):
        flag = True
        for j in range(length - i - 1):
            if array[j] > array[j+1]:
                array[j], array[j+1] = array[j+1], array[j]
                flag = False
        if flag:
            break

def main():
    lib.test_sort(bubble_sort)

if __name__ == '__main__':
    main()
