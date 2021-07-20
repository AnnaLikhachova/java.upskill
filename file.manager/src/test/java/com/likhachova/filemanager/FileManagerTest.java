package com.likhachova.filemanager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {

    private File file;

    @BeforeEach
    public void testListInit() throws FileNotFoundException {
        file = new File("classpath:firstfolder");
    }

    @Test
    @DisplayName("Method counts files and returns correct result")
    public void whenCountFiles_thenQuantityIsCorrect() {
        int actualResult = FileManager.countFiles(file.getPath());
        int expectedResult = 6;
        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName("Method counts directories and returns correct result")
    public void whenCountDirectories_thenQuantityIsCorrect() {
        int actualResult = FileManager.countDirs(file.getPath());
        int expectedResult = 6;
        assertEquals(expectedResult,actualResult);
    }

    @Test
    @DisplayName("Method copies file by path")
    public void whenCopyFile_thenFileIsCopied() {
        String from = "classpath:firstfolder/thirdfolder/fifthfolder/sixfolder/textToCopy.txt";
        String to = "classpath:firstfolder/textToCopy.txt";
        FileManager.copy(from,to);
        File copiedFile = new File("classpath:firstfolder/thirdfolder/fifthfolder/sixfolder/textToCopy.txt");
        File existingFile = new File("classpath:firstfolder/textToCopy.txt");
        assertTrue(copiedFile.exists());
        assertNotNull(existingFile);
    }

    @Test
    @DisplayName("Method moves file by path")
    public void whenMoveFile_thenFileIsMoved() {
        String from = "classpath:firstfolder/text.txt";
        String to = "classpath:firstfolder/thirdfolder/fifthfolder/sixfolder/text.txt";
        FileManager.move(from,to);
        File newFile = new File("classpath:firstfolder/text.txt");
        File fileNotExists = new File("classpath:firstfolder/thirdfolder/fifthfolder/sixfolder/text.txt");
        assertFalse(newFile.exists());
    }

    @AfterAll
    @DisplayName("Clean resources")
    public static void cleanResources() {
        String from = "classpath:firstfolder/thirdfolder/fifthfolder/sixfolder/text.txt";
        String to = "classpath:firstfolder/text.txt";
        FileManager.move(from,to);
        File fileToDelete = new File("classpath:firstfolder/textToCopy.txt");
        fileToDelete.delete();
    }
}
