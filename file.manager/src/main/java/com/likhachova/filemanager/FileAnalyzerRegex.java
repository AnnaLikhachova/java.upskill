package com.likhachova.filemanager;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzerRegex {

    private int wordCount = 0;
    private final String SENTENCE_REGEX = "([!?.]\\s*)";
    private String WORD_REGEX = "[d]g(?=\\s*|\\W)";


    public void createFileAnalyzer(String filePath, String wordToFind) {
        buildRegex(wordToFind);
        if(filePath != null && wordToFind != null) {
            File file = new File(filePath);
            Charset charset = StandardCharsets.UTF_8; //TODO: refactor split in regex
            String content = null;
            try(InputStream in = new FileInputStream(file)) {
                byte[] bytes = new byte[(int) file.length()];
                in.read(bytes);
                content = new String(bytes, charset);
            }
            catch(FileNotFoundException ex) {
                System.out.println(
                        "Unable to open file '" +
                                filePath + "'");
            }
            catch(IOException ex) {
                System.out.println(
                        "Error reading file '"
                                + filePath + "'");
            }
            countMatches(content, WORD_REGEX);
            if(wordCount > 0) {
                System.out.println("The word " + wordToFind + " is present in text. The quantity is: " + wordCount);
            }
            String[] text = content.split(SENTENCE_REGEX);
            printSentence(text, WORD_REGEX);
        } else {
            System.out.println("One or all arguments are null");
        }
    }

    private void buildRegex(String wordToFind) {
        String firstLetter = wordToFind.substring(0, 1);
        String restPartOfWord = wordToFind.substring(1);
        StringBuilder builder = new StringBuilder();
        builder.append(firstLetter.toUpperCase());
        builder.append(firstLetter);
        WORD_REGEX = WORD_REGEX.replace("d", builder.toString());
        WORD_REGEX = WORD_REGEX.replace("g", restPartOfWord);
    }

    private void countMatches(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            wordCount++;
        }
    }

    private void printSentence(String[] text, String regex) {
        for(String sentence : text) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sentence);
            if(matcher.find()) {
                System.out.println(sentence);
            }
        }
    }
}