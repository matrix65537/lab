#!/usr/bin/python
#coding:utf8

class Node(object):
    def __init__(self, v, next = None):
        self.v = v
        self.next = next

class LinkBag(object):

    def __init__(self):
        self.__head = Node(None)
        self.__N = 0

    def add(self, v):
        node = Node(v, self.__head.next)
        self.__head.next = node
        self.__N += 1

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
    s = LinkBag()
    for i in range(10):
        s.add(i)

    for x in s.iterator():
        print x,

if __name__ == '__main__':
    main()
