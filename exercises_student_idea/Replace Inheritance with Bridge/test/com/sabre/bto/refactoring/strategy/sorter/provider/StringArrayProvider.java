package com.sabre.bto.refactoring.strategy.sorter.provider;


import static junitparams.JUnitParamsRunner.*;

public class StringArrayProvider {

    public static String[] array = new String[] {"John", "Anthony", "john", "1 Wayne" };
    public static String[] sortedArray = new String[] {"1 Wayne", "Anthony", "John", "john" };


    public static String[] array2 = new String[] {"1", "01", "12", "2", "10", "9" };
    public static String[] sortedArray2 = new String[] {"01", "1", "10", "12", "2", "9"};

    public static Object[] provideArraysAndResults() {
        return $($( new ArrayHolder<String>(array), new ArrayHolder<String>(sortedArray)),
                 $(new ArrayHolder<String>(array2), new ArrayHolder<String>(sortedArray2)));
    }
}
