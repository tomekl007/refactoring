package com.sabre.bto.refactoring.template.sorter;


public class ComparableArrayBubbleSorter<T extends Comparable<T>> extends AbstractBubbleSorter<T[]>{



    protected int getLength(T[] s) {
        return s.length ;
    }

    protected void swap(T[] array, int index) {
        T temp = array[index];
        array[index] = array[(index + 1)];
        array[(index + 1)] = temp;
    }

    protected boolean shouldSwap(T[] array, int index) {
        return array[index].compareTo(array[(index + 1)]) > 0;
    }

}
