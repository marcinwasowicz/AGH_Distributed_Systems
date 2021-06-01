# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.5
#
# <auto-generated>
#
# Generated from file `Office.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy
import Notification_ice

# Included module Notification
_M_Notification = Ice.openModule('Notification')

# Start of module Office
_M_Office = Ice.openModule('Office')
__name__ = 'Office'

if 'basicCaseData' not in _M_Office.__dict__:
    _M_Office.basicCaseData = Ice.createTempClass()
    class basicCaseData(object):
        def __init__(self, uniqueID=0, registrationTime=Ice._struct_marker):
            self.uniqueID = uniqueID
            if registrationTime is Ice._struct_marker:
                self.registrationTime = _M_Notification.timeStamp()
            else:
                self.registrationTime = registrationTime

        def __hash__(self):
            _h = 0
            _h = 5 * _h + Ice.getHash(self.uniqueID)
            _h = 5 * _h + Ice.getHash(self.registrationTime)
            return _h % 0x7fffffff

        def __compare(self, other):
            if other is None:
                return 1
            elif not isinstance(other, _M_Office.basicCaseData):
                return NotImplemented
            else:
                if self.uniqueID is None or other.uniqueID is None:
                    if self.uniqueID != other.uniqueID:
                        return (-1 if self.uniqueID is None else 1)
                else:
                    if self.uniqueID < other.uniqueID:
                        return -1
                    elif self.uniqueID > other.uniqueID:
                        return 1
                if self.registrationTime is None or other.registrationTime is None:
                    if self.registrationTime != other.registrationTime:
                        return (-1 if self.registrationTime is None else 1)
                else:
                    if self.registrationTime < other.registrationTime:
                        return -1
                    elif self.registrationTime > other.registrationTime:
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
            return IcePy.stringify(self, _M_Office._t_basicCaseData)

        __repr__ = __str__

    _M_Office._t_basicCaseData = IcePy.defineStruct('::Office::basicCaseData', basicCaseData, (), (
        ('uniqueID', (), IcePy._t_int),
        ('registrationTime', (), _M_Notification._t_timeStamp)
    ))

    _M_Office.basicCaseData = basicCaseData
    del basicCaseData

_M_Office._t_Reception = IcePy.defineValue('::Office::Reception', Ice.Value, -1, (), False, True, None, ())

