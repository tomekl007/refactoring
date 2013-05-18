package com.sabre.bto.refactoring.strategy.sorter;

import com.sabre.bto.refactoring.strategy.sorter.provider.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class SelectionSorterTest {

    @Test
    @Parameters(source = IntArrayProvider.class)
    public void testIntSort(int[] array, int[] sortedArray) throws Exception {
        int operations = new IntArraySelectionSorter().sort(array);
        System.out.println("IntArrayBubbleSorter, operations=" + operations);
        assertThat(array).containsSequence(sortedArray);
    }


    @Test
    @Parameters(source = DoubleArrayProvider.class)
    public void testDoubleSort(double[] array, double[] sortedArray) throws Exception {
        int operations = new DoubleArraySelectionSorter().sort(array);
        System.out.println("DoubleArraySelectionSorter, operations=" + operations);
        assertThat(array).containsSequence(sortedArray);
    }

    @Test
    @Parameters(source = StringArrayProvider.class)
    public void testComparableSort(ArrayHolder<String> array, ArrayHolder<String> sortedArray) throws Exception {
        int operations = new ComparableArraySelectionSorter<String>().sort(array.getArray());
        System.out.println("ComparableArraySelectionSorter, operations=" + operations);
        assertThat(array.getArray()).containsSequence(sortedArray.getArray());
    }
}
