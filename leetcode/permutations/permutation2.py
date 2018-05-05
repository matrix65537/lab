#!/usr/bin/env python
#coding:utf8

class Solution(object):

    def permuteUnique(self, nums):
        length = len(nums)
        if length == 0:
            return [[]]

        rlists = [[nums[0]]]
        for i in range(1, length):
            tlists = []
            for L in rlists:
                v = nums[i]
                for j in range(i + 1):
                    lcopy = L[::]
                    lcopy.insert(j, v)
                    tlists.append(lcopy)
                rlists = tlists
        d = {}
        for L in rlists:
            d[tuple(L)] = True
        return map(lambda x: list(x), d.keys())

def main():
    nums = [1, 1, 2]
    s = Solution()
    rlists = s.permuteUnique(nums)
    for L in rlists:
        print L

if __name__ == '__main__':
    main()
