package commands;

import data_base.ORM.Product;

import java.util.List;

public class ShowProductsList<T> {
    List<Product> listOfProducts;

    public ShowProductsList() {

    }

    public ShowProductsList(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}
