package me.kopite.test;

/**
 * Created by zhouwei on 2017年6月14日.
 */

public class Test {
    
    public static void main(String[] args) {
        System.out.println("should not print me!!!");
    }
    
    static {
        test();
    }
    
    public static void test() {
        throw new RuntimeException("test");
    }

}
