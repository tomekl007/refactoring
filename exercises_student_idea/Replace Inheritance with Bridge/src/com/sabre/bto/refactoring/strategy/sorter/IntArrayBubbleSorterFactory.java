package com.sabre.bto.refactoring.strategy.sorter;

import com.sabre.bto.refactoring.strategy.sorter.handler.SortHandler;

public class IntArrayBubbleSorterFactory implements SortHandlerFactory<int[]> {
    public IntArrayBubbleSorterFactory() {
    }

    @Override
    public SortHandler<int[]> createSortHandler(final int[] subject) {
        return new SortHandler<int[]>() {
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
                int temp = subject[i];
                subject[i] = subject[j];
                subject[j] = temp;
            }
        };

    }
}