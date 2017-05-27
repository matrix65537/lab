#!/usr/bin/python
#coding:utf8

import algorithms.heap.heap as heap
import algorithms.test.testlib as testlib

less_func = lambda a, b : a > b

@testlib.timeit
def min_k_num1(a, k):
    r = []
    h = heap.Heap(less_func = less_func, values = a)
    while k > 0:
        r.append(h.pop())
        k -= 1
    return r

@testlib.timeit
def min_k_num2(a, k):
    r = []
    h = heap.Heap(less_func = lambda a, b : a < b, values = a[0:k])
    length = len(a)
    i = k
    while i < length:
        top = h.top()
        if a[i] < top:
            h.pop()
            h.insert(a[i])
        i += 1
    for i in range(k):
        r.append(h.pop())
    return r

def main():
    a = range(10)
    a = testlib.read_ints_from_file(r"c:\work\matrix65537\lab\trunk\algorithms\test\1000_0000.txt")
    r1 = min_k_num1(a, 8)
    print r1
    r2 = min_k_num2(a, 8)
    print r2

if __name__ == '__main__':
    main()
