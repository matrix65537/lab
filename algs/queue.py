#!/usr/bin/python
#coding:utf8

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

    def __iter__(self):

        class Iter(object):
            def __init__(self, v, N, first):
                self.__v = v
                self.__N = N
                self.__i = 0
                self.__first = first

            def next(self):
                if self.__i == self.__N:
                    raise StopIteration
                else:
                    r = self.__v[self.__first]
                    self.__i += 1
                    self.__first = (self.__first + 1) % self.__N
                    return r


        return Iter(self.__v, self.__N, self.__first)

################################################################################

class LinkQueue(object):

    def __init__(self):
        self.__head = None
        self.__tail = None
        self.__N = 0

    def enqueue(self, v):

        class Node(object):
            def __init__(self, v, next = None):
                self.v = v
                self.next = next

        node = Node(v, None)
        #只有一个节点时（既是尾节点也是头节点）
        if not self.__tail:
            self.__head = node
        #添加到尾部
        else:
            self.__tail.next = node
        self.__tail = node
        self.__N += 1

    def dequeue(self):
        if self.isEmpty():
            raise Exception, "stack is empty when pop"
        v = self.__head.v
        self.__head = self.__head.next
        #头节点为空时，尾节点也为空
        if not self.__head:
            self.__tail = None
        self.__N -= 1
        return v

    def peek(self):
        if self.isEmpty() == 0:
            raise Exception, "stack is empty when pop"
        return self.__head.v

    def isEmpty(self):
        return self.__N == 0

    def size(self):
        return self.__N

    def __iter__(self):

        class Iter(object):
            def __init__(self, head):
                self.__head = head

            def next(self):
                if not self.__head:
                    raise StopIteration
                else:
                    v = self.__head.v
                    self.__head = self.__head.next
                    return v

        return Iter(self.__head)


def main():
    s = LinkQueue()
    for i in range(10):
        s.enqueue(i)

    for x in s:
        print x,
    print

    while not s.isEmpty():
        print s.dequeue()

if __name__ == '__main__':
    main()
