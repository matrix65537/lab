#!/usr/bin/python
#coding:utf8

class Node(object):
    def __init__(self, v):
        self.v = v
        self.next = self

class DNode(object):
    def __init__(self, v):
        self.v = v
        self.next = self
        self.prev = self

################################################################################

class List(object):
    '''单链表(仅有头节点)'''
    def __init__(self):
        node = Node(None)
        node.next = None
        self.__head = node

    def add_head(self, v):
        node = Node(v)
        node.next = self.__head.next
        self.__head.next = node

    def add_tail(self, v):
        tail = self.__head
        while tail.next:
            tail = tail.next
        node = Node(v)
        node.next = None
        tail.next = node

    def del_head(self):
        node = self.__head.next
        if self.__head.next:
            self.__head.next = self.__head.next.next
        return node

    def del_tail(self):
        if self.__head.next:
            node = self.__head
            while node.next.next:
                node = node.next
            tail = node.next
            node.next = None
            return tail

    def reverse(self):
        prev = None
        node = self.__head.next
        while node:
            next = node.next
            node.next = prev
            prev = node
            node = next
        self.__head.next = prev

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


class DoubleCircleList(object):
    '''双循环链表'''
    def __init__(self):
        self.__head = DNode(None)

    def add_head(self, v):
        node = DNode(v)
        self.insert_after(self.__head, node)

    def add_tail(self, v):
        node = DNode(v)
        self.insert_before(self.__head, node)

    def del_head(self):
        if self.__head.next != self.__head:
            return self.del_node(self.__head.next)

    def del_tail(self):
        if self.__head.next != self.__head:
            return self.del_node(self.__head.prev)

    def get_head(self):
        if self.__head.next != self.__head:
            return self.__head.next

    def get_tail(self):
        if self.__head.next != self.__head:
            return self.__head.prev

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
        return node

    def travel(self, func):
        node = self.__head.next
        while node != self.__head:
            func(node.v)
            node = node.next

    def is_empty(self):
        return self.__head.next == self.__head

    def iterator(self):

        class Iter(object):
            def __init__(self, head):
                self.__head = head

            def __iter__(self):
                node = self.__head.next
                while node != self.__head:
                    yield node.v
                    node = node.next
        return Iter(self.__head)

    def reverse_iterator(self):

        class Iter(object):
            def __init__(self, head):
                self.__head = head

            def __iter__(self):
                node = self.__head.prev
                while node != self.__head:
                    yield node.v
                    node = node.prev
                node = self.__head.next

        return Iter(self.__head)



def main():
    link = List()
    for i in range(10):
        link.add_head(i)

    link.reverse()
    link.reverse()
    it = link.iterator()
    for x in it:
        print x,
    print


if __name__ == '__main__':
    main()
