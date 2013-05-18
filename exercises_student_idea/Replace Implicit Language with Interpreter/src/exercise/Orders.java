package exercise;

import java.util.LinkedList;
import java.util.List;

public class Orders {

    List<Order> orders;

    public Orders() {
        orders = new LinkedList<Order>();
    }

    public void add(Order order){
        orders.add(order);
    }
    
    public int getOrderCount() {
        return orders.size();
    }

    public Order getOrder(int i) {
        return orders.get(i);
    }
}
