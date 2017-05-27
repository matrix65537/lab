#!/usr/bin/python
#coding:utf8

class Key(object):
    def __init__(self):
        pass

    def __lt__(self, key):
        raise NotImplementedError, "< method must be implemented"


class IntNode(Key):
    def __init__(self, v):
        super(Key, self).__init__()
        self.__v = v

    def __lt__(self, node):
        return self.__v < node.__v

    def __str__(self):
        return str(self.__v)


class SortBase(object):
    def __init__(self):
        pass

    @staticmethod
    def exch(keys, i, j):
        keys[i], keys[j] = keys[j], keys[i]

    @staticmethod
    def less(keya, keyb):
        if not (isinstance(keya, Key) and isinstance(keyb, Key)):
            raise TypeError, "must be Key type for cmp"
        return keya < keyb

    @staticmethod
    def show(keys):
        for x in keys:
            print x,

    @staticmethod
    def is_sorted(keys):
        for i in range(1, len(keys)):
            if SortBase.less(keys[i], keys[i-1]):
                return False
        return True

    def sort(self, a):
        raise NotImplementedError, "sort method not implemented"


def main():
    pass

if __name__ == '__main__':
    main()
