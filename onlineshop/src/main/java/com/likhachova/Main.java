package com.likhachova;

import com.likhachova.configuration.PropertiesReader;
import com.likhachova.controller.AddProductController;
import com.likhachova.controller.AllProductsController;
import com.likhachova.controller.MainController;
import com.likhachova.controller.UpdateProductController;
import com.likhachova.dao.ProductDaoImpl;
import com.likhachova.service.ProductService;
import com.likhachova.dao.jdbc.ConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {

        String pathToProperties ="application.properties";
        PropertiesReader propertiesReader = new PropertiesReader(pathToProperties);
        ConnectionFactory connectionFactory = new ConnectionFactory(propertiesReader.getProperties());

        ProductDaoImpl productDaoImpl = new ProductDaoImpl(connectionFactory.getConnection());

        ProductService productService = new ProductService();
        productService.setProductDaoImpl(productDaoImpl);

        MainController mainController = new MainController();
        AllProductsController allProductsController = new AllProductsController(productService);
        AddProductController addProductServlet = new AddProductController(productService);
        UpdateProductController updateProductControllerServlet = new UpdateProductController(productService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(mainController), "/");
        contextHandler.addServlet(new ServletHolder(allProductsController), "/allproducts");
        contextHandler.addServlet(new ServletHolder(addProductServlet), "/addproduct");
        contextHandler.addServlet(new ServletHolder(updateProductControllerServlet), "/updateproduct");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
    }
}
