package me.kopite.test;

import java.util.Date;

/**
 * Created by zhouwei on 2016年1月11日.
 */

public class Pair<T> {

    public Pair(T first, T second) {
        super();
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    private T first;
    private T second;
}

class DateInterval extends Pair<Date> {

    public DateInterval(Date first, Date second) {
        super(first, second);
    }

    @Override
    public void setSecond(Date second) {
        if (second.compareTo(getFirst()) >= 0) {
            super.setSecond(second);
        }
    }

    @Override
    public Date getSecond() {
        return (Date) super.getSecond().clone();
    }
}
