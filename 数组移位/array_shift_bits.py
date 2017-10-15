#!/usr/bin/env python
#coding:utf8

from scplib import *

'''
数组移位
'''

mask1 = [0xFF, 0xFE, 0xFC, 0xF8, 0xF0, 0xE0, 0xC0, 0x80]
mask2 = [0xFF, 0x7F, 0x3F, 0x1F, 0x0F, 0x07, 0x03, 0x01]

def array_shift_bits_left(array, k):
    length = len(array)
    a = k / 8
    k = k % 8
    if a > 0:
        array = array[a:] + array[:a]
    assert(k < 8 and k >= 0)
    cells = []
    v0 = ord(array[0])
    for i in range(length - 1):
        v1 = ord(array[i])
        v2 = ord(array[i + 1])
        v = ((v1 << k) & 0xFF) | (v2 >> (8 - k))
        cells.append(chr(v))
    v1 = ord(array[length - 1])
    v2 = v0
    v = ((v1 << k) & 0xFF) | (v2 >> (8 - k))
    cells.append(chr(v))
    return ''.join(cells)

def array_shift_bits_right(array, k):
    length = len(array)
    a = k / 8
    k = k % 8
    if a > 0:
        array = array[-a:] + array[:-a]
    assert(k < 8 and k >= 0)
    cells = []
    v0 = ord(array[length - 1])
    for i in range(length - 1, 0, -1):
        v1 = ord(array[i])
        v2 = ord(array[i - 1])
        v = (v1 >> k) | ((v2 << (8 - k)) & 0xFF)
        cells.append(chr(v))
    v1 = ord(array[0])
    v2 = v0
    v = (v1 >> k) | ((v2 << (8 - k)) & 0xFF)
    cells.append(chr(v))
    cells.reverse()
    return ''.join(cells)

def main():
    data = b''.join([chr(x) for x in range(0x100)])
    data = '\xAB\xCD\xEF'
    data = b("00 00 01")
    print ds(data)

    for k in range(len(data) * 8 + 1):
        data = array_shift_bits_left(data, k)
        print ds(data)
        data = array_shift_bits_right(data, k)
        print ds(data)
        print

if __name__ == '__main__':
    main()

