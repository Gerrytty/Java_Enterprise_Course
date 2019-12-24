import data_base.DAO.ProductDAO;
import data_base.ORM.Product;
import utils.ConnectionToDataBase;
import utils.DataBase;

public class Main {
    public static void main(String[] args) {

        DataBase.setProperties("db.properties");

        ConnectionToDataBase.getConnection();

        Product p = new Product();
        p.setName("Telik");
        p.setPrice(1000);

        ProductDAO productDAO = new ProductDAO();

//        productDAO.save(p);

        p.setId(3);

        productDAO.delete(p);
    }
}
