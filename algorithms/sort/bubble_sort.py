#!/usr/bin/python
#coding:utf8

import lib

@lib.timeit
def bubble_sort(a):
    length = len(a)
    for i in range(length):
        flag = True
        for j in range(length - i - 1):
            if a[j] > a[j+1]:
                a[j], a[j+1] = a[j+1], a[j]
                flag = False
        if flag:
            break

def main():
    lib.test_sort(bubble_sort)

if __name__ == '__main__':
    main()
