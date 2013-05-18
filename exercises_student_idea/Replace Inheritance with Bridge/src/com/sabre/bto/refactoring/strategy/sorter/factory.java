package com.sabre.bto.refactoring.strategy.sorter;

import com.sabre.bto.refactoring.strategy.sorter.handler.SortHandler;

public class factory<T extends Comparable<T>> implements SortHandlerFactory<T[]> {
    public factory() {
    }

    @Override
    public SortHandler<T[]> createSortHandler(final T[] subject) {
        return new SortHandler<T[]>() {
            @Override
            public int getLength() {
                return subject.length;
            }

            @Override
            public int compare(int i, int j) {
                return subject[i].compareTo(subject[j]);
            }

            @Override
            public void swap(int i, int j) {
                T temp = subject[i];
                subject[i] = subject[j];
                subject[j] = temp;
            }
        };

    }
}