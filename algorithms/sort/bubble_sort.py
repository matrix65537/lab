#!/usr/bin/python
#coding:utf8

def bubble_sort(a):
    length = len(a)
    for i in range(length):
        flag = True
        for j in range(length - i - 1):
            if a[j] > a[j+1]:
                a[j], a[j+1] = a[j+1], a[j]
                flag = False
        if flag:
            break

def main():
    pass

if __name__ == '__main__':
    main()
