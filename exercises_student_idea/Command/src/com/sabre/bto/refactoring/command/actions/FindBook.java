package com.sabre.bto.refactoring.command.actions;

import com.sabre.bto.refactoring.command.Book;
import com.sabre.bto.refactoring.command.CatalogApp;
import com.sabre.bto.refactoring.command.HandlerResponse;
import com.sabre.bto.refactoring.command.ResponseType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class FindBook implements BookAction {
    private final CatalogApp catalogApp;

    public FindBook(CatalogApp catalogApp) {
        this.catalogApp = catalogApp;
    }

    public HandlerResponse execute(Map<String, String> parameters) {
        Book book = catalogApp.getWorkshopManager().getBook(parameters.get(CatalogApp.BOOK_ISBN));
        if (book == null) {
            return new HandlerResponse(catalogApp.getXmlCreator().getAsXml(Collections.<Book>emptyList()), ResponseType.NOT_FOUND);
        } else {
            return new HandlerResponse(catalogApp.getXmlCreator().getAsXml(Arrays.asList(book)), ResponseType.OK);
        }
    }
}