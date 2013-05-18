package exercise.spec;

import exercise.Product;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 5:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class BelowPriceSpec extends Spec {
    float price;

    public BelowPriceSpec(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public boolean isSatisfiedBy(Product product) {
        return product.getPrice() < getPrice();
    }
}
