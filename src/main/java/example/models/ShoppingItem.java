package example.models;

import JXpress.converters.JSONfield;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItem {
    @JSONfield
    private String name;
    @JSONfield
    private Double price;
    @JSONfield
    private int amount;
    @JSONfield
    private boolean hasPaid;

    @JSONfield
    private String description;
    @JSONfield("soldBy")
    private List<Employee> employees = new ArrayList<>();

    ShoppingItem(String name, Double price, int amount, boolean hasPaid, String description, List<Employee> employees) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.hasPaid = hasPaid;
        this.description = description;
        this.employees = employees;
    }
}
