package com.devan.services;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileSystemServiceTest {

    @Test
    public void testCreateDirectory() throws Exception {

        FileSystemService.CreateDirectory("test");

        File file = new File(FileSystemService.GameServerNodeDir, "test");

        assert file.exists();

        final boolean success = file.delete();

    }

//    @Test
//    public void testDeleteDirectory() throws Exception {
//        File file = new File(FileSystemService.GameServerNodeDir, "test2");
//
//        assert file.mkdirs();
//
//        FileSystemService.DeleteDirectory("test2");
//
//        assert !file.exists();
//        assert file.delete();
//    }
//
//    @Test
//    public void testFileExists() throws Exception {
//        File file = new File(FileSystemService.GameServerNodeDir, "test");
//
//        assert FileSystemService.FileExists("test");
//    }

    @Test
    public void testDownloadFile() throws Exception {

    }
}