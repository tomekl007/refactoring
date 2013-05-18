package exercise;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
* Tę klasę przerobimy wprowadzając Interpreter
*/

public class ProductFinder {
    private List<Product> repository;

    public ProductFinder(List<Product> repository) {
        this.repository = repository;
    }

    public List<Product> belowPriceAvoidingAColor(float price, Color color){
        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if(product.getPrice() < price && product.getColor() != color)
                foundProducts.add(product);
        }
        return foundProducts;
    }

    public List<Product> byColor( Color color ){
        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if(product.getColor().equals(color))
                foundProducts.add(product);
        }
        return foundProducts;
    }

    public List<Product> byColorAndBelowPrice(Color color, float price ){
        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if(product.getPrice() < price && product.getColor().equals(color))
                foundProducts.add(product);
        }
        return foundProducts;
    }

    public List<Product> byColorAndAbovePrice(Color color, float price){
        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if(product.getPrice() > price && product.getColor().equals(color))
                foundProducts.add(product);
        }
        return foundProducts;
    }

    public List<Product> byColorSizeAndBelowPrice(Color color, ProductSize size, float price){
        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if (    product.getPrice() < price &&
                    product.size.equals(size) &&
                    product.getColor().equals(color))
                foundProducts.add(product);
        }
        return foundProducts;
    }
}
