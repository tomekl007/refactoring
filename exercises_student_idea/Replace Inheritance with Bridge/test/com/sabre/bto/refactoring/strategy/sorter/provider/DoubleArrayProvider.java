package com.sabre.bto.refactoring.strategy.sorter.provider;


import static junitparams.JUnitParamsRunner.*;

public class DoubleArrayProvider {

    public static Object[] provideArraysAndResults() {
        return $($(new double[] {10.0, 12, 0, 4526.44, 6, -5.2}, new double[] {-5.2, 0, 6, 10.0, 12, 4526.44}),
                 $(new double[] {50.001, -0.1, -2.2, 17, 16, -55, 66, 7.7}, new double[] {-55, -2.2, -0.1, 7.7, 16, 17, 50.001, 66 }));
    }
}
