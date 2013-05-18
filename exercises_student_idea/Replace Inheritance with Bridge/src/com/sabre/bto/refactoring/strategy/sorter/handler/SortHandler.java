package com.sabre.bto.refactoring.strategy.sorter.handler;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 2:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SortHandler<S> {
    int getLength();

   int compare(int i, int j);


    void swap(int i, int j) ;

}
