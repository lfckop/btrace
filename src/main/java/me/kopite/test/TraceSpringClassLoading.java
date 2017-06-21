package me.kopite.test;

import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils.Reflective;
import com.sun.btrace.BTraceUtils.Threads;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Return;

@BTrace
public class TraceSpringClassLoading {
    @OnMethod(clazz = "org.springframework.util.ClassUtils", method = "forName", location = @Location(Kind.RETURN))
    public static void forName(@Return Class cl) {
        println("loaded " + Reflective.name(cl));
        Threads.jstack();
        println("==========================================================================");
    }
}
