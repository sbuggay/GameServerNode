package com.devan.api;

import com.devan.models.GameServerCommand;
import com.devan.services.GameServerService;
import com.devan.models.GameServerConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class ApiController {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> CreateServer(@RequestBody GameServerConfiguration configuration) {
        GameServerConfiguration updatedConfig = GameServerService.CreateServer(configuration);
        return new ResponseEntity<>(updatedConfig, HttpStatus.OK);
    }

    @RequestMapping(path = "/destroy", method = RequestMethod.DELETE)
    public ResponseEntity<?> DestroyServer(String name) {
        GameServerService.DestroyServer(name);
        return new ResponseEntity<>("done", HttpStatus.OK);
    }

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    public ResponseEntity<?> ExecuteServer(@RequestBody GameServerCommand command) {
        GameServerService.ExecuteServerCommand(command);
        return new ResponseEntity<>("done", HttpStatus.OK);
    }

//    @RequestMapping(path = "/list", method = RequestMethod.GET)
//    public ResponseEntity<List<GameServerConfiguration>> GetAllServers() {
//        return new ResponseEntity<>(GameServerService.GetServers(), HttpStatus.OK);
//    }
//
//    @RequestMapping(path = "/info", method = RequestMethod.GET)
//    public ResponseEntity<GameServerConfiguration> GetServer(String name) {
//        return new ResponseEntity<>(GameServerService.GetServerInfo(name), HttpStatus.OK);
//    }
}
