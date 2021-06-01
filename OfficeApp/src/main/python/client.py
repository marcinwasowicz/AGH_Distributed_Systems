from overrides import overrides
from datetime import datetime

import sys
import Ice
import Notification
import Office
import logging


def is_float(number: str):
    return len([i for i in number.split('.') if i.isdigit()]) in [1, 2]


def to_bool(expr: str):
    return {'true': True, 'false': False}.get(expr, None)


class NotifierImpl(Notification.Notifier):
    def __init__(self):
        logging.basicConfig(level=logging.INFO)
        self.logger = logging.getLogger("notifier")

    @overrides
    def singleNotify(self, result, current=None):
        request_time = ':'.join([
            str(result.registrationTimeStamp.hour),
            str(result.registrationTimeStamp.minute),
            str(result.registrationTimeStamp.second)
        ])

        completion_time = ':'.join([
            str(datetime.now().hour),
            str(datetime.now().minute),
            str(datetime.now().second)
        ])

        self.logger.info(
            'Case requested at {}, with expected resolution time of {}, was completed at {}, with result of {}'.
            format(request_time, result.expectedResolutionTime, completion_time, result.resultMessage)
        )

    @overrides
    def batchedNotify(self, results, current=None):
        for result in results:
            self.singleNotify(result, current)


class NotifierProxyFactory:
    def __init__(self, icm: Ice.CommunicatorI,
                 srv_prx: Office.ServicePrx,
                 rcp_prx: Office.ReceptionPrx):

        self.object_adapter = icm.createObjectAdapter("")
        self.service_proxy = srv_prx
        self.reception_proxy = rcp_prx

    def create_notifier_proxy(self):
        new_notifier_proxy = Notification.NotifierPrx.uncheckedCast(
            self.object_adapter.addWithUUID(NotifierImpl())
        )

        self.service_proxy.ice_getCachedConnection().setAdapter(self.object_adapter)
        self.reception_proxy.ice_getCachedConnection().setAdapter(self.object_adapter)
        return new_notifier_proxy


class CommandLineInterface:
    def __init__(self, uid,
                 srv_prx: Office.ServicePrx,
                 rcp_prx: Office.ReceptionPrx,
                 icm: Ice.CommunicatorI):

        self.unique_id = uid
        self.service_proxy = srv_prx
        self.reception_proxy = rcp_prx
        self.notifier_proxy_factory = NotifierProxyFactory(icm, srv_prx, rcp_prx)
        self.notifier_proxy = self.notifier_proxy_factory.create_notifier_proxy()
        self.logger = logging.getLogger("command line interface")

        self.reception_proxy.register(uid, self.notifier_proxy)

    def parse_request(self):
        request = input("Enter your request: \n")
        request = request.rstrip().split(' ')
        time_stamp = datetime.now()

        data = Office.basicCaseData(
            self.unique_id,
            Notification.timeStamp(time_stamp.hour, time_stamp.minute, time_stamp.second)
        )

        if len(request) == 2 and request[0] == 'AID' and is_float(request[1]):
            average_earnings = float(request[1])
            expected_time = self.service_proxy.requestUnemployedAid(data, average_earnings)
            self.logger.info(
                'Expected resolution time for case {} is {}'.format(request[0], expected_time)
            )

        elif len(request) == 3 and request[0] == 'PERMISSION' and is_float(request[2]):
            address = request[1]
            area = float(request[2])
            expected_time = self.service_proxy.requestBuildingPermission(data, address, area)
            self.logger.info(
                'Expected resolution time for case {} is {}'.format(request[0], expected_time)
            )

        elif len(request) == 3 and request[0] == 'LICENCE' and to_bool(request[2]) is not None and request[1].isdigit():
            candidate_profile = int(request[1])
            first_licence = to_bool(request[2])
            expected_time = self.service_proxy.requestDrivingLicence(data, candidate_profile, first_licence)
            self.logger.info(
                'Expected resolution time for case {} is {}'.format(request[0], expected_time)
            )

        elif len(request) == 1 and request[0] == 'REFRESH':
            self.notifier_proxy = self.notifier_proxy_factory.create_notifier_proxy()
            self.reception_proxy.register(self.unique_id, self.notifier_proxy)

        elif len(request) == 1 and request[0] == 'QUIT':
            return False

        else:
            self.logger.warning('Invalid arguments provided!')

        return True


if __name__ == '__main__':
    with Ice.initialize(sys.argv) as communicator:
        try:
            reception_proxy = Office.ReceptionPrx.checkedCast(communicator.propertyToProxy('ReceptionProxy'))
            service_proxy = Office.ServicePrx.checkedCast(communicator.propertyToProxy('ServiceProxy'))
            cli = CommandLineInterface(int(sys.argv[1]), service_proxy, reception_proxy, communicator)

            while cli.parse_request():
                pass

        except Exception:
            raise RuntimeError("Connection to server failed. Restart needed.")

