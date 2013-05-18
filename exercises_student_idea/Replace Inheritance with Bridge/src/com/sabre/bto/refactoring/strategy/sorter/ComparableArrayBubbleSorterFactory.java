package com.sabre.bto.refactoring.strategy.sorter;

import com.sabre.bto.refactoring.strategy.sorter.handler.SortHandler;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 3:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class ComparableArrayBubbleSorterFactory<T extends Comparable<T>>
        implements SortHandlerFactory<T[]> {
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
