package com.likhachova.filemanager;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static int countFiles(String path) {
        int count = 0;
        File dir = new File(path);
        if(dir.isDirectory()) {
            count = recursiveCountFiles(dir.listFiles(), count);
        }
        else {
            count++;
        }
        return count;
    }

    private static int recursiveCountFiles(File[] dir, int count) {
        int nextCount = count;
        for(File file : dir) {
            if(file.isDirectory()) {
                nextCount = recursiveCountFiles(file.listFiles(), nextCount);
            }
            else {
                nextCount++;
            }
        }
        return nextCount;
    }

    private static int recursiveCountDirs(File[] dir, int count) {
        int nextCount = count;
        for(File file : dir) {
            if(file.isDirectory()) {
                nextCount++;
                nextCount = recursiveCountDirs(file.listFiles(), nextCount);
            }
        }
        return nextCount;
    }

    public static int countDirs(String path) {
        int count = 0;
        File dir = new File(path);
        if(dir.isDirectory()) {
            count++;
            count = recursiveCountDirs(dir.listFiles(), count);
        }
        return count;
    }

    public static void copy(String from, String to) throws FileNotFoundException {
        File source = ResourceUtils.getFile(from);
        File dest = ResourceUtils.getFile(to);
        try {
            FileUtils.copyDirectory(source, dest);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        if(dest.exists()) {
            System.out.println("File copied successfully.");
        }
        else {
            System.out.println("File copy failed.");
        }
    }

    public static void move(String from, String to) {
        Path result = null;
        try {
            result = Files.move(Paths.get(from), Paths.get(to));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        if(result != null) {
            System.out.println("File moved successfully.");
        }
        else {
            System.out.println("File movement failed.");
        }
    }
}
