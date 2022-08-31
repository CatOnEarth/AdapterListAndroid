package com.snail.adapterlist.objects;

/**Class of animal object
 *
 */
public class Animal {
    /** ID in SQLITE */
    private long   _id;
    /** Animal name */
    private String name;
    /** Animal age */
    private int    age;
    /** Animal length */
    private double length;
    /** Animal weight */
    private double weight;
    /** Animal color */
    private String color;

    /**Default constructor Animal
     *
     */
    public Animal() {  }

    /**Constructor for animal
     *
     * @param _id id SQLITE
     * @param name name
     * @param age age
     * @param length length
     * @param weight weight
     * @param color color
     */
    public Animal(long _id, String name, int age, double length, double weight, String color) {
        this._id    = _id;
        this.name   = name;
        this.age    = age;
        this.length = length;
        this.weight = weight;
        this.color  = color;
    }

    /**Constructor for animal
     *
     * @param name name
     * @param age age
     * @param length length
     * @param weight weight
     * @param color color
     */
    public Animal(String name, int age, double length, double weight, String color) {
        this.name   = name;
        this.age    = age;
        this.length = length;
        this.weight = weight;
        this.color  = color;
    }

    /**Getter ID
     *
     * @return id
     */
    public long get_id() {
        return _id;
    }

    /**Setter id
     *
     * @param _id id in sqlite
     */
    public void set_id(long _id) {
        this._id = _id;
    }

    /**Getter name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**Setter name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Getter age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**Setter age
     *
     * @param age age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**Setter length
     *
     * @return length
     */
    public double getLength() {
        return length;
    }

    /**Setter length
     *
     * @param length length
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**Getter weight
     *
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**Setter weight
     *
     * @param weight weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**Getter color
     *
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**Setter color
     *
     * @param color color
     */
    public void setColor(String color) {
        this.color = color;
    }
}
