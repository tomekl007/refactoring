package com.sabre.bto.refactoring.strategy.sorter;


import com.sabre.bto.refactoring.strategy.sorter.provider.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class CombSorterTest {

    @Test
    @Parameters(source = IntArrayProvider.class)
    public void testIntSort(int[] array, int[] sortedArray) throws Exception {
        int operations = new IntArrayCombSorter().sort(array);
        System.out.println("IntArrayCombSorter, operations=" + operations);
        assertThat(array).containsSequence(sortedArray);
    }

    @Test
    @Parameters(source = DoubleArrayProvider.class)
    public void testDoubleSort(double[] array, double[] sortedArray) throws Exception {
        int operations = new DoubleArrayCombSorter().sort(array);
        System.out.println("DoubleArrayCombSorter, operations=" + operations);
        assertThat(array).containsSequence(sortedArray);
    }

    @Test
    @Parameters(source = StringArrayProvider.class)
    public void testComparableSort(ArrayHolder<String> array, ArrayHolder<String> sortedArray) throws Exception {
        int operations = new ComparableArrayCombSorter<String>().sort(array.getArray());
        System.out.println("ComparableArrayCombSorter, operations=" + operations);
        assertThat(array.getArray()).containsSequence(sortedArray.getArray());
    }
}
