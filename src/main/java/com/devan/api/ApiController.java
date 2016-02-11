package com.devan.api;

import com.devan.GameServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ApiController {

    @RequestMapping(path = "/create")
    public boolean CreateServer(String name) {
        return GameServer.CreateServer(name);
    }

    @RequestMapping(path = "/destroy")
    public boolean DestroyServer(String name) {
        return GameServer.DestroyServer(name);
    }

    @RequestMapping(path = "/execute")
    public boolean DestroyServer(String name, String command) {
        return GameServer.ExecuteServerCommand(name, command);
    }

    @RequestMapping(path = "/list")
    public boolean GetServers() {
        return GameServer.GetServers();
    }
}
