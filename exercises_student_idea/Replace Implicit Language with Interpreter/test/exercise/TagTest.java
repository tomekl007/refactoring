package exercise;

import org.junit.Assert;
import org.junit.Test;

public class TagTest {

    private String SAMPLE_PRICE = "8.95";

    @Test
    public void testSimpleTagWithOneAttributeAndValue() throws Exception {
        //given
        TagNode priceTag = new TagNode("price");
        priceTag.addAttribute("currency", "USD");
        priceTag.addValue(SAMPLE_PRICE);

        //when
        String result = priceTag.toString();

        //then
        String expected = "<price currency='USD'>"+SAMPLE_PRICE+"</price>";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testCompositeTagOneChild() throws Exception {
        //given
        TagNode productNode = new TagNode("product");
        productNode.add(new TagNode("price"));
        
        //when
        String result = productNode.toString();
        
        //then
        String expected = "<product><price></price></product>";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testAddingChildrenAndGrandchildren() throws Exception {
        //given
        TagNode ordersNode = new TagNode("orders");
        TagNode orderNode = new TagNode("order");
        TagNode productNode = new TagNode("product");
        ordersNode.add(orderNode);
        orderNode.add(productNode);
        
        //when
        String result = ordersNode.toString();
        
        //then
        String expected = "<orders><order><product></product></order></orders>";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testAddingManyChildren() throws Exception {
        //given
        TagNode orderNode = new TagNode("order");
        TagNode productNode = new TagNode("product");
        orderNode.add(productNode);
        orderNode.add(productNode);
        
        //when
        String actual = orderNode.toString();
        
        //then
        String expected = "<order><product></product><product></product></order>";
        Assert.assertEquals(expected, actual);
    }
}
