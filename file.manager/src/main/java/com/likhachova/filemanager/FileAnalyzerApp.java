package com.likhachova.filemanager;

public class FileAnalyzerApp {

    public static void main(String [] args) {
        String filePath = args[0];
        String wordToFind = args[1];
        new FileAnalyzer(filePath,wordToFind);
    }
}
