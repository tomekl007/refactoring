package exercise.spec;

import exercise.Product;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 6:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class OrSpec extends Spec {
    Spec first;

    public Spec getSenond() {
        return senond;
    }

    public OrSpec(Spec first, Spec senond) {
        this.first = first;
        this.senond = senond;
    }

    public Spec getFirst() {
        return first;
    }

    Spec senond;


    @Override
    public boolean isSatisfiedBy(Product product) {
        return first.isSatisfiedBy(product) || senond.isSatisfiedBy(product);
    }
}
