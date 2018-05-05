#!/usr/bin/env python
#coding:utf8

class Solution(object):
    def myPow(self, x, n):
        mflag = False
        if n < 0:
            mflag = True
            n = -n
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        t = x
        r = 1.0
        while n:
            if n & 0x01:
                r = r * t
            t = t * t
            n >>= 1
        print r
        if mflag:
            r = 1.0 / r
        return r

def main():
    s = Solution()
    r = s.myPow(34.00515, -3)
    print r

if __name__ == '__main__':
    main()
