# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.5
#
# <auto-generated>
#
# Generated from file `Notification.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy

# Start of module Notification
_M_Notification = Ice.openModule('Notification')
__name__ = 'Notification'

if 'timeStamp' not in _M_Notification.__dict__:
    _M_Notification.timeStamp = Ice.createTempClass()
    class timeStamp(object):
        def __init__(self, hour=0, minute=0, second=0):
            self.hour = hour
            self.minute = minute
            self.second = second

        def __hash__(self):
            _h = 0
            _h = 5 * _h + Ice.getHash(self.hour)
            _h = 5 * _h + Ice.getHash(self.minute)
            _h = 5 * _h + Ice.getHash(self.second)
            return _h % 0x7fffffff

        def __compare(self, other):
            if other is None:
                return 1
            elif not isinstance(other, _M_Notification.timeStamp):
                return NotImplemented
            else:
                if self.hour is None or other.hour is None:
                    if self.hour != other.hour:
                        return (-1 if self.hour is None else 1)
                else:
                    if self.hour < other.hour:
                        return -1
                    elif self.hour > other.hour:
                        return 1
                if self.minute is None or other.minute is None:
                    if self.minute != other.minute:
                        return (-1 if self.minute is None else 1)
                else:
                    if self.minute < other.minute:
                        return -1
                    elif self.minute > other.minute:
                        return 1
                if self.second is None or other.second is None:
                    if self.second != other.second:
                        return (-1 if self.second is None else 1)
                else:
                    if self.second < other.second:
                        return -1
                    elif self.second > other.second:
                        return 1
                return 0

        def __lt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r < 0

        def __le__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r <= 0

        def __gt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r > 0

        def __ge__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r >= 0

        def __eq__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r == 0

        def __ne__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r != 0

        def __str__(self):
            return IcePy.stringify(self, _M_Notification._t_timeStamp)

        __repr__ = __str__

    _M_Notification._t_timeStamp = IcePy.defineStruct('::Notification::timeStamp', timeStamp, (), (
        ('hour', (), IcePy._t_int),
        ('minute', (), IcePy._t_int),
        ('second', (), IcePy._t_int)
    ))

    _M_Notification.timeStamp = timeStamp
    del timeStamp

if 'caseResult' not in _M_Notification.__dict__:
    _M_Notification.caseResult = Ice.createTempClass()
    class caseResult(object):
        def __init__(self, registrationTimeStamp=Ice._struct_marker, expectedResolutionTime=0, resultMessage=''):
            if registrationTimeStamp is Ice._struct_marker:
                self.registrationTimeStamp = _M_Notification.timeStamp()
            else:
                self.registrationTimeStamp = registrationTimeStamp
            self.expectedResolutionTime = expectedResolutionTime
            self.resultMessage = resultMessage

        def __hash__(self):
            _h = 0
            _h = 5 * _h + Ice.getHash(self.registrationTimeStamp)
            _h = 5 * _h + Ice.getHash(self.expectedResolutionTime)
            _h = 5 * _h + Ice.getHash(self.resultMessage)
            return _h % 0x7fffffff

        def __compare(self, other):
            if other is None:
                return 1
            elif not isinstance(other, _M_Notification.caseResult):
                return NotImplemented
            else:
                if self.registrationTimeStamp is None or other.registrationTimeStamp is None:
                    if self.registrationTimeStamp != other.registrationTimeStamp:
                        return (-1 if self.registrationTimeStamp is None else 1)
                else:
                    if self.registrationTimeStamp < other.registrationTimeStamp:
                        return -1
                    elif self.registrationTimeStamp > other.registrationTimeStamp:
                        return 1
                if self.expectedResolutionTime is None or other.expectedResolutionTime is None:
                    if self.expectedResolutionTime != other.expectedResolutionTime:
                        return (-1 if self.expectedResolutionTime is None else 1)
                else:
                    if self.expectedResolutionTime < other.expectedResolutionTime:
                        return -1
                    elif self.expectedResolutionTime > other.expectedResolutionTime:
                        return 1
                if self.resultMessage is None or other.resultMessage is None:
                    if self.resultMessage != other.resultMessage:
                        return (-1 if self.resultMessage is None else 1)
                else:
                    if self.resultMessage < other.resultMessage:
                        return -1
                    elif self.resultMessage > other.resultMessage:
                        return 1
                return 0

        def __lt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r < 0

        def __le__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r <= 0

        def __gt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r > 0

        def __ge__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r >= 0

        def __eq__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r == 0

        def __ne__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r != 0

        def __str__(self):
            return IcePy.stringify(self, _M_Notification._t_caseResult)

        __repr__ = __str__

    _M_Notification._t_caseResult = IcePy.defineStruct('::Notification::caseResult', caseResult, (), (
        ('registrationTimeStamp', (), _M_Notification._t_timeStamp),
        ('expectedResolutionTime', (), IcePy._t_int),
        ('resultMessage', (), IcePy._t_string)
    ))

    _M_Notification.caseResult = caseResult
    del caseResult

