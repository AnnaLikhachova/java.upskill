package com.likhachova.controller;

import com.likhachova.util.ImageReader;
import com.likhachova.util.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MainController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String mainImage = ImageReader.getImage("templates/images/products.jpg");
        pageVariables.put("mainImage", mainImage);
        response.getWriter().println(PageGenerator.instance().getPage("main.ftl", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
