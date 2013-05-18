package com.sabre.bto.refactoring.strategy.sorter;

public class DoubleArraySelectionSorter extends SelectionSorter<double[]> {

   @Override
   protected int getLength() {
      return subject.length;
   }

   @Override
   protected int compare(int i, int j) {
      return subject[i] == subject[j] ? 0 : subject[i] < subject[j] ? -1 : 1 ;
   }

   @Override
   protected void swap(int i, int j) {
      double temp = subject[i];
      subject[i] = subject[j];
      subject[j] = temp;
   }
}
