package exercise;

import exercise.spec.*;

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

    public List<Product> belowPriceAvoidingAColor(float priceF, Color color){
        BelowPriceSpec price = new BelowPriceSpec(priceF);
        NotSpec not = new NotSpec(new ColorSpec(color));

        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if(price.isSatisfiedBy(product) && not.isSatisfiedBy(product))
                foundProducts.add(product);
        }
        return foundProducts;
    }

    public List<Product> byColor( Color colorF ){

        ColorSpec color = new ColorSpec(colorF);
        //ColorSpec colorSpec = new ColorSpec(color);
        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if(color.isSatisfiedBy(product))
                foundProducts.add(product);
        }
        return foundProducts;
    }


    public List<Product> byColorAndBelowPrice(Color colorF, float priceF ){

        ColorSpec color = new ColorSpec(colorF);
        BelowPriceSpec price = new BelowPriceSpec(priceF);
        AndSpec andSpec = new AndSpec(color,price);

        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if(andSpec.isSatisfiedBy(product))
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

    public List<Product> byColorSizeAndBelowPrice(Color color, ProductSize size, float priceF){
        BelowPriceSpec price = new BelowPriceSpec(priceF);
        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if (    price.isSatisfiedBy(product) &&
                    product.size.equals(size) &&
                    product.getColor().equals(color))
                foundProducts.add(product);
        }
        return foundProducts;
    }

    public List<Product> byPrice(float price) {
        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if ( product.getPrice() == price){
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }


    public List<Product> byColorAndBelowPriceOrByColorAndSize(Color colorF, float priceF, Color colorF2, ProductSize sizeF) {

        ColorSpec color = new ColorSpec(colorF);
        BelowPriceSpec price = new BelowPriceSpec(priceF);
        ColorSpec colorSecond = new ColorSpec(colorF2);
        SizeSpec size = new SizeSpec(sizeF);
        AndSpec andFirst = new AndSpec(color,price);
        AndSpec andSecond = new AndSpec(colorSecond,size);
        OrSpec or = new OrSpec(andFirst,andSecond);


        List<Product> foundProducts = new ArrayList<Product>();
        Iterator<Product> products = repository.iterator();
        while( products.hasNext() ){
            Product product = products.next();
            if ( or.isSatisfiedBy(product)){
                foundProducts.add(product);
            }
        }
        return foundProducts;

    }
}
