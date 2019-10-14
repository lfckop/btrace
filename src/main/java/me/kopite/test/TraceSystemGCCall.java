package me.kopite.test;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Location;
import static com.sun.btrace.BTraceUtils.*; 

// https://hllvm-group.iteye.com/group/topic/29340
// RednaxelaFX
@BTrace
public class TraceSystemGCCall {
    @OnMethod(clazz = "/.*/", 
            method = "/.*/", 
            location = @Location(value = Kind.CALL, clazz = "java.lang.System", method = "gc"))
    public static void onSystemGC() {
        jstack();
    }
}
