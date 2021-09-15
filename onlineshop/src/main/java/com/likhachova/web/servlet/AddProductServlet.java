package com.likhachova.web.controller;

import com.likhachova.model.Product;
import com.likhachova.service.ProductService;
import com.likhachova.util.ExtractProductFromRequest;
import com.likhachova.util.ImageReader;
import com.likhachova.util.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddProductServlet extends HttpServlet {

    private ProductService productService;

    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("addproduct.html", pageVariables));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        if(name == null || price == null || description == null || date == null || name.isEmpty() || price.isEmpty() ||  description.isEmpty() || date.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            pageVariables.put("message", "Product was not added as one or more fields were empty");
        }
        else {
            Product product = ExtractProductFromRequest.getProductFromRequest(request);
            response.setStatus(HttpServletResponse.SC_OK);
            productService.addProduct(product);
            pageVariables.put("message", "Product was added");
        }
        String mainImage = ImageReader.getImage("templates/images/products.jpg");
        pageVariables.put("mainImage", mainImage);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("main.ftl", pageVariables));
    }

}
