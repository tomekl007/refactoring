package com.sabre.bto.refactoring.strategy.sorter.provider;


public class ArrayHolder<T> {
    private T[] array;

    public ArrayHolder(T[] array) {
        this.array = array;
    }

    public T[] getArray() {
        return array;
    }
}
