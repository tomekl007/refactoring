package exercise.spec;

import exercise.Product;

/**
 * Created with IntelliJ IDEA.
 * User: Tomek
 * Date: 5/18/13
 * Time: 5:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class AndSpec extends Spec {
    public Spec getFirst() {
        return first;
    }

    Spec first;

    public Spec getSecond() {
        return second;
    }

    public AndSpec(Spec first, Spec second) {
        this.first = first;
        this.second = second;
    }

    Spec second;
    @Override
    public boolean isSatisfiedBy(Product product) {
        return first.isSatisfiedBy(product) && second.isSatisfiedBy(product);
    }
}
