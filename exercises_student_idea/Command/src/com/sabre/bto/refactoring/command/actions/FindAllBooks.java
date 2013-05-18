package com.sabre.bto.refactoring.command.actions;

import com.sabre.bto.refactoring.command.CatalogApp;
import com.sabre.bto.refactoring.command.HandlerResponse;
import com.sabre.bto.refactoring.command.ResponseType;

import java.util.Map;

public class FindAllBooks implements BookAction {
    private final CatalogApp catalogApp;

    public FindAllBooks(CatalogApp catalogApp) {
        this.catalogApp = catalogApp;
    }

   public HandlerResponse execute(Map<String, String> parameters) {
        return new HandlerResponse(catalogApp.getXmlCreator().getAsXml(catalogApp.getWorkshopManager().getAllBooks()), ResponseType.OK);
    }
}