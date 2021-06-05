# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.5
#
# <auto-generated>
#
# Generated from file `HelperTypes.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy

# Start of module HelperTypes
_M_HelperTypes = Ice.openModule('HelperTypes')
__name__ = 'HelperTypes'

if '_t_IntegerGroup' not in _M_HelperTypes.__dict__:
    _M_HelperTypes._t_IntegerGroup = IcePy.defineSequence('::HelperTypes::IntegerGroup', (), IcePy._t_int)

if '_t_StringGroup' not in _M_HelperTypes.__dict__:
    _M_HelperTypes._t_StringGroup = IcePy.defineSequence('::HelperTypes::StringGroup', (), IcePy._t_string)

if '_t_CountDict' not in _M_HelperTypes.__dict__:
    _M_HelperTypes._t_CountDict = IcePy.defineDictionary('::HelperTypes::CountDict', (), IcePy._t_int, IcePy._t_int)

if '_t_StrToIntDict' not in _M_HelperTypes.__dict__:
    _M_HelperTypes._t_StrToIntDict = IcePy.defineDictionary('::HelperTypes::StrToIntDict', (), IcePy._t_string, IcePy._t_int)

if '_t_GroupedIntegersDict' not in _M_HelperTypes.__dict__:
    _M_HelperTypes._t_GroupedIntegersDict = IcePy.defineDictionary('::HelperTypes::GroupedIntegersDict', (), IcePy._t_string, _M_HelperTypes._t_IntegerGroup)

if '_t_GroupedStringDict' not in _M_HelperTypes.__dict__:
    _M_HelperTypes._t_GroupedStringDict = IcePy.defineDictionary('::HelperTypes::GroupedStringDict', (), IcePy._t_int, _M_HelperTypes._t_StringGroup)

if '_t_DictSeq' not in _M_HelperTypes.__dict__:
    _M_HelperTypes._t_DictSeq = IcePy.defineSequence('::HelperTypes::DictSeq', (), _M_HelperTypes._t_StrToIntDict)

# End of module HelperTypes
