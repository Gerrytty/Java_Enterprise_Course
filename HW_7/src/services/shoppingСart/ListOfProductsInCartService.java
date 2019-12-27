package services.shoppingСart;

import dto.ShoppingCartDto;

import java.util.List;

public interface ListOfProductsInCartService {

    List<ShoppingCartDto> findAll();

}
