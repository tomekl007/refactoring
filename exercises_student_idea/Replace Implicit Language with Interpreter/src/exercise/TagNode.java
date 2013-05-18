package exercise;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TagNode {

    private String name = "";
    private String value = "";
    private StringBuffer attributes;
    private List<TagNode> children;

    public TagNode(String name) {
        this.name = name;
        attributes = new StringBuffer();
        children = new ArrayList<TagNode>();
    }

    public void addAttribute(String attribute, String value) {
        attributes.append(" ");
        attributes.append(attribute);
        attributes.append("='");
        attributes.append(value);
        attributes.append("'");
    }

    public void addValue(String value) {
        this.value = value;
    }

    public void add(TagNode child) {
        children.add(child);
    }


    @Override
    public String toString() {
        String result = String.format("<%s%s>", name, attributes);
        Iterator it = children.iterator();
        while(it.hasNext()){
            TagNode node = (TagNode) it.next();
            result += node.toString();
        }
        result += String.format("%s</%s>", value, name);
        return result;
    }

}
