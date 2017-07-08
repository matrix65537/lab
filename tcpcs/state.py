#!/usr/bin/env python
# -*- coding:utf-8 -*-
from __future__ import unicode_literals

_var_dict           = None

def set_var_dict(var_dict):
    global _var_dict
    _var_dict = var_dict

def get_var_dict():
    return _var_dict

