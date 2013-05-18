package exercise;

import exercise.exceptions.UnsupportedColorException;
import exercise.exceptions.UnsupportedSizeException;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.core.Is.is;

public class OrderWritterTest {

    @Test
    public void writeEmptyOrderQueue() throws Exception, UnsupportedSizeException {
        //given
        Orders orders = new Orders();
        final Orders orders1 = orders;
        OrderWritter orderWritter = new OrderWritter(new TagNodeFactory(), orders1);

        //when
        String xmlOrder = orderWritter.writeOrders();
        
        //then
        Assert.assertThat(xmlOrder, is("<orders></orders>"));
    }

    @Test
    public void writeOrderQueueWithOneEmptyOrder() throws UnsupportedColorException, UnsupportedSizeException {
        //given
        Order order = new Order(321);
        Orders orders = new Orders();
        orders.add(order);
        final Orders orders1 = orders;
        OrderWritter orderWritter = new OrderWritter(new TagNodeFactory(), orders1);

        //when
        String xmlOrder = orderWritter.writeOrders();

        //then
        Assert.assertThat(xmlOrder, is("<orders><order id='321'></order></orders>"));
    }

    @Test
    public void writeOrderQueueWithOneOrderContainingFireTruck() throws UnsupportedColorException, UnsupportedSizeException {
        //given
        Product product = new Product("Fire Truck", ProductSize.MEDIUM, 8.95f, 1234, Color.RED );
        Order order = new Order(321);
        order.addProduct(product);
        Orders orders = new Orders();
        orders.add(order);
        final Orders orders1 = orders;
        OrderWritter orderWritter = new OrderWritter(new TagNodeFactory(), orders1);

        //when
        String xmlOrder = orderWritter.writeOrders();

        //then
        Assert.assertThat(xmlOrder, is("<orders><order id='321'><product id='1234' color='red' size='medium'><price currency='USD'>8.95</price>Fire Truck</product></order></orders>"));
    }

    @Test
    public void writeOrderQueueWithOneOrderContainingFireTruckAndToyPorsche() throws UnsupportedColorException, UnsupportedSizeException {
        //given
        Product fireTruck = new Product("Fire Truck", ProductSize.MEDIUM, 8.95f, 1234, Color.RED );
        Product toyProsche = new Product("Toy Proshe Convertible", ProductSize.NOT_APPLICABLE, 230.0f, 1112, Color.RED );

        Order order = new Order(321);
        order.addProduct(fireTruck);
        order.addProduct(toyProsche);
        Orders orders = new Orders();
        orders.add(order);
        final Orders orders1 = orders;
        OrderWritter orderWritter = new OrderWritter(new TagNodeFactory(), orders1);

        //when
        String xmlOrder = orderWritter.writeOrders();

        //then
        Assert.assertThat(xmlOrder, is(
                "<orders>" +
                        "<order id='321'>" +
                        "<product id='1234' color='red' size='medium'>" +
                        "<price currency='USD'>8.95</price>" +
                        "Fire Truck" +
                        "</product>" +
                        "<product id='1112' color='red'>" +
                        "<price currency='USD'>230.0</price>" +
                        "Toy Proshe Convertible" +
                        "</product>" +
                        "</order>" +
                        "</orders>"));
    }


}
