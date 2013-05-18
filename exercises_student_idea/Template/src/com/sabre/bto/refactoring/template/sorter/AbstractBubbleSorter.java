package com.sabre.bto.refactoring.template.sorter;

/**
 * Created with IntelliJ IDEA.
 * User: Kudo
 * Date: 16.05.13
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractBubbleSorter<S> implements Sorter<S> {
    public final int sort(S array) {
        int operations = 0;
        int length = getLength(array);
        if (length<=0)
            return operations;

        for (int nextToLast = length - 2; nextToLast >= 0; nextToLast--)
            for (int index = 0; index <= nextToLast; index++) {
               if (shouldSwap(array, index)) {
                   swap(array, index);
                }
                operations++;
            }

        return operations;
    }

    protected abstract int getLength(S array);

    protected abstract boolean shouldSwap(S array, int index);

    protected abstract void swap(S array, int index);
}
