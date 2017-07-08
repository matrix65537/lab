#!/usr/bin/python
#coding:utf8

import socket
import time

IP = "127.0.0.1"
PORT = 9995
max_sock_count = 100
addr = (IP, PORT)

sock_list = []
for i in range(max_sock_count):
    print "connect", i
    s = socket.socket(2, 1, 0)
    s.connect(addr)
    sock_list.append(s)

for i in range(max_sock_count):
    s = sock_list[i]
    s.send(b"ABCD")
    v = s.recv(20)
    print "read", i, v
