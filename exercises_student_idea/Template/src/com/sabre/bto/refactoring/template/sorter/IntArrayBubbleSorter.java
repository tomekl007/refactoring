package com.sabre.bto.refactoring.template.sorter;


public class IntArrayBubbleSorter extends AbstractBubbleSorter<int[]> {

    @Override
    protected int getLength(int[] array) {
        return array.length ;
    }

    @Override
    protected boolean shouldSwap(int[] array, int index) {
        return array[index] > array[(index + 1)];
    }

    @Override
    protected void swap(int[] array, int index) {
        int temp = array[index];
        array[index] = array[(index + 1)];
        array[(index + 1)] = temp;
    }

}