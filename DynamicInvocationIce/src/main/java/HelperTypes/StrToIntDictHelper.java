//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.5
//
// <auto-generated>
//
// Generated from file `HelperTypes.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package HelperTypes;
/**
 * Helper class for marshaling/unmarshaling StrToIntDict.
 **/

public final class StrToIntDictHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, java.util.Map<java.lang.String, java.lang.Integer> v)
    {
        if(v == null)
        {
            ostr.writeSize(0);
        }
        else
        {
            ostr.writeSize(v.size());
            for(java.util.Map.Entry<java.lang.String, java.lang.Integer> e : v.entrySet())
            {
                ostr.writeString(e.getKey());
                ostr.writeInt(e.getValue());
            }
        }
    }

    public static java.util.Map<java.lang.String, java.lang.Integer> read(com.zeroc.Ice.InputStream istr)
    {
        java.util.Map<java.lang.String, java.lang.Integer> v;
        v = new java.util.HashMap<java.lang.String, java.lang.Integer>();
        int sz0 = istr.readSize();
        for(int i0 = 0; i0 < sz0; i0++)
        {
            String key;
            key = istr.readString();
            int value;
            value = istr.readInt();
            v.put(key, value);
        }
        return v;
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<java.util.Map<java.lang.String, java.lang.Integer>> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Map<java.lang.String, java.lang.Integer> v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            StrToIntDictHelper.write(ostr, v);
            ostr.endSize(pos);
        }
    }

    public static java.util.Optional<java.util.Map<java.lang.String, java.lang.Integer>> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            java.util.Map<java.lang.String, java.lang.Integer> v;
            v = StrToIntDictHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}
