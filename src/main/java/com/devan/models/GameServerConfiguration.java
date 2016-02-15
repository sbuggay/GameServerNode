package com.devan.models;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.util.Properties;


public class GameServerConfiguration {

    public String name = "";
    public String desc = "";
    public String tags = "";
    public String gameid = "";
    public String port = "27015";

    public String GSLT = "";
    public String workshopkey = "";

    public String uuid;

    public void save(File file) {
        Properties prop = new Properties();

        try {
            OutputStream output = new FileOutputStream(file);

            prop.setProperty("name", name);
            prop.setProperty("desc", desc);
            prop.setProperty("tags", tags);
            prop.setProperty("gameid", gameid);
            prop.setProperty("port", port);
            prop.setProperty("GSLT", GSLT);
            prop.setProperty("workshopkey", workshopkey);
            prop.setProperty("uuid", uuid);

            prop.store(output, file.getName());

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(File file) {
        Properties prop = new Properties();

        try {
            InputStream input = new FileInputStream(file);
            prop.load(input);

            name = prop.getProperty("name");
            desc = prop.getProperty("desc");
            tags = prop.getProperty("tags");
            gameid = prop.getProperty("gameid");
            port = prop.getProperty("port");
            GSLT = prop.getProperty("GSLT");
            workshopkey = prop.getProperty("workshopkey");
            uuid = prop.getProperty("uuid");

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

}
