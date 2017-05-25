#!/usr/bin/python
#coding:utf8

import random
import time


def timeit(func):
    def wrap(*args, **kwargs):
        start = time.clock()
        print "begin func"
        func(*args, **kwargs)
        print "end func"
        end = time.clock()
        print "used:", end-start
    return wrap


def read_ints_from_file(filename):
    with open(filename, "rb") as fobj:
        all_values = []
        for line in fobj:
            line = line.strip()
            if len(line) == 0:
                continue
            if line.startswith("#"):
                continue
            values = line.split()
            values = [int(x) for x in values]
            all_values += values
        return all_values

def random_ints(a, b, count):
    all_ints = []
    for i in range(count):
        v = random.randint(a, b)
        all_ints.append(v)
    return all_ints

def write_ints_to_file(filename, ints, col = 0x10):
    with open(filename, "wb") as fobj:
        for i in range(len(ints)):
            v = ints[i]
            fobj.write("% 8d" %v)
            if i % col == (col - 1):
                fobj.write("\n")

def is_sorted(array, reverse = False):
    length = len(array)
    if length <= 1:
        return True
    for i in range(length - 1):
        if reverse:
            if array[i] < array[i+1]:
                return False
        else:
            if array[i] > array[i+1]:
                return False
    return True

def test_sort(sort_func):
    filename = "1_0000.txt"
    array = read_ints_from_file(filename)
    sort_func(array)
    assert(is_sorted(array) == True)

def main():
    ints = random_ints(1, 10000000, 10000)
    write_ints_to_file("1_0000.txt", ints)

if __name__ == '__main__':
    main()
