package com.likhachova.controller;

import com.likhachova.model.Product;
import com.likhachova.service.ProductService;
import com.likhachova.util.ImageReader;
import com.likhachova.util.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

public class UpdateProductController extends HttpServlet {

    private ProductService productService;

    public UpdateProductController(ProductService productService) {
        this.productService = productService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        Product product = productService.getProduct(request);
        pageVariables.put("product", product);
        response.getWriter().println(PageGenerator.instance().getPage("updateproduct.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String date = request.getParameter("date");
        if(name == null || price == null || date == null || name.isEmpty() || price.isEmpty() || date.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            pageVariables.put("message", "Product was not updated as one or more fields were empty");
        }
        else {
            response.setStatus(HttpServletResponse.SC_OK);
            productService.updateProduct(request);
            pageVariables.put("message", "Product was updated");
        }
        String mainImage = ImageReader.getImage("templates/images/products.jpg");
        pageVariables.put("mainImage", mainImage);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("main.ftl", pageVariables));

    }

}
