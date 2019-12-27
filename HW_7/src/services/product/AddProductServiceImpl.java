package services.product;

import context.Component;
import dto.Dto;
import model.Product;
import repositories.product.ProductsRepository;

import static dto.ProductDto.from;

public class AddProductServiceImpl implements AddProductService, Component {

    private ProductsRepository productsRepository;

    public AddProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Dto addProduct(String name, float price) {
        Product product = new Product(name, price);
        productsRepository.save(product);
        return from(product);
    }

    @Override
    public String getName() {
        return "addProductService";
    }
}