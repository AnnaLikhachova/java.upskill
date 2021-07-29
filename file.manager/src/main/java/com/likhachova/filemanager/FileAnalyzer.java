package com.likhachova.filemanager;

import java.io.*;

public class FileAnalyzer {

    private final String SENTENCE_REGEX = "([!?.]\\s*)";
    private String content;

    FileAnalyzer(String filePath, String wordToFind) {
        try {
            readContent(filePath, wordToFind);
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            filePath + "'");
            throw new RuntimeException(ex);
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + filePath + "'");
            throw new RuntimeException(ex);
        }
        countMatches(content, wordToFind);
        printSentence(content, wordToFind);
    }

    private void readContent(String filePath, String wordToFind) throws IOException {
        if(filePath == null || wordToFind == null) {
            System.out.println("One or all arguments are null");
            return;
        }
        File file = new File(filePath);
        InputStream in = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        in.read(bytes);
        content = new String(bytes);

    }

    private void countMatches(String text, String wordToFind) {
        int wordCount = 0;
        int index = 0;
        while(index > 0) {
            index = text.indexOf(wordToFind);
            if(index > 0) {
                text = text.substring(index);
                wordCount++;
            }
        }
        if(wordCount > 0) {
            System.out.println("The word is present in text. The quantity is: " + wordCount);
        }
    }

    private void printSentence(String content, String wordToFind) {
        String[] text = content.split(SENTENCE_REGEX);
        for(String sentence : text) {
            if(sentence.contains(wordToFind))
                System.out.println(sentence);
        }
    }
}
