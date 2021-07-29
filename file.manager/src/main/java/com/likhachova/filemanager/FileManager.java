package com.likhachova.filemanager;

import java.io.*;

public class FileManager {

    public static int countFiles(String path) {
        int count = 0;
        File dir = new File(path);
        for(File file : dir.listFiles()){
            if(dir.isDirectory()) {
                count = countFiles(file.getAbsolutePath());
            }
            else {
                count++;
            }
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
        for(File file : dir.listFiles()){
            if(file.isDirectory()) {
                count++;
                count += countDirs(file.getAbsolutePath());
            }
        }
        return count;
    }

    private static void recursiveCopy(File from, File to) {
        if(from.isDirectory()) {
            if(!to.exists()) {
                to.mkdir();
            }
            String[] children = from.list();
            for(int i = 0; i < children.length; i++) {
                recursiveCopy(new File(from, children[i]), new File(to, children[i]));
            }
        } else {

            try(
                    InputStream in = new FileInputStream(from);
                    OutputStream out = new FileOutputStream(to);
            ) {
                byte[] buf = new byte[8192];
                int len;
                while((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void copy(String from, String to) {
        File fileToCopy = new File(from);
        File fileCopied = new File(to);
        recursiveCopy(fileToCopy,fileCopied);
    }

    public static void move(String from, String to) {
        File fileToCopy = new File(from);
        File fileCopied = new File(to);
        recursiveCopy(fileToCopy,fileCopied);
        fileToCopy.delete();
    }
}
