from overrides import overrides

import Ice
import DynamicExample
import sys


class DynamicInvocationsImpl(DynamicExample.DynamicInvocations):
    def __init__(self):
        super(DynamicInvocationsImpl, self).__init__()

    @overrides
    def groupByKey(self, seq, current=None):
        result = {}
        for d in seq:
            for key, val in d.items():
                if key not in result:
                    result[key] = []
                result[key].append(val)
        return result

    @overrides
    def reverseDict(self, dict, current=None):
        result = {}
        for key, val in dict.items():
            if val not in result:
                result[val] = []
            result[val].append(key)
        return result

    @overrides
    def countOccurences(self, group, current=None):
        result = {}
        for elem in group:
            if elem not in result:
                result[elem] = 0
            result[elem] += 1
        return result


if __name__ == '__main__':
    with Ice.initialize(sys.argv) as icm:
        adapter = icm.createObjectAdapter('Adapter')
        adapter.add(DynamicInvocationsImpl(), icm.stringToIdentity('DynamicObject'))
        adapter.activate()
        icm.waitForShutdown()
