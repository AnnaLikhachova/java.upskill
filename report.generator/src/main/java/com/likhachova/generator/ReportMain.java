package com.likhachova.generator;

import com.likhachova.generator.db.DataBaseService;
import com.likhachova.generator.model.QueryResult;
import com.likhachova.generator.report.ConsoleReportPrinter;
import com.likhachova.generator.report.HTMLReportProducer;
import com.likhachova.generator.util.PropertiesLoader;
import com.likhachova.generator.util.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class ReportMain {

    public static void main(String[] args) {
        Settings settings = new PropertiesLoader().load();
        DataBaseService dataBaseService = new DataBaseService();

        HTMLReportProducer htmlReportProducer = new HTMLReportProducer(settings.getReportPath());
        ConsoleReportPrinter consoleReportPrinter = new ConsoleReportPrinter();

        try(Scanner scanner = new Scanner(System.in)) {
            Connection connection = DriverManager.getConnection(settings.getUrl(), settings.getUserName(),settings.getPassword());
            dataBaseService.setConnection(connection);

            while(true) {
                if(scanner.hasNext()) {
                    String query = scanner.nextLine();
                    QueryResult result = dataBaseService.executeQuery(query);
                    consoleReportPrinter.writeToConsole(result);
                    htmlReportProducer.createReport(result);
                }
            }
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
