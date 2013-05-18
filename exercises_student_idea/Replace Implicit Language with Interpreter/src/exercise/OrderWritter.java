package exercise;

import exercise.exceptions.UnsupportedColorException;
import exercise.exceptions.UnsupportedSizeException;

import java.awt.*;

public class OrderWritter {

    private Orders orders;

    public OrderWritter(Orders orders) {
        this.orders = orders;
    }

    /*
   * Replace implicit tree with Composite
   * */
    public String getContents() throws UnsupportedSizeException, UnsupportedColorException {
        StringBuffer xml = new StringBuffer();
        writeOrdersTo(xml);
        return xml.toString();
    }

    private void writeOrdersTo(StringBuffer xml) throws UnsupportedColorException, UnsupportedSizeException {
        TagNode ordersNode = new TagNode("orders");
        for(int i = 0; i < orders.getOrderCount(); i++){
            Order order = orders.getOrder(i);
            writeOrderTo(ordersNode, order);
        }

        xml.append(ordersNode.toString());
    }

    private void writeOrderTo(TagNode parent, Order order) throws UnsupportedColorException, UnsupportedSizeException {
        TagNode orderNode = new TagNode("order");
        orderNode.addAttribute("id", String.valueOf(order.getOrderId()));
        for(int j=0; j < order.getProductCount(); j++){
            Product product = order.getProduct(j);
            writeProductTo(orderNode, product);
        }
        parent.add(orderNode);
    }

    private void writeProductTo(TagNode parent, Product product) throws UnsupportedColorException, UnsupportedSizeException {
        TagNode productNode = new TagNode("product");
        productNode.addAttribute("id", String.valueOf(product.getID()) );
        productNode.addAttribute("color", getColorFor(product) );

        if(product.getSize() != ProductSize.NOT_APPLICABLE){
            productNode.addAttribute("size", getSizeFor(product));
        }
        writePriceTo(productNode, product);
        productNode.addValue(product.getName());

        parent.add(productNode);
    }

    private void writePriceTo(TagNode parent, Product product) {
        TagNode priceNode = new TagNode("price");
        priceNode.addAttribute("currency", getCurrencyFor(product));
        priceNode.addValue(priceFor(product));
        parent.add(priceNode);
    }

    private String priceFor(Product product){
        return String.valueOf(product.getPrice());
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
