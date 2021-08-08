package com.likhachova.dao;

import com.likhachova.model.Product;
import com.likhachova.util.DateConverter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products;";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                LocalDate date = DateConverter.convertToEntityAttribute(resultSet.getDate(4));
                products.add(new Product(id, name, price, date));
            }
        }
        catch(SQLException e) {
            throw new RuntimeException("Cannot get all products from database", e);
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, price = ?, date = ? WHERE id = ?;";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setDate(3, DateConverter.convertToDatabaseColumn(product.getDate()));
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            throw new RuntimeException("Cannot update product in database", e);
        }
    }

    @Override
    public void addProduct(Product product) {
        String query = "INSERT INTO products (name, price, date) Values (?, ?, ?);";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setDate(3, DateConverter.convertToDatabaseColumn(product.getDate()));
            preparedStatement.execute();
        }
        catch(SQLException e) {
            throw new RuntimeException("Cannot add product to database", e);
        }
    }

    @Override
    public void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?;";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            throw new RuntimeException("Cannot delete product from database", e);
        }
    }

    @Override
    public Product getProductById(int id) {
        String query = "SELECT id, name, price, date FROM products WHERE id = ?;";
        PreparedStatement preparedStatement;
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            product = getProductFromResultSet(product, resultSet);
        }
        catch(SQLException e) {
            throw new RuntimeException("Cannot get product from database", e);
        }
        return product;
    }

    @Override
    public Product getProductByName(String name) {
        String query = "SELECT id, name, price, date FROM products WHERE name = ?;";
        PreparedStatement preparedStatement;
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            product = getProductFromResultSet(product, resultSet);
        }
        catch(SQLException e) {
            throw new RuntimeException("Cannot get product from database", e);
        }
        return product;
    }

    private Product getProductFromResultSet(Product product, ResultSet resultSet) throws SQLException {
        while(resultSet.next()) {
            int productId = resultSet.getInt(1);
            String productName = resultSet.getString(2);
            int price = resultSet.getInt(3);
            LocalDate date = DateConverter.convertToEntityAttribute(resultSet.getDate(4));
            product = new Product(productId, productName, price, date);
        }
        return product;
    }
}
