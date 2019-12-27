package services.shoppingСart;

import context.Component;
import dto.Dto;
import model.ShoppingCart;
import repositories.shoppingCart.ShoppingCartRepository;

public class AddProductToCartServiceImpl implements AddProductToShoppingCartService, Component {
    private ShoppingCartRepository shoppingCartRepository;

    public AddProductToCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public Dto addProductToCart(ShoppingCart cart) {
        shoppingCartRepository.save(cart);
        return null;
    }

    @Override
    public String getName() {
        return "addProductToCartService";
    }
}