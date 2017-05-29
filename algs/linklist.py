#!/usr/bin/python
#coding:utf8

class Node(object):
    def __init__(self, v):
        self.v = v
        self.next = self
        self.prev = self

class DoubleCircleList(object):
    def __init__(self):
        self.__head = Node(None)

    def add_head(self, v):
        node = Node(v)
        self.insert_after(self.__head, node)

    def add_tail(self, v):
        node = Node(v)
        self.insert_before(self.__head, node)

    def del_head(self):
        if self.__head.next != self.__head:
            self.del_node(self.__head.next)

    def del_tail(self):
        if self.__head.next != self.__head:
            self.del_node(self.__head.prev)

    def insert_after(self, node1, node2):
        node2.next = node1.next
        node2.prev = node1
        node1.next.prev = node2
        node1.next = node2

    def insert_before(self, node1, node2):
        node2.next = node1
        node2.prev = node1.prev
        node1.prev.next = node2
        node1.prev = node2

    def del_node(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next

    def travel(self, func):
        node = self.__head.next
        while node != self.__head:
            func(node.v)
            node = node.next

    def is_empty(self):
        return self.__head.next == self.__head

    def __iter__(self):

        class Iter(object):
            def __init__(self, head):
                self.__head = head
                self.__pos = self.__head.next

            def next(self):
                if self.__pos == self.__head:
                    raise StopIteration
                else:
                    v = self.__pos.v
                    self.__pos = self.__pos.next
                    return v

        return Iter(self.__head)

    def __reversed__(self):

        class Iter(object):
            def __init__(self, head):
                self.__head = head
                self.__pos = self.__head.prev

            def next(self):
                if self.__pos == self.__head:
                    raise StopIteration
                else:
                    v = self.__pos.v
                    self.__pos = self.__pos.prev
                    return v

        return Iter(self.__head)

class LinkList(object):
    def __init__(self):
        self.__head = None

    def add_head(self, v):
        self.__head = Node(v, self.__head)

    def del_head(self):
        if self.__head == None or self.__head.next == None:
            self.__head = None


def list_dump(node):
    while node:
        print node.v, "->",
        node = node.next
    print


def list_reverse_recursion(head):
    if head == None or head.next == None:
        return head
    new_head = list_reverse(head.next)
    head.next.next = head
    head.next = None
    return new_head


def list_reverse(head):
    prev = None
    while head != None:
        next = head.next
        head.next = prev
        prev = head
        head = next
    return prev

def new_list(L):
    node = None
    for x in L:
        node = Node(x, node)
    return node

def func(v):
    print v,

def main():
    dlink = DoubleCircleList()
    for i in range(10):
        dlink.add_tail(i)

    for x in reversed(dlink):
        print x,

if __name__ == '__main__':
    main()
