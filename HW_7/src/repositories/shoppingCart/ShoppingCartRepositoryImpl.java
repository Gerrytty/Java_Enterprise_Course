package repositories.shoppingCart;

import context.Component;
import model.Product;
import model.ShoppingCart;
import repositories.RowMapper;
import utils.ConnectionToDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartRepositoryImpl implements ShoppingCartRepository, Component {
    private Connection connection;

    private RowMapper<ShoppingCart> rowMapper = rs -> new ShoppingCart(
            rs.getInt("user_id"),
            rs.getInt("product_id"),
            new Product(rs.getString("product_name"), rs.getInt("price"))
    );

    public ShoppingCartRepositoryImpl() {
        connection = ConnectionToDataBase.getConnection();
    }

    @Override
    public void save(ShoppingCart cart) {
        String insertString = "insert into java_lab_HW.ShoppingCart(user_id, product_id) values (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(insertString)) {

            ps.setInt(1, cart.getUserId());
            ps.setInt(2, cart.getProductId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<ShoppingCart> findOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<ShoppingCart> findAll() {

        String selectString = "select * from java_lab_HW.ShoppingCart join java_lab_HW.Product on " +
                "ShoppingCart.product_id = Product.product_id";

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(selectString);
            List<ShoppingCart> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(rowMapper.mapRow(rs));
            }

            return productList;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public String getName() {
        return "shoppingCartRepository";
    }
}