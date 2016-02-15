package com.devan.services;

import java.io.*;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

import com.devan.models.GameServerCommand;
import com.devan.models.GameServerConfiguration;
import org.apache.commons.io.FileUtils;


public class GameServerService {

    public static String HomeDirectory = System.getProperty("user.home");


    //TODO: UUID.nameUUIDFromBytes needs to be refactored into another function or done before this service get it.

    /**
     * Create server with configuration.
     * @param configuration Configuration for server to set it's properties to.
     * @return Returns true if successful.
     */
    public static boolean CreateServer(GameServerConfiguration configuration) {
        boolean success = new File(HomeDirectory, "GameServerNode/" + UUID.nameUUIDFromBytes(configuration.name.getBytes())).mkdirs();


        try {
            download("https://raw.githubusercontent.com/dgibbs64/linuxgameservers/master/CounterStrikeGlobalOffensive/csgoserver",
                    UUID.nameUUIDFromBytes(configuration.name.getBytes()).toString(), "server");
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }

        Path path = new File(HomeDirectory, "GameServerNode/" + UUID.nameUUIDFromBytes(configuration.name.getBytes()) + "/server").toPath();
        Charset charset = StandardCharsets.UTF_8;


        //TODO: Clean this up.
        String content = null;
        try {
            content = new String(Files.readAllBytes(path),
                    charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert content != null;
        content = content.replaceAll("port=\"27015\"", "port=\"" + configuration.port +"\"");
        content = content.replaceAll("ip=\"0.0.0.0\"", "ip=\"127.0.0.1\"");
        content = content.replaceAll("gamename=\"Counter Strike: Global Offensive\"", "gamename=\"" + configuration.name +"\"");
        try {
            Files.write(path, content.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Destroy specified server.
     * @param server Server to be destroyed.
     * @return Returns true if successful.
     */
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

    /**
     * Execute server command on specified server.
     * @param command Server to run command.
     * @return Returns true if successful.
     */
    public static boolean ExecuteServerCommand(GameServerCommand command) {

        try {

            final ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "server", command.command)
                    .directory(new File(HomeDirectory, "GameServerNode/" + UUID.nameUUIDFromBytes(command.name.getBytes())));

            final Process process = processBuilder.start();

            System.out.println(process.toString());

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

        }
        catch (IOException e){
            System.out.println(e.toString());
        }

        return true;
    }

    /**
     * List all servers.
     * @return Returns JSON array of all server properties.
     */
//    public static List<GameServerConfiguration> GetServers() {
//        File[] directories = new File(HomeDirectory, "GameServerNode/").listFiles(File::isDirectory);
//
//        List<GameServerConfiguration> servers = new ArrayList<>();
//
//
//        //TODO: Refactor this to use GetServerInfo
//        Properties prop = new Properties();
//
//        for (File directory : directories) {
//            try {
//                InputStream input = new FileInputStream(new File(directory + "/server.properties"));
//                prop.load(input);
//
//                GameServerConfiguration config = new GameServerConfiguration();
//
//                config.setFromProperties(prop);
//
//                servers.add(config);
//            }
//            catch (IOException e) {
//                System.out.println(e.toString());
//            }
//        }
//
//        return servers;
//    }

    /**
     * Get information on a specific server.
     * @param server Specified server name.
     * @return JSON object of server properties.
     */
//    public static GameServerConfiguration GetServerInfo(String server) {
//
//        Properties prop = new Properties();
//
//        try {
//            InputStream input = new FileInputStream(new File(HomeDirectory, "GameServerNode/" + UUID.nameUUIDFromBytes(server.getBytes()) + "/server.properties"));
//            prop.load(input);
//
//        }
//        catch (IOException e) {
//            System.out.println(e.toString());
//        }
//
//        GameServerConfiguration config = new GameServerConfiguration();
//
//        config.setFromProperties(prop);
//
//        return config;
//    }

    /**
     * Download specified url to target directory.
     * @param sourceUrl URL of download source.
     * @param targetDirectory Directory to download to.
     * @return Returns path of downloaded file.
     */
    public static Path download(String sourceUrl, String targetDirectory, String fileName) throws IOException
    {
        URL url = new URL(sourceUrl);

        Path targetPath = new File(HomeDirectory, "GameServerNode/" + targetDirectory + "/" + fileName).toPath();
        Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return targetPath;
    }
}
