package me.kopite.test;

import static com.sun.btrace.BTraceUtils.print;
import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.BTraceUtils.Threads;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;

/**
 * 排查类加载最后一步即初始化的问题，追踪类构造器clinit的异常
 * 
 * @author zhouwei
 *
 */
@BTrace
public class ClassInitializationTracing {

    @OnMethod(clazz = "com.sankuai.qcs.dispatch.monitor.util.common.EnviromentUtil$EnvHolder", method = "<clinit>", location = @Location(Kind.ERROR))
    public static void onError(Throwable e) {
        print(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        println("###error###");
        
        Threads.jstack(e);
        
        println("==========================");
    }

    @OnMethod(clazz = "com.sankuai.qcs.dispatch.monitor.util.common.EnviromentUtil$EnvHolder", method = "<clinit>", location = @Location(Kind.THROW))
    public static void onException(Throwable e) {
        print(BTraceUtils.Time.timestamp("yyyy-MM-dd HH:mm:ss"));
        println("###throw###");
        
        Threads.jstack(e);
        
        println("==========================");
    }
   
}
