#!/usr/bin/python
#coding:utf8

def insert_sort(a):
    length = len(a)
    for i in range(1, length):
        v = a[i]
        j = i
        while j > 0 and v < a[j-1]:
            a[j] = a[j-1]
            j -= 1
        a[j] = v

def main():
    pass

if __name__ == '__main__':
    main()
