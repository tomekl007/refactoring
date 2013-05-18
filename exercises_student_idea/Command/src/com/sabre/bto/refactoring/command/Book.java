package com.sabre.bto.refactoring.command;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Book class stores book information, after parsing the xml
 */
public class Book {
   String isbn;
   String title;
   BigDecimal price;
   List<String> authors;

   public Book() {
      authors = new ArrayList<String>();
   }

   public String getIsbn() {
      return isbn;
   }

   public void setIsbn(String isbn) {
      this.isbn = isbn;
   }
   
   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }
   
   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public List<String> getAuthors() {
      return authors;
   }

   public void setAuthors(List<String> authors) {
      this.authors = authors;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((authors == null) ? 0 : authors.hashCode());
      result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
      result = prime * result + ((price == null) ? 0 : price.hashCode());
      result = prime * result + ((title == null) ? 0 : title.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Book other = (Book) obj;
      if (authors == null) {
         if (other.authors != null)
            return false;
      } else if (!authors.equals(other.authors))
         return false;
      if (isbn == null) {
         if (other.isbn != null)
            return false;
      } else if (!isbn.equals(other.isbn))
         return false;
      if (price == null) {
         if (other.price != null)
            return false;
      } else if (!price.equals(other.price))
         return false;
      if (title == null) {
         if (other.title != null)
            return false;
      } else if (!title.equals(other.title))
         return false;
      return true;
   }

   
}
