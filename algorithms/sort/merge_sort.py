#!/usr/bin/python
#coding:utf8

#使用辅助数组进行merge [l, mid], [mid+1, h]
def merge(a, aux, l, mid, h):
    for k in range(l, h + 1):
        aux[k] = a[k]
    i = l
    j = mid + 1
    for k in range(l, h + 1):
        if i > mid:
            a[k] = aux[j]
            j += 1
        elif j > h:
            a[k] = aux[i]
            i += 1
        elif aux[i] < aux[j]:
            a[k] = aux[i]
            i += 1
        else:
            a[k] = aux[j]
            j += 1

#插入排序，在归并排序过程中，用于小数组排序
def insert_sort(a, l, h):
    length = (h - l) + 1
    for i in range(l + 1, h + 1):
        v = a[i]
        j = i
        while j > l and v < a[j-1]:
            a[j] = a[j-1]
            j -= 1
        a[j] = v

def m_sort(a, aux, l, h):
    if l == h:
        return
    length = (h - l) + 1
    if length <= 0x0F:
        insert_sort(a, l, h)
    else:
        mid = (l + h) / 2
        m_sort(a, aux, l, mid)
        m_sort(a, aux, mid + 1, h)
        if a[mid] > a[mid+1]:
            merge(a, aux, l, mid, h)

#自顶向下的归并
def merge_sort1(a):
    aux = a[::]
    m_sort(a, aux, 0, len(a) - 1)


#自底向上的归并
def merge_sort2(a):
    length = len(a)
    aux = a[::]
    n = 1
    while n < length:
        for i in range(0, length - n, 2*n):
            merge(a, aux, i, i + n - 1, min(i + 2*n - 1, length-1))
        n *= 2

def main():
    pass

if __name__ == '__main__':
    main()
