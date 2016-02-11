package com.devan;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.devan.models.GameServerConfiguration;
import org.apache.commons.io.FileUtils;

public class GameServer {

    public static String HomeDirectory = System.getProperty("user.home");

    public static boolean CreateServer(GameServerConfiguration configuration) {
        boolean success = new File(HomeDirectory, "GameServerNode/" + configuration.ServerName).mkdirs();

        try {
            System.out.println(download("https://raw.githubusercontent.com/dgibbs64/linuxgameservers/master/CounterStrikeGlobalOffensive/csgoserver", configuration.ServerName));
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }

        return success;
    }

    public static boolean DestroyServer(String server) {

        try {
            FileUtils.deleteDirectory(new File(HomeDirectory, "GameServerNode/" + server));
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }

        return true;
    }

    public static boolean ExecuteServerCommand(String server, String command) {

        try {
            Process process = Runtime.getRuntime().exec("./server " + command, null, new File(HomeDirectory, "GameServerNode/" + server));
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println("Script output: " + s);
            }
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
        return true;
    }

    public static boolean GetServers() {
        File[] directories = new File(HomeDirectory, "GameServerNode/").listFiles(File::isDirectory);

        for (File directory : directories) {
            System.out.println(directory.toString());
        }

        return true;
    }

    public static Path download(String sourceUrl, String targetDirectory) throws IOException
    {
        URL url = new URL(sourceUrl);

        Path targetPath = new File(HomeDirectory, "GameServerNode/" + targetDirectory + "/" + "server").toPath();
        Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return targetPath;
    }
}
