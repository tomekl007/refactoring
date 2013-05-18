package exercise;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Kudo
 * Date: 16.05.13
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class TagNodeTest {
    @Test
    public void testEmptyTag() throws Exception {
       //Given
      Node node = new TagNode("name")  ;

        //When


        String xml = node.toString();

        // Then

        Assert.assertEquals("<name></name>", xml);

    }

    @Test
    public void testTagWithValue() throws Exception {

        //Given
        Node node = new TagNode("name")  ;

        //When

        node.setValue("value")    ;
        String xml = node.toString();

        // Then

        Assert.assertEquals("<name>value</name>", xml);

    }

    @Test
    public void testTagWithAttrib() throws Exception {

        //Given
        Node node = new TagNode("name")  ;

        //When

        node.addAttribiute("key1", "value")    ;
        String xml = node.toString();

        // Then

        Assert.assertEquals("<name key1='value'></name>", xml);

    }

    @Test
    public void testNestedTag() throws Exception {

        //Given
        Node parent = new TagNode("parent")  ;
        Node child1 = new TagNode("child1")  ;
        Node child2 = new TagNode("child2")  ;


        //When

        parent.addChild(child1)     ;
        parent.addChild(child2);

       // node.addAttribiute("key1", "value")    ;
        String xml = parent.toString();

        // Then

        Assert.assertEquals("<parent><child1></child1><child2></child2></parent>", xml);

    }
}
