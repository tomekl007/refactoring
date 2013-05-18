package com.sabre.bto.refactoring.strategy.sorter;

import com.sabre.bto.refactoring.strategy.sorter.handler.SortHandler;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 3:19 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SortHandlerFactory<S> {
    SortHandler<S> createSortHandler(S subject);


}
