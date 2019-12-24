package data_base.DAO;


import commands.PaginationRequest;
import data_base.ORM.Product;
import utils.ConnectionToDataBase;
import utils.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO implements DAO<Product> {

    private String tableName = "Product";

    @Override
    public Optional<Product> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    public void save(Product product) {

        String insertString = "INSERT INTO " + DataBase.dataBaseName + "." + tableName +  " (name, price) " + "VALUES (?, ?)";

        try {

            PreparedStatement preparedStatement = ConnectionToDataBase.getConnection().prepareStatement(insertString);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("adding to data base failed");
        }

    }

    @Override
    public void update(Product product, String[] params) {

    }

    @Override
    public void delete(Product product) {
        String deleteString = "DELETE from " + DataBase.dataBaseName + "." + tableName + " WHERE product_id = ?";

        try {

            PreparedStatement preparedStatement = ConnectionToDataBase.getConnection().prepareStatement(deleteString);

            preparedStatement.setInt(1, product.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("adding to data base failed");
        }
    }

    public List<Product> pagination(PaginationRequest request) {

        String pagination = "select * from java_lab_HW.Product limit ?, ?";

        List<Product> list = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = ConnectionToDataBase.getConnection().prepareStatement(pagination);
            preparedStatement.setInt(1, request.getStart());
            preparedStatement.setInt(2, request.getEnd());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));

                list.add(product);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
