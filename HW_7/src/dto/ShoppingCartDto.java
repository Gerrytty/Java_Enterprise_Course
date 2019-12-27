package dto;

import model.Product;

public class ShoppingCartDto {

    private int userId;
    private int productId;

    private Product product;

    public ShoppingCartDto() {

    }

    public ShoppingCartDto(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public ShoppingCartDto(int userId, int productId, Product product) {
        this(userId, productId);
        this.product = product;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}
