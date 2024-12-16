package org.example.Model;

public class BigUser {
    private final String name;
    private final int age;

    public BigUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "BigUser{name='" + name + '\'' + ", age=" + age + '}';
    }
}
