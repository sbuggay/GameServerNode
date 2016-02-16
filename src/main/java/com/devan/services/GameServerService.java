package com.devan.services;

import java.io.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.devan.models.GameServerCommand;
import com.devan.models.GameServerConfiguration;
import com.devan.models.ServerScriptUrl;


public class GameServerService {

    //TODO: UUID.nameUUIDFromBytes needs to be refactored into another function or done before this service get it.

    final static int MaxGameServers = 10;

    /**
     * Create server with configuration.
     * @param configuration Configuration for server to set it's properties to.
     * @return Returns true if successful.
     */
    public static GameServerConfiguration CreateServer(GameServerConfiguration configuration) {

        //TODO: How do I get this error message back up to the API?

        configuration.uuid = UUID.nameUUIDFromBytes(configuration.name.getBytes()).toString();

        if (FileSystemService.DirectoryCount() >= MaxGameServers)
            return null;

        FileSystemService.CreateDirectory(configuration.uuid);

        Path path = FileSystemService.DownloadFile(ServerScriptUrl.getUrlForGame(configuration.gameid),
                configuration.uuid, "server");

        if (path == null)
            return null;

        configuration.save(new File(FileSystemService.GameServerNodeDir + "/" + configuration.uuid, "server.properties"));


        Charset charset = StandardCharsets.UTF_8;


        //TODO: Clean this up. Write a helper for it maybe
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
        content = content.replaceAll("gamename=\"Counter Strike: Global Offensive\"",
                "gamename=\"" + configuration.name +"\"");
        try {
            Files.write(path, content.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    /**
     * Destroy specified server.
     * @param server Server to be destroyed.
     * @return Returns true if successful.
     */
    public static boolean DestroyServer(String server) {
        return FileSystemService.DeleteDirectory(server);
    }

    /**
     * Execute server command on specified server.
     * @param command Server to run command.
     * @return Returns true if successful.
     */
    public static boolean ExecuteServerCommand(GameServerCommand command) {

        try {

            final ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "server", command.command)
                    .directory(new File(FileSystemService.GameServerNodeDir, command.uuid));

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
    public static List<GameServerConfiguration> GetAllServers() {
        File[] directories = FileSystemService.GameServerNodeDir.listFiles(File::isDirectory);

        List<GameServerConfiguration> servers = new ArrayList<>();


        //TODO: Refactor this to use GetServerInfo

        for (File directory : directories) {

            GameServerConfiguration config = new GameServerConfiguration();
            config.load(new File(directory + "/server.properties"));
            servers.add(config);

        }

        return servers;
    }

    /**
     * Get information on a specific server.
     * @param uuid Specified server name.
     * @return JSON object of server properties.
     */
    public static GameServerConfiguration GetServerInfo(String uuid) {
        GameServerConfiguration config = new GameServerConfiguration();
        config.load(new File(FileSystemService.GameServerNodeDir + "/" + uuid, "/server.properties"));
        return config;
    }


}
