package exercise;

import java.util.LinkedList;
import java.util.List;

public class Order {
    final int id;
    List<Product> products = new LinkedList<Product>();

    public Order(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return id;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public int getProductCount() {
        return products.size();
    }

    public Product getProduct(int index) {
        return products.get(index);
    }
}
