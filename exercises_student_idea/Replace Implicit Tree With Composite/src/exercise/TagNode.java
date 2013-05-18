package exercise;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kudo
 * Date: 16.05.13
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */
public class TagNode implements Node {
    private String name;
    private String value = "";
    private List<Node> nodes = new LinkedList<>() ;
    private Map<String, String> attributes = new LinkedHashMap<>() ;
    public TagNode(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        String result = String.format("<%s%s>%s%s</%s>", name,

                                                        getAttributesAsText(),
                                                        getChildrenAsText(),
                                                        value,
                                                        name)  ;

        return result;    //To change body of overridden methods use File | Settings | File Templates.
    }

    private String getChildrenAsText() {

        StringBuilder sb = new StringBuilder();

        for (Node child : nodes)     {
            sb.append(child.toString());
        }

        return sb.toString();
    }

    private String getAttributesAsText() {

        StringBuilder builder = new StringBuilder()      ;

         for (Map.Entry<String, String> entry : attributes.entrySet())
         {
             builder.append(" ");
             builder.append(entry.getKey())                 ;
             builder.append("='");
             builder.append(entry.getValue());
             builder.append("'")              ;
         }
     return builder.toString();

    }

    @Override
    public void setValue(String value) {
          this.value = value;
        //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public void addAttribiute(String key1, String value) {
        attributes.put(key1, value)     ;

    }

    @Override
    public void addChild(Node child1) {

        nodes.add(child1)     ;


    }
}
