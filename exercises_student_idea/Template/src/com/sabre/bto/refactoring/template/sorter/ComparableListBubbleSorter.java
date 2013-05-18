package com.sabre.bto.refactoring.template.sorter;
import java.util.List;

public class ComparableListBubbleSorter<T extends Comparable<T>> extends AbstractBubbleSorter<List<T>>{




    @Override
    protected int getLength(List<T> array) {
        return array.size();
    }

    @Override
    protected boolean shouldSwap(List<T> subject, int index) {
        return subject.get(index).compareTo(subject.get(index + 1)) > 0;
    }
    @Override
    protected void swap(List<T> subject, int index) {
        T temp = subject.get(index);
        subject.set(index, subject.get(index + 1));
        subject.set(index + 1,temp);
    }
}
