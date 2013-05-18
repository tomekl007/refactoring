package exercise;

public class TagNodeFactory implements NodeFactory {
    public TagNodeFactory() {
    }

    @Override
    public TagNode createNode(String name) {
        return new TagNode(name);
    }
}