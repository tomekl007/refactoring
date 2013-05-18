package com.sabre.bto.refactoring.template.sorter;

import com.sabre.bto.refactoring.template.sorter.provider.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;


@RunWith(JUnitParamsRunner.class)
public class BubbleSorterTest {

    @Test
    @Parameters(source = IntArrayProvider.class)
    public void testIntSort(int[] array, int[] sortedArray) throws Exception {
        int operations = new IntArrayBubbleSorter().sort(array);
        System.out.println("IntArrayBubbleSorter, operations=" + operations);
        assertThat(array).containsSequence(sortedArray);
    }

    @Test
    @Parameters(source = DoubleArrayProvider.class)
    public void testDoubleSort(double[] array, double[] sortedArray) throws Exception {
        int operations = new DoubleArrayBubbleSorter().sort(array);
        System.out.println("DoubleArrayBubbleSorter, operations=" + operations);
        assertThat(array).containsSequence(sortedArray);
    }

    @Test
    @Parameters(source = StringArrayProvider.class)
    public void testComparableArraySort(ArrayHolder<String> array, ArrayHolder<String> sortedArray) throws Exception {
        int operations = new ComparableArrayBubbleSorter<String>().sort(array.getArray());
        System.out.println("ComparableArrayBubbleSorter, operations=" + operations);
        assertThat(array.getArray()).containsSequence(sortedArray.getArray());
    }

   @Test
   @Parameters(source = StringListProvider.class)
   public void testComparableListSort(List<String> list, List<String> sortedList) throws Exception {
       System.out.println("list : " + list + " sortedList : " + sortedList  );



      int operations = new ComparableListBubbleSorter<String>().sort(list);
      System.out.println("ComparablListBubbleSorter, operations=" + operations);
      assertThat(list).isSubsetOf(sortedList);
   }
}