if 'ReceptionPrx' not in _M_Office.__dict__:
    _M_Office.ReceptionPrx = Ice.createTempClass()
    class ReceptionPrx(Ice.ObjectPrx):

        def register(self, uniqueID, notifier, context=None):
            return _M_Office.Reception._op_register.invoke(self, ((uniqueID, notifier), context))

        def registerAsync(self, uniqueID, notifier, context=None):
            return _M_Office.Reception._op_register.invokeAsync(self, ((uniqueID, notifier), context))

        def begin_register(self, uniqueID, notifier, _response=None, _ex=None, _sent=None, context=None):
            return _M_Office.Reception._op_register.begin(self, ((uniqueID, notifier), _response, _ex, _sent, context))

        def end_register(self, _r):
            return _M_Office.Reception._op_register.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_Office.ReceptionPrx.ice_checkedCast(proxy, '::Office::Reception', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_Office.ReceptionPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::Office::Reception'
    _M_Office._t_ReceptionPrx = IcePy.defineProxy('::Office::Reception', ReceptionPrx)

    _M_Office.ReceptionPrx = ReceptionPrx
    del ReceptionPrx

    _M_Office.Reception = Ice.createTempClass()
    class Reception(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::Office::Reception')

        def ice_id(self, current=None):
            return '::Office::Reception'

        @staticmethod
        def ice_staticId():
            return '::Office::Reception'

        def register(self, uniqueID, notifier, current=None):
            raise NotImplementedError("servant method 'register' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_Office._t_ReceptionDisp)

        __repr__ = __str__

    _M_Office._t_ReceptionDisp = IcePy.defineClass('::Office::Reception', Reception, (), None, ())
    Reception._ice_type = _M_Office._t_ReceptionDisp

    Reception._op_register = IcePy.Operation('register', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_int, False, 0), ((), _M_Notification._t_NotifierPrx, False, 0)), (), None, ())

    _M_Office.Reception = Reception
    del Reception

_M_Office._t_Service = IcePy.defineValue('::Office::Service', Ice.Value, -1, (), False, True, None, ())

if 'ServicePrx' not in _M_Office.__dict__:
    _M_Office.ServicePrx = Ice.createTempClass()
    class ServicePrx(Ice.ObjectPrx):

        def requestUnemployedAid(self, data, averageEarnings, context=None):
            return _M_Office.Service._op_requestUnemployedAid.invoke(self, ((data, averageEarnings), context))

        def requestUnemployedAidAsync(self, data, averageEarnings, context=None):
            return _M_Office.Service._op_requestUnemployedAid.invokeAsync(self, ((data, averageEarnings), context))

        def begin_requestUnemployedAid(self, data, averageEarnings, _response=None, _ex=None, _sent=None, context=None):
            return _M_Office.Service._op_requestUnemployedAid.begin(self, ((data, averageEarnings), _response, _ex, _sent, context))

        def end_requestUnemployedAid(self, _r):
            return _M_Office.Service._op_requestUnemployedAid.end(self, _r)

        def requestBuildingPermission(self, data, address, area, context=None):
            return _M_Office.Service._op_requestBuildingPermission.invoke(self, ((data, address, area), context))

        def requestBuildingPermissionAsync(self, data, address, area, context=None):
            return _M_Office.Service._op_requestBuildingPermission.invokeAsync(self, ((data, address, area), context))

        def begin_requestBuildingPermission(self, data, address, area, _response=None, _ex=None, _sent=None, context=None):
            return _M_Office.Service._op_requestBuildingPermission.begin(self, ((data, address, area), _response, _ex, _sent, context))

        def end_requestBuildingPermission(self, _r):
            return _M_Office.Service._op_requestBuildingPermission.end(self, _r)

        def requestDrivingLicence(self, data, candidateProfile, isFirstLicence, context=None):
            return _M_Office.Service._op_requestDrivingLicence.invoke(self, ((data, candidateProfile, isFirstLicence), context))

        def requestDrivingLicenceAsync(self, data, candidateProfile, isFirstLicence, context=None):
            return _M_Office.Service._op_requestDrivingLicence.invokeAsync(self, ((data, candidateProfile, isFirstLicence), context))

        def begin_requestDrivingLicence(self, data, candidateProfile, isFirstLicence, _response=None, _ex=None, _sent=None, context=None):
            return _M_Office.Service._op_requestDrivingLicence.begin(self, ((data, candidateProfile, isFirstLicence), _response, _ex, _sent, context))

        def end_requestDrivingLicence(self, _r):
            return _M_Office.Service._op_requestDrivingLicence.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_Office.ServicePrx.ice_checkedCast(proxy, '::Office::Service', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_Office.ServicePrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::Office::Service'
    _M_Office._t_ServicePrx = IcePy.defineProxy('::Office::Service', ServicePrx)

    _M_Office.ServicePrx = ServicePrx
    del ServicePrx

    _M_Office.Service = Ice.createTempClass()
    class Service(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::Office::Service')

        def ice_id(self, current=None):
            return '::Office::Service'

        @staticmethod
        def ice_staticId():
            return '::Office::Service'

        def requestUnemployedAid(self, data, averageEarnings, current=None):
            raise NotImplementedError("servant method 'requestUnemployedAid' not implemented")

        def requestBuildingPermission(self, data, address, area, current=None):
            raise NotImplementedError("servant method 'requestBuildingPermission' not implemented")

        def requestDrivingLicence(self, data, candidateProfile, isFirstLicence, current=None):
            raise NotImplementedError("servant method 'requestDrivingLicence' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_Office._t_ServiceDisp)

        __repr__ = __str__

    _M_Office._t_ServiceDisp = IcePy.defineClass('::Office::Service', Service, (), None, ())
    Service._ice_type = _M_Office._t_ServiceDisp

    Service._op_requestUnemployedAid = IcePy.Operation('requestUnemployedAid', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_Office._t_basicCaseData, False, 0), ((), IcePy._t_float, False, 0)), (), ((), IcePy._t_int, False, 0), ())
    Service._op_requestBuildingPermission = IcePy.Operation('requestBuildingPermission', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_Office._t_basicCaseData, False, 0), ((), IcePy._t_string, False, 0), ((), IcePy._t_float, False, 0)), (), ((), IcePy._t_int, False, 0), ())
    Service._op_requestDrivingLicence = IcePy.Operation('requestDrivingLicence', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_Office._t_basicCaseData, False, 0), ((), IcePy._t_long, False, 0), ((), IcePy._t_bool, False, 0)), (), ((), IcePy._t_int, False, 0), ())

    _M_Office.Service = Service
    del Service

# End of module Office