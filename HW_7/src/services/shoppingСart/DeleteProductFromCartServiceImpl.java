package services.shoppingСart;

import context.Component;
import dto.Dto;

import repositories.shoppingCart.ShoppingCartRepository;

public class DeleteProductFromCartServiceImpl implements DeleteProductFromCartService, Component {

    private ShoppingCartRepository shoppingCartRepository;

    public DeleteProductFromCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public Dto deleteProduct(int id) {
        shoppingCartRepository.delete(id);
        return null;
    }

    @Override
    public String getName() {
        return "deleteProductFromShoppingCartService";
    }

}
