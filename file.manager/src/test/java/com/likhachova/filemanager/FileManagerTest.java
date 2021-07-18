package com.likhachova.filemanager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {

    private File file;

    @BeforeEach
    public void testListInit() throws FileNotFoundException {
        file = ResourceUtils.getFile("classpath:firstfolder");
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
    public void whenCopyFile_thenFileIsCopied() throws FileNotFoundException {
        String from = "src/main/resources/firstfolder/thirdfolder/fifthfolder/sixfolder";
        String to = "src/main/resources/firstfolder";
        FileManager.copy(from,to);
        File copiedFile = ResourceUtils.getFile("src/main/resources/firstfolder/thirdfolder/fifthfolder/sixfolder/textToCopy.txt");
        File existingFile = ResourceUtils.getFile("src/main/resources/firstfolder/textToCopy.txt");
        assertTrue(copiedFile.exists());
        assertNotNull(existingFile);
    }

    @Test
    @DisplayName("Method moves file by path")
    public void whenMoveFile_thenFileIsMoved() throws FileNotFoundException {
        String from = "src/main/resources/firstfolder/text.txt";
        String to = "src/main/resources/firstfolder/thirdfolder/fifthfolder/sixfolder/text.txt";
        FileManager.move(from,to);
        File newFile = ResourceUtils.getFile("src/main/resources/firstfolder/text.txt");
        File fileNotExists = ResourceUtils.getFile("src/main/resources/firstfolder/thirdfolder/fifthfolder/sixfolder/text.txt");
        assertFalse(newFile.exists());
    }

    @AfterAll
    @DisplayName("Clean resources")
    public static void cleanResources() throws FileNotFoundException {
        String from = "src/main/resources/firstfolder/thirdfolder/fifthfolder/sixfolder/text.txt";
        String to = "src/main/resources/firstfolder/text.txt";
        FileManager.move(from,to);
        File fileToDelete = ResourceUtils.getFile("src/main/resources/firstfolder/textToCopy.txt");
        fileToDelete.delete();
    }
}
