package me.kopite.test;

import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils.Threads;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;

/**
 * Created by zhouwei on 2017-6-14.
 */

@BTrace
public class ClassInitializationTracingForDaas {
    
    @OnMethod(clazz = "me.kopite.test.Test", method = "main", location = @Location(Kind.ERROR))
    public static void onError(Throwable e) {
        println("###error###");
        Threads.jstack(e);
    }

    @OnMethod(clazz = "me.kopite.test.Test", method = "main", location = @Location(Kind.THROW))
    public static void onThrow(Throwable e) {
        println("###throw###");
        Threads.jstack(e);
    }

}
