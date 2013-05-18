package com.sabre.bto.refactoring.command;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookParser extends DefaultHandler {
   List<Book> bookL;
   String tmpValue;
   Book bookTmp;

   public BookParser() {
      bookL = new ArrayList<Book>();
   }

   public List<Book> parse(String xml) {
      // parse
      SAXParserFactory factory = SAXParserFactory.newInstance();
      try {
         SAXParser parser = factory.newSAXParser();
         parser.parse(new InputSource(new StringReader(xml)), this);
      } catch (ParserConfigurationException e) {
         System.out.println("ParserConfig error");
      } catch (SAXException e) {
         System.out.println("SAXException : xml not well formed");
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("IO error");
      }
      
      return bookL;
   }

   @Override
   public void startElement(String s, String s1, String elementName,
         Attributes attributes) throws SAXException {
      // if current element is book , create new book
      // clear tmpValue on start of element

      if (elementName.equalsIgnoreCase("book")) {
         bookTmp = new Book();
         bookTmp.setIsbn(attributes.getValue("isbn"));
      }
   }

   private List<Book> getBooks() {
      return bookL;
   }

   @Override
   public void endElement(String s, String s1, String element)
         throws SAXException {
      // if end of book element add to list
      if (element.equals("book")) {
         bookL.add(bookTmp);
      }
      if (element.equalsIgnoreCase("title")) {
         bookTmp.setTitle(tmpValue);
      }
      if (element.equalsIgnoreCase("author")) {
         bookTmp.getAuthors().add(tmpValue);
      }
      if (element.equalsIgnoreCase("price")) {
         bookTmp.setPrice(new BigDecimal(tmpValue));
      }
   }

   @Override
   public void characters(char[] ac, int i, int j) throws SAXException {
      tmpValue = new String(ac, i, j);
   }
}
