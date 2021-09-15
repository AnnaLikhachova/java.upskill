package com.likhachova.generator.db;

import com.likhachova.generator.report.HTMLReportProducer;
import com.likhachova.generator.model.QueryResult;
import com.likhachova.generator.model.Product;

import java.sql.*;

public class DataBaseService {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public QueryResult executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return  filterQuery(query, resultSet);
    }

    private QueryResult filterQuery(String query, ResultSet resultSet) throws SQLException {
        // only for queries without WHERE
        QueryResult results = new QueryResult();
        if(query.contains("SELECT")){
            while(resultSet.next()){
                String productName = resultSet.getString(1);
                int price = resultSet.getInt(2);
                results.getProducts().add(new Product(productName, price));
            }

        }
        if(query.contains("UPDATE")){
            // выводит количество измененных строк


        }
        return results;
    }

}
