package services.product;

import context.Component;
import dto.ProductDto;
import repositories.product.ProductsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListOfProductsServiceImpl implements ListOfProductsService, Component {

    private ProductsRepository productsRepository;

    public ListOfProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<ProductDto> find(int start, int end) {

        Optional<List<ProductDto>> productList = productsRepository.find(start, end);

        return productList.orElse(new ArrayList<>());

    }

    @Override
    public String getName() {
        return "listOfProductsService";
    }
}
