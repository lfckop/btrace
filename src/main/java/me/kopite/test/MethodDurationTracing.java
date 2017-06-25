package me.kopite.test;

import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.BTraceUtils.Strings;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;

/**
 * Created by zhouwei on 2017-6-22.
 */

@BTrace
public class MethodDurationTracing {
    
    @OnMethod(clazz = "com.sankuai.meituan.banma.thrift.haikui.service.BmHaikuiPoiService", method = "saveHaikuiPoiDeliveryInfo", location = @Location(Kind.RETURN))
    public static void onMethodReturn(@Duration long d) {
        println(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        println(Strings.strcat("Time taken (msec) ", Strings.str(d / 1000000)));
        println("==========================");
    }

}
