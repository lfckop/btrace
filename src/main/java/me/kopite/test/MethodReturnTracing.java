package me.kopite.test;

import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.str;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Return;

/**
 * Created by zhouwei on 2017-6-21.
 */

@BTrace
public class MethodReturnTracing {

    @OnMethod(clazz = "com.sankuai.meituan.banma.thrift.haikui.iface.impl.BmHaikuiContractPoiThriftIfaceImpl", method = "getBmHaikuiContractPoiRelListByUtime", location = @Location(Kind.RETURN))
    public static void onMethodReturn(@Return Object result) {
        println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        println("method return: " + str(result));
        println("==========================");
    }
}
