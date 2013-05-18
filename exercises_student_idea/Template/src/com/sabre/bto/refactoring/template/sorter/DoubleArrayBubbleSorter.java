package com.sabre.bto.refactoring.template.sorter;


public class DoubleArrayBubbleSorter extends AbstractBubbleSorter<double[]>{



    protected boolean shouldSwap(double[] array, int index) {
        return array[index] > array[(index + 1)];
    }

    protected void swap(double[] array, int index) {
        double temp = array[index];
        array[index] = array[(index + 1)];
        array[(index + 1)] = temp;
    }

    protected int getLength(double[] array) {
        return array.length;
    }

}
