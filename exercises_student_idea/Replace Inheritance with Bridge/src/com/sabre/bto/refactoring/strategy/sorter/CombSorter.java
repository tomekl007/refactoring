package com.sabre.bto.refactoring.strategy.sorter;


public abstract class CombSorter<S> implements Sorter<S> {
    protected S subject;

    public int sort(S subject) {
        this.subject = subject;
        int length = getLength();
        int operations = 0;
        if (length <= 1)
            return operations;

        int gap = length;
        boolean swapped = true;

        while (gap > 1 || swapped) {
            if (gap > 1) {
                gap = (int) (gap / 1.3);
            }
            swapped = false;
            for (int i = 0; i + gap < length; i++) {
                if (compare(i, i + gap) > 0) {
                    swap(i, i + gap);
                    swapped = true;
                }
                operations++;
            }
        }

        return operations;
    }

    protected  abstract int getLength();

    protected abstract int compare(int i, int j);

    protected abstract void swap(int i, int j);
}
