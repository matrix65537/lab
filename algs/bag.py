#!/usr/bin/python
#coding:utf8

class LinkBag(object):

    def __init__(self):
        self.__head = None
        self.__N = 0

    def add(self, v):

        class Node(object):
            def __init__(self, v, next = None):
                self.v = v
                self.next = next

        self.__head = Node(v, self.__head)
        self.__N += 1

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
    s = LinkBag()
    for i in range(10):
        s.add(i)

    for x in s:
        print x,

if __name__ == '__main__':
    main()
