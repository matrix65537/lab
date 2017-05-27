#!/usr/bin/python
#coding:utf8

class Heap(object):
    '''大顶堆实现'''
    def __init__(self, less_func, values = None):
        #N记录当前有多少个节点
        self.N = 0
        self.d = [0]
        if values:
            for v in values:
                self.insert(v)
        self.less_func = less_func

    def less(self, i, j):
        return self.less_func(self.d[i], self.d[j])

    def exch(self, i, j):
        self.d[i], self.d[j] = self.d[j], self.d[i]

    def swim(self, k):
        while True:
            #到达树根
            if k <= 1:
                break
            #如果当前节点比父节点小，停止上浮
            if self.less(k, k/2):
                break
            #交换
            self.exch(k, k/2)
            k /= 2

    def sink(self, k):
        while True:
            if 2 * k > self.N:
                break
            j = 2 * k
            #找到左右子节点中的较大者
            if j < self.N and self.less(j, j + 1):
                j += 1
            #如果子节点比当前节点小，停止下沉
            if self.less(j, k):
                break
            #当前节点与较大的子节点进行交换
            self.exch(k, j)
            k = j

    def insert(self, v):
        #当空间不足时，扩容2倍
        if self.N + 1 == len(self.d):
            self.d += [0] * len(self.d)
        #把节点保存到尾部，进行上浮
        self.N += 1
        self.d[self.N] = v
        self.swim(self.N)

    def top(self):
        return self.d[1]

    def pop(self):
        top = self.d[1]
        self.exch(1, self.N)
        self.N -= 1
        self.sink(1)
        return top

    def size(self):
        return self.N

    def is_empty(self):
        return self.N == 0

def main():
    heap = Heap(less_func = lambda a, b: a < b)
    for i in range(16):
        heap.insert(i)
    while not heap.is_empty():
        m = heap.pop()
        print m,

if __name__ == '__main__':
    main()
