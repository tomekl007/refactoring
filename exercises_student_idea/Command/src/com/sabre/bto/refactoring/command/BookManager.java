package com.sabre.bto.refactoring.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManager {

   private Map<String, Book> books = new HashMap<String, Book>();
   
   public BookManager()
   {
      
   }
   
   // Constractor for testing purposes...
   public BookManager(List<Book> _books)
   {
      for (Book book : _books)
      {
         books.put(book.getIsbn(), book);
      }
   }

   public Book getBook(String isbn) {
      return books.get(isbn);
   }

   public List<Book> getAllBooks() {
      return new ArrayList<Book>(books.values());
   }

   public void addBook(Book book) {
      books.put(book.getIsbn(), book);
   }

   public void removeBook(String isbn) {
      books.remove(isbn);
   }
}
