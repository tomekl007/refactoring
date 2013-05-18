package com.sabre.bto.refactoring.command;

import com.sabre.bto.refactoring.command.actions.*;

import java.util.HashMap;
import java.util.Map;

/*
<?xml version="1.0" encoding="UTF-8"?>
<catalog>
    <book isbn=">23-34-42-3" lang="ENG">
        <title>Operating Systems</title>
        <price>400</price>
        <authors>
            <author>Ganesh Tiwari</author>
        </authors>
    </book>
    <book isbn="24-300-042-3">
        <title>Distributed Systems</title>
        <price>500</price>
        <authors>
            <author>Mahesh Poudel</author>
            <author>Bikram Adhikari</author>
            <author>Ramesh Poudel</author>
        </authors>
    </book>
</catalog>
*/

public class CatalogApp {
   public static final String ACTION_ADD_BOOK = "ADD_BOOK";
   public static final String ACTION_DELETE_BOOK = "DELETE_BOOK";
   public static final String ACTION_GET_BOOK_BY_ISBN = "GET_BOOK_BY_ISBN";
   public static final String ACTION_GET_ALL_BOOKS = "GET_ALL_BOOKS";

   public static final String BOOK_TITLE = "BOOK_TITLE";
   public static final String BOOK_ISBN  = "BOOK_ISBN";
   public static final String BOOK_PRICE = "BOOK_PRICE";
   public static final String BOOK_AUTHORS = "BOOK_AUTHORS";
    private Map<String, BookAction> actions = new HashMap<>();
    private XmlBooksCreator xmlCreator = new XmlBooksCreator();
   private BookManager workshopManager;
   
   CatalogApp(BookManager manager) {
      setWorkshopManager(manager);
       initMap();
   }

   public HandlerResponse executeActionAndGetResponse(String actionName, Map<String, String> parameters) {
       BookAction action = actions.get(actionName)          ;

       if (action != null)               {
           return action.execute(parameters)    ;
       }
//      if (actionName.equals(ACTION_ADD_BOOK)) {
//          return addBookCommand.execute(parameters);
//      }
//      else if (actionName.equals(ACTION_DELETE_BOOK)) {
//          return deleteBook.execute(parameters);
//      }
//      else if (actionName.equals(ACTION_GET_BOOK_BY_ISBN)) {
//          return
//                  findBook.execute(parameters);
//      }
//      else if (actionName.equals(ACTION_GET_ALL_BOOKS)) {
//          return findAllBooks.execute(parameters);
//      }
//
      throw new UnsupportedOperationException();
   }

    private HandlerResponse findAllBook() {
        return new FindAllBooks(this).execute(null);
    }

    private HandlerResponse findBook(Map<String, String> parameters) {
        return new FindBook(this).execute(parameters);
    }

    private HandlerResponse deleteBook(Map<String, String> parameters) {
        return new DeleteBook(this).execute(parameters);
    }

//    private HandlerResponse execute(Map<String, String> parameters) {
//        return addBookCommand.execute(parameters);
//    }
    public XmlBooksCreator getXmlCreator() {
        return xmlCreator;
    }

    public void setXmlCreator(XmlBooksCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    public BookManager getWorkshopManager() {
        return workshopManager;
    }

    public void setWorkshopManager(BookManager workshopManager) {
        this.workshopManager = workshopManager;
    }

    public void initMap () {

        actions.put(ACTION_ADD_BOOK, new AddBookCommand(this))  ;
        actions.put(ACTION_DELETE_BOOK, new DeleteBook(this))   ;
        actions.put(ACTION_GET_ALL_BOOKS, new FindAllBooks(this));
        actions.put(ACTION_GET_BOOK_BY_ISBN, new FindBook(this)) ;



    }
}