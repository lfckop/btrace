package me.kopite.test;

import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.strcat;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils.Strings;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;

@BTrace
public class TraceByQindi {
    @OnMethod(clazz = "+java.lang.ClassLoader", method = "defineClass")
    public static void anyRead(AnyType[] args) {
        println(args[0]);
    }

    @OnMethod(clazz = "+java.lang.ClassLoader", method = "defineClass", location = @Location(Kind.ERROR))
    public static void onExecuteError(@ProbeClassName String className, Throwable e) {
        println(Strings.str(e));
        println("###error");
    }

    @OnMethod(clazz = "+java.lang.ClassLoader", method = "defineClass", location = @Location(Kind.THROW))
    public static void anyRead2(AnyType[] args) {
        println(strcat(Strings.str(args[0]), "###throw"));
    }
}
