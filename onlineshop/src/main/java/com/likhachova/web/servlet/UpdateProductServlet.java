package com.likhachova.web.controller;

import com.likhachova.model.Product;
import com.likhachova.service.ProductService;
import com.likhachova.util.ExtractProductFromRequest;
import com.likhachova.util.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

public class UpdateProductServlet extends HttpServlet {

    private ProductService productService;

    public UpdateProductServlet(ProductService productService) {
        this.productService = productService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        Product product = ExtractProductFromRequest.getProductFromRequest(request);
        pageVariables.put("product", product);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("updateproduct.html", pageVariables));
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
            Product product = ExtractProductFromRequest.getProductWithIdFromRequest(request);
            productService.updateProduct(product);
            pageVariables.put("message", "Product was updated");
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("allproducts.ftl", pageVariables));
    }

}
