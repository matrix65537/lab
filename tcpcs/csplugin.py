#!/usr/bin/env python
# -*- coding:utf-8 -*-
from __future__ import unicode_literals

import socket
from scplib import *
from iplugin import Plugin
from icommand import *
from state import *

DEFAULT_IP     = '127.0.0.1'
DEFAULT_PORT   = 8888

listen_sock_dict = {}
server_sock_dict = {}
client_sock_dict = {}


class CommandServerBindListen(Command):

    def add_args(self):
        self.get_parser().add_argument('-i', '--ip', dest = 'ip', metavar = 'ip', action = "store", default = DEFAULT_IP, help='the server ip')
        self.get_parser().add_argument('-p', '--port', dest = 'port', metavar = 'port', type = int, action = "store", default = DEFAULT_PORT, help='the server port')
        self.get_parser().add_argument('-n', '--nblock', action = "store_true", default = False, help='the non block')

    def perform(self, argv):
        ns = self.get_parser().parse_args(argv[1:])
        addr = (ns.ip, ns.port)

        listen_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        listen_sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        listen_sock.setblocking(True)
        listen_sock.bind(addr)
        listen_sock.listen(0x10)
        fd = listen_sock.fileno()
        listen_sock_dict[fd] = (ns.port, listen_sock)

        log.debug("Server bind and listen at %s:%d [fd = %d]" %(ns.ip, ns.port, fd))

class CommandSocketList(Command):

    def add_args(self):
        pass

    def perform(self, argv):
        ns = self.get_parser().parse_args(argv[1:])
        def dump_dict(name, d):
            keys = d.keys()
            keys.sort()
            log.debug(name)
            for k in keys:
                log.debug("%4d :%d" %(k, d[k][0]))
        dump_dict("listen socket:", listen_sock_dict)
        dump_dict("server socket:", server_sock_dict)
        dump_dict("client socket:", client_sock_dict)

class CommandServerAccept(Command):

    def add_args(self):
        self.get_parser().add_argument('fd', type = int, action = "store", help='the socket fd')
        self.get_parser().add_argument('-n', '--nblock', action = "store_true", default = False, help='the non block')

    def perform(self, argv):
        ns = self.get_parser().parse_args(argv[1:])
        listen_port, listen_sock = listen_sock_dict[ns.fd]
        log.debug("Server accept...")
        sock, addr = listen_sock.accept()

        sock.setblocking(True)
        fd = sock.fileno()
        peer_ip, peer_port = sock.getpeername()
        server_sock_dict[fd] = (peer_port, sock)
        log.debug("Server accept connection from %s:%d [fd = %d]" %(peer_ip, peer_port, fd))

class CommandClientConnect(Command):

    def add_args(self):
        self.get_parser().add_argument('-i', '--ip', dest = 'ip', metavar = 'ip', action = "store", default = DEFAULT_IP, help='the server ip')
        self.get_parser().add_argument('-p', '--port', dest = 'port', metavar = 'port', type = int, action = "store", default = DEFAULT_PORT, help='the server port')
        self.get_parser().add_argument('-n', '--nblock', action = "store_true", default = False, help='the non block')

    def perform(self, argv):
        ns = self.get_parser().parse_args(argv[1:])
        addr = (ns.ip, ns.port)

        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.setblocking(True)
        fd = sock.fileno()
        sock.connect(addr)
        local_ip, local_port = sock.getsockname()
        client_sock_dict[fd] = (local_port, sock)
        log.debug("client connect at %s:%d [fd = %d]" %(local_ip, local_port, fd))

class CommandSocketClose(Command):

    def add_args(self):
        self.get_parser().add_argument('fd', type = int, action = "store", help='the socket fd')

    def perform(self, argv):
        ns = self.get_parser().parse_args(argv[1:])
        fd = ns.fd
        if fd in listen_sock_dict:
            listen_sock_dict[fd][1].close()
            #del listen_sock_dict[fd]
        if fd in server_sock_dict:
            server_sock_dict[fd][1].close()
            #del server_sock_dict[fd]
        if fd in client_sock_dict:
            client_sock_dict[fd][1].close()
            #del client_sock_dict[fd]

class CommandSocketSend(Command):

    def add_args(self):
        self.get_parser().add_argument('fd', type = int, action = "store", help='the socket fd')
        self.get_parser().add_argument('data', action = "store", help='the data to be send')

    def perform(self, argv):
        ns = self.get_parser().parse_args(argv[1:])
        fd = ns.fd
        data = b(ns.data)
        sock = None
        while True:
            if fd in server_sock_dict:
                sock = server_sock_dict[fd][1]
                break
            if fd in client_sock_dict:
                sock = client_sock_dict[fd][1]
                break
            break
        length = sock.send(data)
        log.debug("send data length = %d" %(length))
        log.debug(get_dump_string("send", data[:length]))

class CommandSocketRecv(Command):

    def add_args(self):
        self.get_parser().add_argument('fd', type = int, action = "store", help='the socket fd')
        self.get_parser().add_argument('length', type = int, action = "store", help='the recv length')

    def perform(self, argv):
        ns = self.get_parser().parse_args(argv[1:])
        fd = ns.fd
        length = ns.length
        sock = None
        while True:
            if fd in server_sock_dict:
                sock = server_sock_dict[fd][1]
                break
            if fd in client_sock_dict:
                sock = client_sock_dict[fd][1]
                break
            break
        data = sock.recv(length)
        log.debug("recv data length = %d" %(len(data)))
        log.debug(get_dump_string("recv", data))

_cs_cmd_dict = {
    "server-bind-listen": CommandServerBindListen,
    "server-accept"     : CommandServerAccept,

    "client-connect"    : CommandClientConnect,

    "socket-close"      : CommandSocketClose,
    "socket-send"       : CommandSocketSend,
    "socket-recv"       : CommandSocketRecv,

    "socket-list"       : CommandSocketList,
}

class TCPCSPlugin(Plugin):
    def __init__(self):
        self.__name = "cs"
        self.__cmd_dict = _cs_cmd_dict
        self.__var_dict = {}

    def get_name(self):
        return self.__name

    def get_cmd_dict(self):
        return self.__cmd_dict

    def get_var_dict(self):
        return self.__var_dict

    def get_ext_matches(self):
        return []

def register(plugin_dict):
    plugin_object = TCPCSPlugin()
    plugin_dict[plugin_object.get_name()] = plugin_object

