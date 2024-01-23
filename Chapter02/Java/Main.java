import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple");
        System.out.println(cart.getDiscountPercentage()); // 0
        cart.addItem("Book");
        System.out.println(cart.getDiscountPercentage()); // 5
        */
        // ↓
        System.out.println("----------");
        List<String> items = new ArrayList<>();
        items.add("Apple");
        System.out.println(ShoppingCart.getDiscountPercentage(items)); // 0
        items.add("Book");
        System.out.println(ShoppingCart.getDiscountPercentage(items)); // 5

        System.out.println("----------");
        List<String> names = new ArrayList<>();
        System.out.println(TipCalculator.getTipPercentage(names)); // 0
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println(TipCalculator.getTipPercentage(names)); // 10
        names.add("Daniel");
        names.add("Emily");
        names.add("Frank");
        System.out.println(TipCalculator.getTipPercentage(names)); // 20
    }
}