package com.devan.api;

import com.devan.GameServer;
import com.devan.models.GameServerConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ApiController {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean CreateServer(GameServerConfiguration configuration) {
        return GameServer.CreateServer(configuration);
    }

    @RequestMapping(path = "/destroy", method = RequestMethod.POST)
    public boolean DestroyServer(String name) {
        return GameServer.DestroyServer(name);
    }

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    public boolean DestroyServer(String name, String command) {
        return GameServer.ExecuteServerCommand(name, command);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public boolean GetServers() {
        return GameServer.GetServers();
    }
}
