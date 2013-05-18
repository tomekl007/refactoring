package com.sabre.bto.refactoring.strategy.sorter.provider;

import static junitparams.JUnitParamsRunner.*;

public class IntArrayProvider {

    public static Object[] provideArraysAndResults() {
        return $($(new int[] {10, 12, 0, 4526, 6, -5}, new int[] {-5, 0, 6, 10, 12, 4526}),
                 $(new int[] {-500, 0, -2, 17, 16, -55, 6, 77}, new int[] {-500, -55, -2,  0, 6, 16, 17, 77}));
    }
}
