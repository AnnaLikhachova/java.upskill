package com.likhachova.service;

import com.likhachova.dao.ProductDao;
import com.likhachova.dao.ProductDaoImpl;
import com.likhachova.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductService {

    private ProductDao productDao;

    public void setProductDaoImpl(ProductDaoImpl productDaoImpl) {
        this.productDao = productDaoImpl;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProduct(HttpServletRequest httpServletRequest) {
        int id = getProductId(httpServletRequest);
        return productDao.getProductById(id);
    }

    public void addProduct(HttpServletRequest httpServletRequest) {
        Product product = getProductFromRequest(httpServletRequest);
        productDao.addProduct(product);
    }

    public void updateProduct(HttpServletRequest httpServletRequest) {
        Product product = getProductWithIdFromRequest(httpServletRequest);
        productDao.updateProduct(product);
    }

    public void deleteProduct(HttpServletRequest httpServletRequest) {
        int id = getProductId(httpServletRequest);
        productDao.deleteProduct(id);
    }

    private Product getProductFromRequest(HttpServletRequest httpServletRequest) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(httpServletRequest.getParameter("date"), dtf);
        return new Product(
                httpServletRequest.getParameter("name"),
                Integer.parseInt(httpServletRequest.getParameter("price")),
                date);
    }

    private Product getProductWithIdFromRequest(HttpServletRequest httpServletRequest) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(httpServletRequest.getParameter("date"), dtf);
        return new Product(
                Integer.parseInt(httpServletRequest.getParameter("id")),
                httpServletRequest.getParameter("name"),
                Integer.parseInt(httpServletRequest.getParameter("price")),
                date);
    }

    private int getProductId(HttpServletRequest httpServletRequest) {
        return Integer.parseInt(httpServletRequest.getParameter("id"));
    }
}
