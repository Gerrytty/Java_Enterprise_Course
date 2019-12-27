package repositories.product;

import context.Component;
import dto.ProductDto;
import model.Product;
import repositories.RowMapper;
import utils.ConnectionToDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryImpl implements ProductsRepository, Component {

    private Connection connection;

    private RowMapper<ProductDto> rowMapper = rs -> new ProductDto(
            rs.getInt("product_id"),
            rs.getString("name"),
            rs.getFloat("price")
    );

    public ProductsRepositoryImpl() {
        connection = ConnectionToDataBase.getConnection();
    }

    @Override
    public Optional<Product> findOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void save(Product product) {
        String insertString = "insert into java_lab_HW.Product(name, price) values (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(insertString)) {

            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("adding product to data base failed");
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void delete(int id) {
        String deleteString = "delete from java_lab_HW.Product where product_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(deleteString)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("deleting product from data base failed");
            throw new IllegalStateException(e);
        }

    }

    @Override
    public Optional<List<ProductDto>> find(int start, int end) {
        String selectString = "select * from java_lab_HW.Product where product_id > ? and product_id < ?";

        try (PreparedStatement ps = connection.prepareStatement(selectString)) {

            ps.setInt(1, start);
            ps.setInt(2, end);
            ResultSet rs = ps.executeQuery();
            List<ProductDto> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(rowMapper.mapRow(rs));
            }

            return Optional.of(productList);

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public String getName() {
        return "productsRepository";
    }
}
