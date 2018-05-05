#!/usr/bin/env python
# -*- coding:utf-8 -*-
from __future__ import unicode_literals

__copyright__ = '''If you meet some problems, please contact guobaorui4365@126.com
Copyright (c) 2017-2027 Two Prime Numbers.
All Rights Reserved.
'''
__version__ = "version: 2.6.5"


import os
import os.path
import sys
import re
import argparse
import StringIO
import getpass
from scplib import *
import datetime

################################################################################

_completer = None

################################################################################

def find_cmd(cmd_name):
    cmd_object_list = []
    return cmd_object_list

################################################################################

class Completer(object):
    def __init__(self):
        self.matches = []
        self.ext_matches = []

    def set_ext_matches(self, ext_matches):
        self.ext_matches = ext_matches

    def cmd_matches(self, text):
        matches = []
        #第一类为查找到的命令
        cmd_object_list = find_cmd(text)
        for cmd_object in cmd_object_list:
            matches.append(cmd_object.get_name())

        #当前目录的文件
        for file_dir in os.listdir('.'):
            if file_dir.startswith(text):
                matches.append(file_dir)

        #加上扩展提示
        for v in self.ext_matches:
            if v.startswith(text):
                matches.append(v)

        self.matches = matches

    def __call__(self, text, state):
        text = ed.decode(text)
        if state == 0:
            self.cmd_matches(text)
        try:
            return self.matches[state]
        except IndexError:
            return None

try:
    import readline
except ImportError:
    readline = None

HOMEDIR = os.environ.get("HOME")
if not HOMEDIR:
    HOMEDIR = os.environ.get("USERPROFILE", ".")

def init_readline():
    if not readline:
        return
    global _completer
    _completer = Completer()
    readline.set_completer(_completer)
    readline.parse_and_bind('tab: complete')
    try:
        readline.read_history_file(HOMEDIR + "/.scshellhist")
    except IOError:
        pass
    def on_exit():
        readline.write_history_file(HOMEDIR + "/.scshellhist")
    import atexit
    atexit.register(on_exit)

################################################################################

def process_cmd(cmd):
    '''处理一条命令'''
    global _cmd_num
    global _cmd_cells
    _cmd_num += 1
    cmd = decode(cmd)
    #python mode
    global _python_in_flag
    if _python_in_flag:
        if cmd.strip() == 'py-end':
            _python_in_flag = False
            exec_python()
        else:
            _python_cmd_list.append(cmd)
        return
    cmd = cmd.strip()
    if len(cmd) == 0:
        return
    #`//` are comments
    if cmd.startswith("//") or cmd.startswith("#"):
        return
    #`:`use for exec python command
    if cmd.startswith(":"):
        py_cmd = cmd[1:].strip()
        c = compile(py_cmd, 'python command error', mode='exec')
        exec c in _var_dict
        return
    #`'` used for output python var
    elif cmd.startswith("'"):
        var = cmd[1:].strip()
        if len(var) == 0:
            return
        if var in _var_dict:
            v = _var_dict[var]
            if isinstance(v, (int, long)):
                info = "0x%08X %d" %(v, v)
            else:
                info = str(v)
            debug(info)
        else:
            raise CmdException, "var not found"
        return
    #`!` used for shell cmd line
    else:
        shell_cmd = cmd.strip()
        if len(shell_cmd) == 0:
            return
        os.system(shell_cmd)

_cmd_num = 0
_python_in_flag = False
_python_cmd_list = []

def process():
    set_log_level(3)
    global _python_in_flag
    while True:
        try:
            head_info = ""
            try:
                cmd = raw_input("%s> " %(head_info))
            except EOFError:
                _python_in_flag = False
                exec_python()
                continue
            process_cmd(cmd)
        #except AttributeError as e:
        #    error("env not ready")
        except IOError as e:
            error(str(e))
        except CmdExitException as e:
            pass
        except Exception as e:
            import traceback
            if get_log_level() >= 5:
                error(traceback.format_exc())
            error(str(e))
        except KeyboardInterrupt as e:
            print
            debug("input q for exit")

def main():
    global _cmd_dict
    init_readline()
    process()

################################################################################

main()

