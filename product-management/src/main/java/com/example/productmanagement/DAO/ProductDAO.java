package com.example.productmanagement.DAO;

import com.example.productmanagement.DAO.connection.MyConnection;
import com.example.productmanagement.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection = MyConnection.getConnection();
    private static ProductDAO productDAO;

    public static ProductDAO getInstance() {
        if (productDAO == null) {
            productDAO = new ProductDAO();
        }
        return productDAO;
    }

    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        String query = "select * from product";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String brand = resultSet.getString("brand");
                productList.add(new Product(id, name, price, description, brand));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product findById(int id) {
        Product product = null;
        String query = "select * from product";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String brand = resultSet.getString("brand");
                product = new Product(id, name, price, description, brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }


    public void addNewProduct(Product product) {
        String query = "insert into product(name,price,description,brand) values (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        String query = "update product set name = ? , price = ?, description = ? , brand = ? where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getBrand());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteProduct(int id) {
        String query = "delete from product where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> searchByName(String searchName) {
        List<Product> productList = new ArrayList<>();
        String query = "select * from product where name like ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String brand = resultSet.getString("brand");
                productList.add(new Product(id, name, price, description, brand));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
