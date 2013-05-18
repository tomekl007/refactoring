package com.sabre.bto.refactoring.strategy.sorter;

import com.sabre.bto.refactoring.strategy.sorter.handler.SortHandler;

public class DoubleArrayBubbleSorterFactory implements SortHandlerFactory<double[]> {
    public DoubleArrayBubbleSorterFactory() {
    }

    @Override
    public SortHandler<double[]> createSortHandler(final double[] subject) {
        return new SortHandler<double[]>() {
            @Override
            public int getLength() {
                return subject.length;
            }

            @Override
            public int compare(int i, int j) {
                return subject[i] == subject[j] ? 0 : subject[i] < subject[j] ? -1 : 1;
            }

            @Override
            public void swap(int i, int j) {
                double temp = subject[i];
                subject[i] = subject[j];
                subject[j] = temp;
            }
        };

    }
}