package services.product;

import context.Component;
import dto.Dto;
import repositories.product.ProductsRepository;

public class DeleteProductServiceImpl implements DeleteProductService, Component {

    private ProductsRepository productsRepository;

    public DeleteProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Dto deleteProduct(int id) {
        productsRepository.delete(id);
        return null;
    }

    @Override
    public String getName() {
        return "deleteProductService";
    }
}
