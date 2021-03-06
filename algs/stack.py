#!/usr/bin/python
#coding:utf8

class ArrayStack(object):

    def __init__(self, max_size = 2):
        #初始化最大大小
        self.__N = 0
        self.__v = [None] * max_size

    def _resize(self, new_size):
        assert(new_size >= self.__N)
        v = [None] * (new_size)
        for i in range(self.__N):
            v[i] = self.__v[i]
        self.__v = v

    def push(self, item):
        #当容量达到上限时，容量翻倍
        if self.__N == len(self.__v):
            self._resize(len(self.__v) * 2)

        self.__v[self.__N] = item
        self.__N += 1

    def pop(self):
        if self.isEmpty():
            raise Exception, "stack is empty when pop"
        self.__N -= 1
        r = self.__v[self.__N]
        self.__v[self.__N] = None
        #当容量小于等于最大值的1/4时，容量减半
        if self.__N > 0 and self.__N == (len(self.__v) / 4):
            self._resize(len(self.__v) / 2)
        return r

    def peek(self):
        if self.isEmpty() == 0:
            raise Exception, "stack is empty when pop"
        return self.__v[self.__N - 1]

    def isEmpty(self):
        return self.__N == 0

    def size(self):
        return self.__N

    def iterator(self):

        class Iter(object):
            def __init__(self, v, N):
                self.__v = v
                self.__N = N

            def __iter__(self):
                while self.__N > 0:
                    self.__N -= 1
                    yield self.__v[self.__N]

        return Iter(self.__v, self.__N)


class Node(object):
    def __init__(self, v, next = None):
        self.v = v
        self.next = next

class LinkStack(object):

    def __init__(self):
        self.__head = Node(None)
        self.__N = 0

    def push(self, v):
        node = Node(v, self.__head.next)
        self.__head.next = node
        self.__N += 1

    def pop(self):
        if self.isEmpty():
            raise Exception, "stack is empty when pop"
        node = self.__head.next
        self.__head.next = node.next
        self.__N -= 1
        return node.v

    def peek(self):
        if self.isEmpty() == 0:
            raise Exception, "stack is empty when pop"
        return self.__head.next.v

    def isEmpty(self):
        return self.__N == 0

    def size(self):
        return self.__N

    def iterator(self):

        class Iter(object):
            def __init__(self, head):
                self.__head = head

            def __iter__(self):
                node = self.__head.next
                while node:
                    yield node.v
                    node = node.next

        return Iter(self.__head)

def main():
    s = ArrayStack()
    s = LinkStack()
    for i in range(10):
        s.push(i)

    for i in range(10):
        print s.pop()

if __name__ == '__main__':
    main()
