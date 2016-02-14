package com.devan.api;

import com.devan.services.GameServerService;
import com.devan.models.GameServerConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ApiController {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public boolean CreateServer(@RequestBody GameServerConfiguration configuration) {
        return GameServerService.CreateServer(configuration);
    }

    @RequestMapping(path = "/destroy", method = RequestMethod.DELETE)
    public boolean DestroyServer(String name) {
        return GameServerService.DestroyServer(name);
    }

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    public boolean ExecuteServer(String name, String command) {
        return GameServerService.ExecuteServerCommand(name, command);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<GameServerConfiguration>> GetAllServers() {
        return new ResponseEntity<>(GameServerService.GetServers(), HttpStatus.OK);
    }

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public ResponseEntity<GameServerConfiguration> GetServer(String name) {
        return new ResponseEntity<>(GameServerService.GetServerInfo(name), HttpStatus.OK);
    }
}
