#!/usr/bin/python
#coding:utf8

def rank(array, key):
    low = 0
    high = len(array) - 1
    while low <= high:
        mid = low + (int)((high - low) / 2)
        if key < array[mid]:
            high = mid - 1
        elif key > array[mid]:
            low = mid + 1
        else:
            return mid
    return -1


def main():
    array = [1, 3, 5, 6, 8, 9, 10]
    key = 6
    print rank(array, key)

if __name__ == '__main__':
    main()
