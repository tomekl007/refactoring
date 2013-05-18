package exercise;

/**
 * Created with IntelliJ IDEA.
 * User: Kudo
 * Date: 16.05.13
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public interface Node {
    @Override
    String toString();

    void setValue(String value);

    void addAttribiute(String key1, String value);

    void addChild(Node child1);
}
