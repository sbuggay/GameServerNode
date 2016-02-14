package com.devan.models;

import java.io.OutputStream;
import java.util.Properties;

public class GameServerConfiguration {

    public String name = "";
    public String desc = "";
    public String tags = "";
    public String gameid = "";
    public int port = 27015;

    public String GSLT = "";
    public String workshopkey = "";



    public void setFromProperties(Properties prop) {
        name = prop.getProperty("name");
        desc = prop.getProperty("desc");
        tags = prop.getProperty("tags");
        gameid = prop.getProperty("gameid");
//        port = Integer.getInteger(prop.getProperty("port"));
        port = 5;
        workshopkey = prop.getProperty("workshopkey");
        GSLT = prop.getProperty("GSLT");

    }

    public Properties createProperties() {
        Properties prop = new Properties();

        prop.setProperty("name", name);
        prop.setProperty("desc", desc);
        prop.setProperty("tags", tags);
        prop.setProperty("gameid", gameid);
        prop.setProperty("port", Integer.toString(port));
        prop.setProperty("GSLT", GSLT);
        prop.setProperty("workshopkey", workshopkey);

        return prop;
    }
}