if '_t_batchedResults' not in _M_Notification.__dict__:
    _M_Notification._t_batchedResults = IcePy.defineSequence('::Notification::batchedResults', (), _M_Notification._t_caseResult)

_M_Notification._t_Notifier = IcePy.defineValue('::Notification::Notifier', Ice.Value, -1, (), False, True, None, ())

if 'NotifierPrx' not in _M_Notification.__dict__:
    _M_Notification.NotifierPrx = Ice.createTempClass()
    class NotifierPrx(Ice.ObjectPrx):

        def singleNotify(self, result, context=None):
            return _M_Notification.Notifier._op_singleNotify.invoke(self, ((result, ), context))

        def singleNotifyAsync(self, result, context=None):
            return _M_Notification.Notifier._op_singleNotify.invokeAsync(self, ((result, ), context))

        def begin_singleNotify(self, result, _response=None, _ex=None, _sent=None, context=None):
            return _M_Notification.Notifier._op_singleNotify.begin(self, ((result, ), _response, _ex, _sent, context))

        def end_singleNotify(self, _r):
            return _M_Notification.Notifier._op_singleNotify.end(self, _r)

        def batchedNotify(self, results, context=None):
            return _M_Notification.Notifier._op_batchedNotify.invoke(self, ((results, ), context))

        def batchedNotifyAsync(self, results, context=None):
            return _M_Notification.Notifier._op_batchedNotify.invokeAsync(self, ((results, ), context))

        def begin_batchedNotify(self, results, _response=None, _ex=None, _sent=None, context=None):
            return _M_Notification.Notifier._op_batchedNotify.begin(self, ((results, ), _response, _ex, _sent, context))

        def end_batchedNotify(self, _r):
            return _M_Notification.Notifier._op_batchedNotify.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_Notification.NotifierPrx.ice_checkedCast(proxy, '::Notification::Notifier', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_Notification.NotifierPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::Notification::Notifier'
    _M_Notification._t_NotifierPrx = IcePy.defineProxy('::Notification::Notifier', NotifierPrx)

    _M_Notification.NotifierPrx = NotifierPrx
    del NotifierPrx

    _M_Notification.Notifier = Ice.createTempClass()
    class Notifier(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::Notification::Notifier')

        def ice_id(self, current=None):
            return '::Notification::Notifier'

        @staticmethod
        def ice_staticId():
            return '::Notification::Notifier'

        def singleNotify(self, result, current=None):
            raise NotImplementedError("servant method 'singleNotify' not implemented")

        def batchedNotify(self, results, current=None):
            raise NotImplementedError("servant method 'batchedNotify' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_Notification._t_NotifierDisp)

        __repr__ = __str__

    _M_Notification._t_NotifierDisp = IcePy.defineClass('::Notification::Notifier', Notifier, (), None, ())
    Notifier._ice_type = _M_Notification._t_NotifierDisp

    Notifier._op_singleNotify = IcePy.Operation('singleNotify', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_Notification._t_caseResult, False, 0),), (), None, ())
    Notifier._op_batchedNotify = IcePy.Operation('batchedNotify', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_Notification._t_batchedResults, False, 0),), (), None, ())

    _M_Notification.Notifier = Notifier
    del Notifier

# End of module Notification
