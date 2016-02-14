package com.devan.services;

import java.io.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

import com.devan.models.GameServerConfiguration;
import org.apache.commons.io.FileUtils;


public class GameServerService {

    public static String HomeDirectory = System.getProperty("user.home");

    public static boolean CreateServer(GameServerConfiguration configuration) {
        boolean success = new File(HomeDirectory, "GameServerNode/" + UUID.nameUUIDFromBytes(configuration.name.getBytes())).mkdirs();


        try {
            download("https://raw.githubusercontent.com/dgibbs64/linuxgameservers/master/CounterStrikeGlobalOffensive/csgoserver",
                    UUID.nameUUIDFromBytes(configuration.name.getBytes()).toString());
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }


        OutputStream output = null;


        try {

            output = new FileOutputStream(new File(HomeDirectory, "GameServerNode/" + UUID.nameUUIDFromBytes(configuration.name.getBytes()) + "/server.properties"));

            Properties prop = configuration.createProperties();

            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        return success;
    }

    public static boolean DestroyServer(String server) {

        try {
            FileUtils.deleteDirectory(new File(HomeDirectory, "GameServerNode/" + UUID.nameUUIDFromBytes(server.getBytes())));
            return true;
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }

        return false;
    }

    public static boolean ExecuteServerCommand(String server, String command) {

        try {
            Process process = Runtime.getRuntime().exec("server " + command, null, new File(HomeDirectory, "GameServerNode/" + server));
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

    public static List<GameServerConfiguration> GetServers() {
        File[] directories = new File(HomeDirectory, "GameServerNode/").listFiles(File::isDirectory);

        List<GameServerConfiguration> servers = new ArrayList<>();

        Properties prop = new Properties();

        for (File directory : directories) {
            try {
                InputStream input = new FileInputStream(new File(directory + "/server.properties"));
                prop.load(input);

                GameServerConfiguration config = new GameServerConfiguration();

                config.setFromProperties(prop);

                servers.add(config);
            }
            catch (IOException e) {
                System.out.println(e.toString());
            }
        }

        return servers;
    }

    public static GameServerConfiguration GetServerInfo(String name) {

        Properties prop = new Properties();

        try {
            InputStream input = new FileInputStream(new File(HomeDirectory, "GameServerNode/" + UUID.nameUUIDFromBytes(name.getBytes()) + "/server.properties"));
            prop.load(input);

        }
        catch (IOException e) {
            System.out.println(e.toString());
        }

        GameServerConfiguration config = new GameServerConfiguration();

        config.setFromProperties(prop);

        return config;
    }

    public static Path download(String sourceUrl, String targetDirectory) throws IOException
    {
        URL url = new URL(sourceUrl);

        Path targetPath = new File(HomeDirectory, "GameServerNode/" + targetDirectory + "/" + "server").toPath();
        Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return targetPath;
    }
}
