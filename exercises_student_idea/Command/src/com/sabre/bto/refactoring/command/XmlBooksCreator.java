package com.sabre.bto.refactoring.command;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlBooksCreator {
   public String getAsXml(List<Book> books)
   {
      try {
         DocumentBuilderFactory docFactory = DocumentBuilderFactory
               .newInstance();
         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

         // root elements
         Document doc = docBuilder.newDocument();
         Element catalog = doc.createElement("catalog");
         doc.appendChild(catalog);
         
         for (Book item : books)
         {
            Element book = doc.createElement("book");
            catalog.appendChild(book);
            
            Attr isbn = doc.createAttribute("isbn");
            isbn.setValue(item.getIsbn());
            book.setAttributeNode(isbn);
            
            // firstname elements
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(item.getTitle()));
            book.appendChild(title);

            // lastname elements
            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(item.getPrice().toString()));
            book.appendChild(price);
            
            Element authors = doc.createElement("authors");
            for (String author : item.getAuthors())
            {
               Element author_1 = doc.createElement("author");
               author_1.appendChild(doc.createTextNode(author));
               authors.appendChild(author_1);
            }
            
            book.appendChild(authors);
         }
         
         // write the content into xml file
         TransformerFactory transformerFactory = TransformerFactory
               .newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         
         ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
         
         StreamResult result = new StreamResult(outputBuffer);
         transformer.transform(source, result);

         return outputBuffer.toString();

      } catch (ParserConfigurationException pce) {
         pce.printStackTrace();
         return null;
      } catch (TransformerException tfe) {
         tfe.printStackTrace();
         return null;
      }
   }
}
