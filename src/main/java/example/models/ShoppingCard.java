package example.models;

import JXpress.converters.JSONfield;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCard {
    @JSONfield
    List<ShoppingItem> items = new ArrayList<>();

    public ShoppingCard() {
        List<Employee> employees = new ArrayList<>();
        items.add(new ShoppingItem("a", 10.69, 10, true, null, employees));
        employees.add(new Employee("Henk", 20));
        items.add(new ShoppingItem("b", 5.78, 5, false, "test", employees));
        employees.add(new Employee("Joop", 21));
        items.add(new ShoppingItem("c", 1.99, 1, true, null, employees));
    }

    public List<ShoppingItem> getItems() {
        return items;
    }
}
