package exercise;

import exercise.exceptions.UnsupportedColorException;
import exercise.exceptions.UnsupportedSizeException;

import java.awt.*;

public  class OrderWritter {

    private NodeFactory NodeFactory;
    private Orders orders;

    public OrderWritter(Orders orders) {
        this(new TagNodeFactory(), orders)  ;
    }

    public OrderWritter(NodeFactory nodeFactory, Orders orders) {
        this.orders = orders;
        this.NodeFactory = nodeFactory;
    }

    /*
   * Replace implicit tree with Composite
   * */
    public String writeOrders() throws UnsupportedSizeException, UnsupportedColorException {
        //StringBuffer xml = new StringBuffer();
        //xml.append("<orders>");
        String name = "orders";
        Node ordersNode = createNode(name);

        for(int i = 0; i < orders.getOrderCount(); i++){
            Order order = orders.getOrder(i);
            //writeOrder(ordersNode, order);
            Node on = createOrderNode(order);
            ordersNode.addChild(on);
        }
       // xml.append("</orders>");

        return ordersNode.toString();
    }

    protected TagNode createNode(String name) {
        return NodeFactory.createNode(name);
    }


    private void writeOrder(Node tagNode, Order order) throws UnsupportedColorException, UnsupportedSizeException {
      //  TagNode orderNode = new TagNode("order");
        Node orderNode = createOrderNode(order);
        tagNode.addChild(orderNode);
    }

    private Node createOrderNode(Order order) throws UnsupportedColorException, UnsupportedSizeException {
        Node orderNode = createNode("order");

        orderNode.addAttribiute("id",String.valueOf(order.getOrderId()));

        //xml.append("<order id='");
        //xml.append(order.getOrderId());
        //xml.append("'>");
        for(int j=0; j < order.getProductCount(); j++){
            Product product =  order.getProduct(j);
            writeProduct(orderNode, product);
        }
        return  orderNode;
    }

    private void writeProduct(Node orderNode, Product product) throws UnsupportedColorException, UnsupportedSizeException {
         Node tag = createNode("product");
        tag.addAttribiute("id",String.valueOf(product.getID()));
        tag.addAttribiute("color", getColorFor(product));
        tag.setValue(product.getName());

        if(product.getSize() != ProductSize.NOT_APPLICABLE){
           tag.addAttribiute("size",getSizeFor(product));
//            xml.append(" size='");
//            xml.append(getSizeFor(product));
//            xml.append("'");
        }
       // xml.append(">");

        Node priceNode = createNode("price");
        priceNode.addAttribiute("currency", getCurrencyFor(product));
        priceNode.setValue(String.valueOf(product.getPrice()));


        tag.addChild(priceNode);
        orderNode.addChild(tag);
       // xml.append("</product>");
        orderNode.addChild(tag);
    }

    private String getCurrencyFor(Product product) {
        return "USD";
    }

    private String getSizeFor(Product product) throws UnsupportedSizeException {
        ProductSize productSize = product.getSize();
        switch (productSize){
            case SMALL:
                return "small";
            case MEDIUM:
                return "medium";
            case LARGE:
                return "large";
        }
        throw new UnsupportedSizeException();
    }

    private String getColorFor(Product product) throws UnsupportedColorException {
        Color color = product.getColor();
        if( color.equals(Color.red))
            return "red";
        if( color.equals(Color.green))
            return "green";
        if( color.equals(Color.blue))
            return "blue";

        throw new UnsupportedColorException();
    }

}
