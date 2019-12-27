package model;

public class Product {

    private int id;
    private String name;
    private float price;

    public Product() {

    }

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, float price) {
        this(name, price);
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

}
