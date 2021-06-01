//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.5
//
// <auto-generated>
//
// Generated from file `Office.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Office;

public interface Service extends com.zeroc.Ice.Object
{
    int requestUnemployedAid(basicCaseData data, float averageEarnings, com.zeroc.Ice.Current current);

    int requestBuildingPermission(basicCaseData data, String address, float area, com.zeroc.Ice.Current current);

    int requestDrivingLicence(basicCaseData data, long candidateProfile, boolean isFirstLicence, com.zeroc.Ice.Current current);

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::Office::Service"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::Office::Service";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_requestUnemployedAid(Service obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        basicCaseData iceP_data;
        float iceP_averageEarnings;
        iceP_data = basicCaseData.ice_read(istr);
        iceP_averageEarnings = istr.readFloat();
        inS.endReadParams();
        int ret = obj.requestUnemployedAid(iceP_data, iceP_averageEarnings, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeInt(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_requestBuildingPermission(Service obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        basicCaseData iceP_data;
        String iceP_address;
        float iceP_area;
        iceP_data = basicCaseData.ice_read(istr);
        iceP_address = istr.readString();
        iceP_area = istr.readFloat();
        inS.endReadParams();
        int ret = obj.requestBuildingPermission(iceP_data, iceP_address, iceP_area, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeInt(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_requestDrivingLicence(Service obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        basicCaseData iceP_data;
        long iceP_candidateProfile;
        boolean iceP_isFirstLicence;
        iceP_data = basicCaseData.ice_read(istr);
        iceP_candidateProfile = istr.readLong();
        iceP_isFirstLicence = istr.readBool();
        inS.endReadParams();
        int ret = obj.requestDrivingLicence(iceP_data, iceP_candidateProfile, iceP_isFirstLicence, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeInt(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "requestBuildingPermission",
        "requestDrivingLicence",
        "requestUnemployedAid"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 1:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 2:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 4:
            {
                return _iceD_requestBuildingPermission(this, in, current);
            }
            case 5:
            {
                return _iceD_requestDrivingLicence(this, in, current);
            }
            case 6:
            {
                return _iceD_requestUnemployedAid(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}