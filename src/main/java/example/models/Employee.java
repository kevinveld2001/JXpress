package example.models;

import JXpress.converters.JSONfield;

public class Employee {
    @JSONfield
    private String name;
    @JSONfield
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
