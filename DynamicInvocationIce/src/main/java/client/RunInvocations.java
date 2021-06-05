package client;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import java.util.HashMap;
import java.util.Map;

public class RunInvocations {
    public static void main(String[] args) {
        Communicator communicator = Util.initialize(args);
        ObjectPrx dynamicObjPrx = communicator.propertyToProxy("DynamicProxy");

        Client client = new Client(dynamicObjPrx, communicator);

        var res1 = client.invokeCountOccurences(new int[] {1, 1, 5, 5, 5, 3, 0, 8, 8, 8, 4, 3, 1});
        var res2 = client.invokeReverseDict( new HashMap<>(){{
            put("abc", 1);
            put("abba", 1);
            put("cdvetb", 2);
            put("egtbsfgb", 1);
            put("kwhk", 3);
            put("eurfhwkuh", 2);
        }});
        var res3 = client.invokeGroupByKey(new Map[] {
                new HashMap<>() {{
                    put("aba", 1);
                    put("abba", 4);
                }},
                new HashMap<>() {{
                    put("aba", 5);
                    put("caba", 8);
                }},
                new HashMap<>() {{
                    put("abba", 14);
                    put("caba", 3);
                }}
        });

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        communicator.destroy();
    }
}
