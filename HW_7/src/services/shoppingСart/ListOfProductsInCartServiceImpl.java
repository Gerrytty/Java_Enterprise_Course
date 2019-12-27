package services.shoppingСart;

import context.Component;
import dto.ShoppingCartDto;

import java.util.List;

public class ListOfProductsInCartServiceImpl implements ListOfProductsInCartService, Component {

    private ListOfProductsInCartService listOfProductsInCartService;

    public ListOfProductsInCartServiceImpl(ListOfProductsInCartService listOfProductsInCartService) {
        this.listOfProductsInCartService = listOfProductsInCartService;
    }

    @Override
    public List<ShoppingCartDto> findAll() {
        return listOfProductsInCartService.findAll();
    }

    @Override
    public String getName() {
        return "listOfProductsInCartService";
    }
}
