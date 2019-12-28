package services.product;

import dto.ProductDto;

import java.util.List;

public interface ListOfProductsService {

    List<ProductDto> find(int start, int end);

    List<ProductDto> findAll();

}
