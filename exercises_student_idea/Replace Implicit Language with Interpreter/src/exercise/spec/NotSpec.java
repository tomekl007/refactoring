package exercise.spec;

import exercise.Product;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 5:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotSpec extends Spec {
    public NotSpec(Spec spec) {
        this.spec = spec;
    }

    public Spec getSpec() {
        return spec;
    }

    Spec spec;
    @Override
    public boolean isSatisfiedBy(Product product) {
       return !spec.isSatisfiedBy(product);
    }
}
