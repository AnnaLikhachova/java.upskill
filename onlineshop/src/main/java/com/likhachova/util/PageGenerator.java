package com.likhachova.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class PageGenerator {

    private static final String HTML_DIR = "/templates/";

    private static PageGenerator pageGenerator;
    private static Configuration configuration;

    private PageGenerator() { }

    public static PageGenerator instance() {
        if (pageGenerator == null){
            pageGenerator = new PageGenerator();
            configuration = new Configuration();
            configuration.setClassForTemplateLoading(PageGenerator.class, HTML_DIR);
        }
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = configuration.getTemplate(filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }




}
