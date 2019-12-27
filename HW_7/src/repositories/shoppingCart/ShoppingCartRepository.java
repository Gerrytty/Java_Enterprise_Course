package repositories.shoppingCart;

import model.ShoppingCart;
import repositories.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {

    void save(ShoppingCart shoppingCart);

}
