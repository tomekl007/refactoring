package exercise;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ProductFinderTest {
    private ProductFinder finder;

    private Product fireTruck = new Product("Fire Truck", ProductSize.MEDIUM, 8.95f, 1234, Color.red);
    private Product barbieClassic = new Product("Barbie Classic", ProductSize.SMALL, 15.95f, 7654, Color.yellow);
    private Product frisbee = new Product("Frisbee", ProductSize.LARGE, 9.99f, 4321, Color.pink);
    private Product baseball = new Product("Baseball", ProductSize.NOT_APPLICABLE, 8.95f, 2343, Color.white );
    private Product toyConvertible = new Product("Toy Prosche Convertible", ProductSize.NOT_APPLICABLE, 230f,1112, Color.red);


    private Product blueBall = new Product("blue adidas Ball", ProductSize.NOT_APPLICABLE, 8.95f, 1234, Color.blue);
    private Product redBall = new Product("red nike ball", ProductSize.SMALL, 125.95f, 7654, Color.red);
    @Before
    public void setUp() throws Exception {
        finder = new ProductFinder(createProductRepository());
    }

    private List<Product> createProductRepository() {
        return Arrays.asList(fireTruck, barbieClassic, frisbee, baseball, toyConvertible, blueBall,redBall);
    }

    @Test
    public void testFindByColor() throws Exception {
        //when
        List<Product> actual = finder.byColor(Color.red);

        //then
        assertEquals("found 2 products",2, actual.size());
        assertTrue(actual.contains(fireTruck));
        assertTrue(actual.contains(toyConvertible));
    }

    @Test
    public void testFindByPrice() throws Exception {
        //given
        float price = 8.95f;

        //when
        List<Product> actual = finder.byPrice(price);

        //then
        assertEquals(2, actual.size());
        Iterator<Product> it = actual.iterator();
        while(it.hasNext()){
            Product product = it.next();
            assertEquals(price, product.getPrice());
        }
        
    }


    @Test
    public void testFindByColorAndBelowPrice() {
        List foundProducts =
                finder.byColorAndBelowPrice(Color.red, 10.00f);
        assertEquals(
                "found 1 small red products below $10.00",
                1,
                foundProducts.size());

        foundProducts =
                finder.byColorAndBelowPrice(Color.red, 10.00f);
        assertEquals(
                "found firetruck when looking for cheap medium red toys",
                fireTruck,
                foundProducts.get(0));
    }

    @Test
    public void testFindBelowPriceAvoidingAColor() {
        List foundProducts =
                finder.belowPriceAvoidingAColor(9.00f, Color.white);
        assertEquals(
                "found 1 non-white product < $9.00",
                1,
                foundProducts.size());
        assertTrue("found fireTruck", foundProducts.contains(fireTruck));

        foundProducts = finder.belowPriceAvoidingAColor(9.00f, Color.red);
        assertEquals(
                "found 1 non-red product < $9.00",
                1,
                foundProducts.size());
        assertTrue("found baseball", foundProducts.contains(baseball));
    }

   @Test
   public void testFindByColorAndAbovePrice() {
      List foundProducts = finder.byColorAndAbovePrice(Color.red, 9.00f);
      assertEquals("found 1 red product > $9.00", 1, foundProducts.size());
      assertTrue("found toyConvertible",
         foundProducts.contains(toyConvertible));

      foundProducts = finder.byColorAndAbovePrice(Color.red, 1.00f);
      assertEquals("found 2 red products > $1.00", 2, foundProducts.size());
      assertTrue("found toyConvertible",
         foundProducts.contains(toyConvertible));
      assertTrue("found fireTruck", foundProducts.contains(fireTruck));

      foundProducts = finder.byColorAndAbovePrice(Color.red, 230.00f);
      assertEquals("found 0 red products > $230.00", 0, foundProducts.size());
   }

   @Test
   public void testFindByColorSizeAndBelowPrice() {
      List foundProducts = finder.byColorSizeAndBelowPrice(Color.pink,
         ProductSize.LARGE, 10f);
      assertEquals("found 1 large pink product < $10", 1,
         foundProducts.size());
      assertTrue("found frisbee", foundProducts.contains(frisbee));

      foundProducts = finder.byColorSizeAndBelowPrice(Color.pink,
         ProductSize.LARGE, 9.99f);
      assertEquals("found 0 large pink product < $9.99", 0,
         foundProducts.size());

      foundProducts = finder.byColorSizeAndBelowPrice(Color.red,
         ProductSize.LARGE, 10f);
      assertEquals("found 0 large red product < $10", 0, foundProducts.size());

      foundProducts = finder.byColorSizeAndBelowPrice(Color.pink,
         ProductSize.MEDIUM, 10f);
      assertEquals("found 0 medium pink product < $10", 0,
         foundProducts.size());
   }

    @Test
    public void findCompositeSpecOrSecondSpec(){

        List foundProducts = finder.byColorAndBelowPriceOrByColorAndSize(Color.blue, 10f,
                Color.red, ProductSize.SMALL);
        System.out.println(foundProducts.toString());
        assertEquals("found two balls",2, foundProducts.size());
        /*assertEquals("found 1 large pink product < $10", 1,
                foundProducts.size());
        assertTrue("found frisbee", foundProducts.contains(frisbee));

        foundProducts = finder.byColorSizeAndBelowPrice(Color.pink,
                ProductSize.LARGE, 9.99f);
        assertEquals("found 0 large pink product < $9.99", 0,
                foundProducts.size());
                       */



    }

}
