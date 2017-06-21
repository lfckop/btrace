package me.kopite.test;

import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils.Threads;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;

@BTrace
public class ClassInitializationTracing {

    @OnMethod(clazz = "me.kopite.springboot.test.Test", method = "test", location = @Location(Kind.ERROR))
    public static void onExecuteError(Throwable e) {
        println("###error");
        Threads.jstack(e);
    }

    @OnMethod(clazz = "me.kopite.springboot.test.Test", method = "test", location = @Location(Kind.THROW))
    public static void anyRead2(Throwable e) {
        println("###throw");
        Threads.jstack(e);
    }
}
