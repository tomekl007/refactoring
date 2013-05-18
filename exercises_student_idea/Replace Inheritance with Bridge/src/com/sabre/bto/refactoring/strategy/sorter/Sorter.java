package com.sabre.bto.refactoring.strategy.sorter;

/**
 * Created with IntelliJ IDEA.
 * User: sg0620645
 * Date: 10/18/12
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Sorter<S> {
   public int sort(S subject);
}
