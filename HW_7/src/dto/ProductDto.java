package dto;

import model.Product;

public class ProductDto implements Dto {

    private int id;
    private String name;
    private float price;

    public ProductDto() {
    }

    public ProductDto(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static ProductDto from(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}