package com.sabre.bto.refactoring.strategy.sorter;


import com.sabre.bto.refactoring.strategy.sorter.handler.SortHandler;

public class BubbleSorter<S> implements Sorter<S>  {
    protected final SortHandlerFactory<S>
            factory;
    protected S subject;
    protected SortHandler<S> sortHandler = createSortHandler(subject);

     SortHandler<S> createSortHandler(S subject){
         return factory.createSortHandler(subject);
     }

    BubbleSorter(SortHandlerFactory<S> factory){
        this.factory = factory;
    }


   @Override
   public int sort(S subject) {
      this.subject = subject;
       int length = sortHandler.getLength();
      int operations = 0;
      if (length <= 1)
         return operations;

      for (int nextToLast = length - 2; nextToLast >= 0; nextToLast--)
         for (int index = 0; index <= nextToLast; index++) {
             if (sortHandler.compare(index, index + 1) > 0)
                 sortHandler.swap(index, index + 1);
            operations++;
         }

      return operations;
   }


}
