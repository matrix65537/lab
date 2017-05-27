#!/usr/bin/python
#coding:utf8

import random
import time


def timeit(func):
    def wrap(*args, **kwargs):
        start = time.clock()
        #print "begin func"
        func(*args, **kwargs)
        #print "end func"
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

def is_sorted(a, reverse = False):
    length = len(a)
    if length <= 1:
        return True
    for i in range(length - 1):
        if reverse:
            if a[i] < a[i+1]:
                return False
        else:
            if a[i] > a[i+1]:
                return False
    return True

def gen_ints_file(filename, a, b, count):
    print filename
    ints = random_ints(a, b, count)
    write_ints_to_file(filename, ints)


@timeit
def test_sort(sort_func, name = ""):
    filename = "10_0000.txt"
    a = read_ints_from_file(filename)
    print name
    sort_func(a)
    assert(is_sorted(a) == True)

def main():
    gen_ints_file("1_0000.txt", 1, 10000000, 1 * 10000)
    gen_ints_file("10_0000.txt", 1, 10000000, 10 * 10000)
    gen_ints_file("100_0000.txt", 1, 10000000, 100 * 10000)
    gen_ints_file("1000_0000.txt", 1, 10000000, 1000 * 10000)

if __name__ == '__main__':
    main()
