package com.sabre.bto.refactoring.strategy.sorter;


import javax.security.auth.Subject;

public abstract class SelectionSorter<S> implements Sorter<S>{
    protected S subject;

    public int sort(S subject ) {
        this.subject = subject;
        int length = getLength();
        int operations = 0;

        if (length <= 1)
            return operations;

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (compare(i, j) > 0) {
                    swap(i, j);
                }
                operations++;
            }
        }

        return operations;
    }

    protected abstract int getLength();

    protected abstract int compare(int i, int j);

    protected abstract void swap(int i, int j);
}
