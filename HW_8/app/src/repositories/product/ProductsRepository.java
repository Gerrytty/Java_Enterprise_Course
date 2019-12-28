package repositories.product;

import dto.ProductDto;
import model.Product;
import repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends CrudRepository<ProductDto, Integer> {

    Optional<List<ProductDto>> find(int start, int end);

}
