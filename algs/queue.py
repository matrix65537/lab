#!/usr/bin/python
#coding:utf8

from linklist import DoubleCircleList
from stack import LinkStack

class ArrayQueue(object):

    def __init__(self, max_size = 2):
        #初始化最大大小
        self.__N = 0
        self.__first = 0
        self.__last = 0
        self.__v = [None] * max_size

    def _resize(self, new_size):
        assert(new_size >= self.__N)
        v = [None] * (new_size)
        for i in range(self.__N):
            v[i] = self.__v[(i + self.__first) % self.__N]
        self.__v = v
        self.__first = 0
        self.__last = self.__N

    def enqueue(self, item):
        #当容量达到上限时，容量翻倍
        if self.__N == len(self.__v):
            self._resize(len(self.__v) * 2)
        self.__v[self.__last] = item
        self.__last += 1
        if self.__last == len(self.__v):
            self.__last = 0
        self.__N += 1


    def dequeue(self):
        if self.isEmpty():
            raise Exception, "queue is empty when pop"
        r = self.__v[self.__first]
        self.__v[self.__first] = None
        self.__first += 1
        if self.__first == len(self.__v):
            self.__first = 0
        self.__N -= 1
        #当容量小于等于最大值的1/4时，容量减半
        if self.__N > 0 and self.__N == (len(self.__v) / 4):
            self._resize(len(self.__v) / 2)
        return r

    def peek(self):
        if self.isEmpty() == 0:
            raise Exception, "stack is empty when pop"
        return self.__v[self.__first]

    def isEmpty(self):
        return self.__N == 0

    def size(self):
        return self.__N

    def iterator(self):

        class Iter(object):
            def __init__(self, v, N, first):
                self.__v = v
                self.__N = N
                self.__first = first

            def __iter__(self):
                i = 0
                while i < self.__N:
                    yield self.__v[self.__first]
                    self.__first = (self.__first + 1) % self.__N
                    i += 1


        return Iter(self.__v, self.__N, self.__first)

################################################################################

class LinkQueue(object):
    '''使用双循环链表实现队列'''
    def __init__(self):
        self.__N = 0
        self.__dlink = DoubleCircleList()

    def enqueue(self, v):
        self.__dlink.add_tail(v)
        self.__N += 1

    def dequeue(self):
        if self.isEmpty():
            raise Exception, "stack is empty when pop"
        node = self.__dlink.del_head()
        self.__N -= 1
        return node.v

    def peek(self):
        if self.isEmpty() == 0:
            raise Exception, "stack is empty when pop"
        return self.__dlink.get_head().v

    def isEmpty(self):
        return self.__N == 0

    def size(self):
        return self.__N

    def iterator(self):
        return self.__dlink.iterator()


class QueueTwoStack(object):
    '''使用双栈实现队列'''
    def __init__(self):
        self.__stack1 = LinkStack()
        self.__stack2 = LinkStack()

    def enqueue(self, v):
        self.__stack2.push(v)

    def dequeue(self):
        if self.__stack1.isEmpty():
            while not self.__stack2.isEmpty():
                self.__stack1.push(self.__stack2.pop())
        return self.__stack1.pop()

    def peek(self):
        if self.__stack1.isEmpty():
            while not self.__stack2.isEmpty():
                self.__stack1.push(self.__stack2.pop())
        return self.__stack1.peek()

    def isEmpty(self):
        return self.__stack1.isEmpty() and self.__stack2.isEmpty()

    def size(self):
        return self.__stack1.size() + self.__stack2.size()


class StackTwoQueue(object):
    '''使用双队列实现栈'''
    def __init__(self):
        self.__q1 = LinkQueue()
        self.__q2 = LinkQueue()

    def push(self, v):
        self.__q2.enqueue(v)

    def pop(self):
        while True:
            x = self.__q2.dequeue()
            if self.__q2.isEmpty():
                self.__q1, self.__q2 = self.__q2, self.__q1
                return x
            else:
                self.__q1.enqueue(x)

    def peek(self):
        while True:
            x = self.__q2.dequeue()
            self.__q1.enqueue(x)
            if self.__q2.isEmpty():
                self.__q1, self.__q2 = self.__q2, self.__q1
                return x

    def isEmpty(self):
        return self.__q2.isEmpty()

    def size(self):
        return self.__q2.size()

def main():
    q = QueueTwoStack()
    for i in range(20):
        q.enqueue(i)

    while not q.isEmpty():
        print q.dequeue(), q.size()

    s = StackTwoQueue()
    for i in range(10):
        s.push(i)

    while not s.isEmpty():
        print s.pop(),


if __name__ == '__main__':
    main()
