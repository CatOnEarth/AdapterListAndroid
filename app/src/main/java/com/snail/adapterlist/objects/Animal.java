package com.snail.adapterlist.objects;

public class Animal {
    private long   _id;
    private String name;
    private int    age;
    private double length;
    private double weight;
    private String color;

    public Animal() {  }

    public Animal(long _id, String name, int age, double length, double weight, String color) {
        this._id    = _id;
        this.name   = name;
        this.age    = age;
        this.length = length;
        this.weight = weight;
        this.color  = color;
    }

    public Animal(String name, int age, double length, double weight, String color) {
        this.name   = name;
        this.age    = age;
        this.length = length;
        this.weight = weight;
        this.color  = color;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
