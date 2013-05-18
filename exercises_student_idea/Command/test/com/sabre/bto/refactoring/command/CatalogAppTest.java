package com.sabre.bto.refactoring.command;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CatalogAppTest
{   
   private BookManager manager;
   private CatalogApp app;
   
   private Book FIRST_BOOK;
   private Book SECOND_BOOK;
   
   @Before
   public void setUp()
   {
      manager = new BookManager();
      app = new CatalogApp(manager);
      
      FIRST_BOOK = new Book();
      FIRST_BOOK.setIsbn("1234567890123");
      FIRST_BOOK.setTitle("First Book");
      FIRST_BOOK.setPrice(new BigDecimal("39.99"));
      FIRST_BOOK.setAuthors(Arrays.asList("First Author","Second Author"));
      
      manager.addBook(FIRST_BOOK);
      
      SECOND_BOOK = new Book();
      SECOND_BOOK.setIsbn("0123456789012");
      SECOND_BOOK.setTitle("Second Book");
      SECOND_BOOK.setPrice(new BigDecimal("59.99"));
      SECOND_BOOK.setAuthors(Arrays.asList("Third Author","Forth Author"));
      
      // second book is not added initially to the manager
   }
   
   @After
   public void tearDown()
   {
      manager = null;
      app = null;
      FIRST_BOOK = null;
      SECOND_BOOK = null;
   }
   
   private void initialCheck()
   {
      Collection<Book> preBooks = manager.getAllBooks();
      Assert.assertEquals(1, preBooks.size());
      Assert.assertNotNull(manager.getBook(FIRST_BOOK.getIsbn()));
      Assert.assertNull(manager.getBook(SECOND_BOOK.getIsbn()));
   }
   
   @Test
   public void testAddNewBook()
   {
      initialCheck();
      
      // Call new-workshop (parameters passed as map)
      HandlerResponse response = app.executeActionAndGetResponse(CatalogApp.ACTION_ADD_BOOK, transformToParameters(SECOND_BOOK));
      
      // Check the answer : Workshops/Workshop/id|name|status|duration
      Assert.assertEquals(ResponseType.ADDED, response.getType());
      
      Map<String, Book> currentBooks = retrieveBooksFromXml(response.getContent());
      Assert.assertEquals(2, currentBooks.size());
      Book firstBook = currentBooks.get(FIRST_BOOK.getIsbn());
      Book secondBook = currentBooks.get(SECOND_BOOK.getIsbn());
      Assert.assertEquals(FIRST_BOOK, firstBook);
      Assert.assertEquals(SECOND_BOOK, secondBook);
      
      // Check the repository content using look-by-id or look-by-name
      Collection<Book> postBooks = manager.getAllBooks();
      Assert.assertEquals(2, postBooks.size());
      Assert.assertNotNull(manager.getBook(FIRST_BOOK.getIsbn()));
      Assert.assertNotNull(manager.getBook(SECOND_BOOK.getIsbn()));
   }

   @Test
   public void testDeleteBook()
   {
      initialCheck();
      
      manager.addBook(SECOND_BOOK);
      Assert.assertNotNull(manager.getBook(SECOND_BOOK.getIsbn()));
      
      // Call delete workshop (id/name parameter passed as map)
      HandlerResponse response = app.executeActionAndGetResponse(CatalogApp.ACTION_DELETE_BOOK, transformToIsbnParameter(FIRST_BOOK));

      // Check the answer: Workshops/Workshop/id|name|status|duration
      Assert.assertEquals(ResponseType.DELETED, response.getType());
      
      Map<String, Book> currentBooks = retrieveBooksFromXml(response.getContent());
      Assert.assertEquals(1, currentBooks.size());
      Book firstBook = currentBooks.get(FIRST_BOOK.getIsbn());
      Book secondBook = currentBooks.get(SECOND_BOOK.getIsbn());
      Assert.assertNull(firstBook);
      Assert.assertEquals(SECOND_BOOK, secondBook);
      
      // Check the repository content (empty or look-by-name -> empty)
      Collection<Book> postBooks = manager.getAllBooks();
      Assert.assertEquals(1, postBooks.size());
      Assert.assertNull(manager.getBook(FIRST_BOOK.getIsbn()));
      Assert.assertNotNull(manager.getBook(SECOND_BOOK.getIsbn()));
   }

   @Test
   public void testGetBook_OK()
   {
      initialCheck();
      
      // Call get-workshop (id/name parameter passed as map)
      HandlerResponse response = app.executeActionAndGetResponse(CatalogApp.ACTION_GET_BOOK_BY_ISBN, transformToIsbnParameter(FIRST_BOOK));
      
      // Check the answer: Workshops/Workshop/id|name|status|duration
      Assert.assertEquals(ResponseType.OK, response.getType());
      
      Map<String, Book> currentBooks = retrieveBooksFromXml(response.getContent());
      Assert.assertEquals(1, currentBooks.size());
      Book firstBook = currentBooks.get(FIRST_BOOK.getIsbn());
      Book secondBook = currentBooks.get(SECOND_BOOK.getIsbn());
      Assert.assertEquals(FIRST_BOOK, firstBook);
      Assert.assertNull(secondBook);
   }
   
   
   @Test
   public void testGetBook_NOT_FOUND()
   {
      initialCheck();
      
      // Call get-workshop (id/name parameter passed as map)
      HandlerResponse response = app.executeActionAndGetResponse(CatalogApp.ACTION_GET_BOOK_BY_ISBN, transformToIsbnParameter(SECOND_BOOK));
      
      // Check the answer: Workshops/Workshop/id|name|status|duration      
      Assert.assertEquals(ResponseType.NOT_FOUND, response.getType());
      
      Map<String, Book> currentBooks = retrieveBooksFromXml(response.getContent());
      Assert.assertEquals(0, currentBooks.size());
   }
   
   @Test
   public void testGetAllBooks()
   {
      initialCheck();
      manager.addBook(SECOND_BOOK);
      
      // Call get-all-workshops
      HandlerResponse response = app.executeActionAndGetResponse(CatalogApp.ACTION_GET_ALL_BOOKS, Collections.<String, String>emptyMap());
      
      // Check the answer: Workshops/Workshop/id|name|status|duration
      Assert.assertEquals(ResponseType.OK, response.getType());
      
      Map<String, Book> currentBooks = retrieveBooksFromXml(response.getContent());
      Assert.assertEquals(2, currentBooks.size());
      Book firstBook = currentBooks.get(FIRST_BOOK.getIsbn());
      Book secondBook = currentBooks.get(SECOND_BOOK.getIsbn());
      Assert.assertEquals(FIRST_BOOK, firstBook);
      Assert.assertEquals(SECOND_BOOK, secondBook);
   }
   
   private Map<String, String> transformToIsbnParameter(Book book)
   {      
      return Collections.singletonMap(CatalogApp.BOOK_ISBN, book.getIsbn());
   }
   
   private Map<String, String> transformToParameters(Book book)
   {
      Map<String, String> map = new HashMap<String, String>();
      
      map.put(CatalogApp.BOOK_ISBN, book.getIsbn());
      map.put(CatalogApp.BOOK_TITLE, book.getTitle());
      map.put(CatalogApp.BOOK_PRICE, book.getPrice().toString());
      map.put(CatalogApp.BOOK_AUTHORS, getCsv(book.getAuthors()));
      
      return map;
   }
   
   private String getCsv(List<String> authors)
   {
      StringBuilder result = new StringBuilder();
      
      Iterator<String> i = authors.iterator();
      if (i.hasNext())
      {
         result.append(i.next());
      }
      
      while (i.hasNext())
      {
         result.append(",");
         result.append(i.next());
      }
      
      return result.toString();
   }
   
   private Map<String, Book> retrieveBooksFromXml(String xml)
   {      
      List<Book> books = new BookParser().parse(xml);
      
      Map<String, Book> map = new HashMap<String, Book>();
      for (Book book : books)
      {
         map.put(book.getIsbn(), book);
      }
      
      return map;      
   }
}
