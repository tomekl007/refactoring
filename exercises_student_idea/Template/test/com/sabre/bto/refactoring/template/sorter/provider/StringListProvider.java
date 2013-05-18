package com.sabre.bto.refactoring.template.sorter.provider;

import java.util.Arrays;

import static junitparams.JUnitParamsRunner.$;

/**
 * Created with IntelliJ IDEA.
 * User: sg0620645
 * Date: 1/21/13
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringListProvider {
   public static Object[] provideComparableListsAndResults() {
      return $($(Arrays.<String>asList("John", "Anthony", "john", "1 Wayne" ), Arrays.<String>asList("1 Wayne", "Anthony", "John", "john")),
               $(Arrays.<String>asList("1", "01", "12", "2", "10", "9"), Arrays.<String>asList("01", "1", "10", "12", "2", "9")));
   /*The $(...) method is defined in JUnitParamsRunner class
    and is a shortcut for creating an array of Objects from
    method params. I use it for readability. It's definition looks like this:

    public static Object[] $(Object... params) { return params; }*/

   }


}
