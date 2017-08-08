#!/usr/bin/env python
#coding:utf8

import sys

fmt = '''
<dependency>
        <groupId>%s</groupId>
        <artifactId>%s</artifactId>
        <version>%s</version>
</dependency>'''

def main():
    with open(sys.argv[1]) as fobj:
        lines = fobj.readlines()
        for line in lines:
            line = line.strip()
            line = line.replace(",", "")
            line = line.replace("'", "")
            L = line.split(":")
            info = fmt %(L[0], L[1], L[2])
            print info

if __name__ == '__main__':
    main()
