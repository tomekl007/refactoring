package com.sabre.bto.refactoring.command.actions;

import com.sabre.bto.refactoring.command.Book;
import com.sabre.bto.refactoring.command.CatalogApp;
import com.sabre.bto.refactoring.command.HandlerResponse;
import com.sabre.bto.refactoring.command.ResponseType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class AddBookCommand implements BookAction {
    private final CatalogApp catalogApp;

    public AddBookCommand(CatalogApp catalogApp) {
        this.catalogApp = catalogApp;
    }

    @Override
    public HandlerResponse execute(Map<String, String> parameters) {
        Book book = getBookFromParameters(parameters);
        catalogApp.getWorkshopManager().addBook(book);
        return new HandlerResponse(catalogApp.getXmlCreator().getAsXml(catalogApp.getWorkshopManager().getAllBooks()), ResponseType.ADDED);
    }

    private Book getBookFromParameters(Map<String, String> parameters) {
      Book book = new Book();

      book.setIsbn(parameters.get(CatalogApp.BOOK_ISBN));
      book.setTitle(parameters.get(CatalogApp.BOOK_TITLE));
      book.setPrice(new BigDecimal(parameters.get(CatalogApp.BOOK_PRICE)));

      String[] authors = parameters.get(CatalogApp.BOOK_AUTHORS).split(",");
      book.setAuthors(Arrays.asList(authors));

      return book;
   }
}