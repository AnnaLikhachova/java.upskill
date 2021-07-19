package com.likhachova.filemanager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzer {

    private int wordCount = 0;
    private final String SENTENCE_REGEX = "([!?.]\\s*)";
    private String WORD_REGEX = "[d]g(?=\\s*|\\W)";


    public void createFileAnalyzer(String filePath, String wordToFind) {
        buildRegex(wordToFind);
        String line = null;
        if(filePath != null && wordToFind != null) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                FileReader fileReader =
                        new FileReader(filePath);

                BufferedReader bufferedReader =
                        new BufferedReader(fileReader);

                while((line = bufferedReader.readLine()) != null) {

                    stringBuilder.append(line);
                }
                bufferedReader.close();
                countMatches(stringBuilder.toString(), WORD_REGEX);
                if(wordCount > 0) {
                    System.out.println("The word " + wordToFind + " is present in text. The quantity is: " + wordCount);
                }
                String[] text = stringBuilder.toString().split(SENTENCE_REGEX);
                printSentence(text, WORD_REGEX);
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
        }
        else {
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
