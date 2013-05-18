package exercise.spec;

import exercise.Product;
import exercise.ProductSize;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 6:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class SizeSpec extends Spec {
    public SizeSpec(ProductSize size) {
        this.size = size;
    }

    public ProductSize getSize() {
        return size;
    }

    ProductSize size;

    @Override
    public boolean isSatisfiedBy(Product product) {
       return product.getSize().equals(size);
    }
}
