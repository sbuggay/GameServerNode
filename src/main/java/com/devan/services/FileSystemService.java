package com.devan.services;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class FileSystemService {

    public static File GameServerNodeDir = new File(System.getProperty("user.home"), "/GameServerNode");

    public static Path CreateDirectory(String directoryName) {
        File dir = new File(GameServerNodeDir, directoryName);
        final boolean success = dir.mkdirs();
        if (success) {
            return dir.toPath();
        }
        else {
            return null;
        }
    }

    /**
     * Delete directory.
     * @param directoryName Directory name to delete.
     * @return Returns true if file no longer exists.
     */
    public static boolean DeleteDirectory(String directoryName) {
        try {
            FileUtils.deleteDirectory(new File(GameServerNodeDir, directoryName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return !FileExists(directoryName);
    }

    public static int DirectoryCount() {
        File files[] = GameServerNodeDir.listFiles();
        if (files == null) {
            return 0;
        }

        return files.length;
    }

    /**
     * Check if file exists.
     * @param fileName Specified filename.
     * @return Returns true if file exists.
     */
    public static boolean FileExists(String fileName) {
        return new File(GameServerNodeDir, fileName).exists();
    }

    /**
     * Download specified url to target directory.
     * @param sourceUrl URL of download source.
     * @param targetDirectory Directory to download to.
     * @return Returns path of downloaded file.
     */
    public static Path DownloadFile(String sourceUrl, String targetDirectory, String fileName)
    {
        URL url = null;
        try {
            url = new URL(sourceUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Path targetPath = new File(GameServerNodeDir, targetDirectory + "/" + fileName).toPath();
        try {
            assert url != null;
            Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return targetPath;
    }
}
