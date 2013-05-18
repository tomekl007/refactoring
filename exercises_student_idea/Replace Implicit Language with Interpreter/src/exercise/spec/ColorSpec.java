package exercise.spec;

import exercise.Product;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 5:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class ColorSpec extends Spec {
    public Color getColor() {
        return color;
    }

    public ColorSpec(Color color) {
        this.color = color;
    }

    Color color;

    public boolean isSatisfiedBy(Product product) {
        return product.getColor().equals(getColor());
    }


}
