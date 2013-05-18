package com.sabre.bto.refactoring.strategy.sorter.provider;


import java.util.Date;

import static junitparams.JUnitParamsRunner.*;

public class ComparableArrayProvider {

    public static Object[] provideCommandsAndResults() {
        return $($(new String[] {"John", "Anthony", "john", "1 Wayne" }, new String[] {"1 Wayne", "Anthony", "John", "john"}),
                 $(new Date[] {new Date(111)}, new Date[] {new Date(111)}));
    }
}
