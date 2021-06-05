package client;

import HelperTypes.*;
import com.zeroc.Ice.*;
import com.zeroc.Ice.Object;
import java.util.Map;

public class Client {
    private final ObjectPrx dynamicObjPrx;
    private final Communicator communicator;

    public Client(ObjectPrx dynamicObjPrx, Communicator communicator){
        this.dynamicObjPrx  = dynamicObjPrx;
        this.communicator = communicator;
    }

    public Map<Integer, Integer> invokeCountOccurences(int[] array) {
        OutputStream paramStream = new OutputStream(this.communicator);
        paramStream.startEncapsulation();
        paramStream.writeIntSeq(array);
        paramStream.endEncapsulation();
        byte[] paramBytes = paramStream.finished();

        Object.Ice_invokeResult resultTuple = dynamicObjPrx.ice_invoke("countOccurences", OperationMode.Normal, paramBytes);
        if(resultTuple.returnValue){
            InputStream resultStream = new InputStream(communicator, resultTuple.outParams);
            resultStream.startEncapsulation();
            Map<Integer, Integer> result = CountDictHelper.read(resultStream);
            resultStream.endEncapsulation();
            return result;
        }
        else {
            throw new RuntimeException("Dynamic Invocation Failed");
        }
    }

    public Map<Integer, String[]> invokeReverseDict(Map<String, Integer> dict){
        OutputStream paramStream = new OutputStream(this.communicator);
        paramStream.startEncapsulation();
        StrToIntDictHelper.write(paramStream, dict);
        paramStream.endEncapsulation();
        byte[] paramBytes = paramStream.finished();

        Object.Ice_invokeResult resultTuple = dynamicObjPrx.ice_invoke("reverseDict", OperationMode.Normal, paramBytes);
        if(resultTuple.returnValue){
            InputStream resultStream = new InputStream(communicator, resultTuple.outParams);
            resultStream.startEncapsulation();
            Map<Integer, String[]> result = GroupedStringDictHelper.read(resultStream);
            resultStream.endEncapsulation();
            return result;
        }
        else {
            throw new RuntimeException("Dynamic Invocation Failed");
        }
    }

    public Map<String, int[]> invokeGroupByKey(Map<String, Integer>[] dictSeq){
        OutputStream paramStream = new OutputStream(this.communicator);
        paramStream.startEncapsulation();
        DictSeqHelper.write(paramStream, dictSeq);
        paramStream.endEncapsulation();
        byte[] paramBytes = paramStream.finished();

        Object.Ice_invokeResult resultTuple = dynamicObjPrx.ice_invoke("groupByKey", OperationMode.Normal, paramBytes);
        if(resultTuple.returnValue){
            InputStream resultStream = new InputStream(communicator, resultTuple.outParams);
            resultStream.startEncapsulation();
            Map<String, int[]> result = GroupedIntegersDictHelper.read(resultStream);
            resultStream.endEncapsulation();
            return result;
        }
        else {
            throw new RuntimeException("Dynamic Invocation Failed");
        }
    }

}
