package com.devan.api;

import com.devan.models.GameServerCommand;
import com.devan.services.GameServerService;
import com.devan.models.GameServerConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class ApiController {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public HttpStatus CreateServer(@RequestBody GameServerConfiguration configuration) {
        GameServerService.CreateServer(configuration);
        return HttpStatus.CREATED;
    }

    @RequestMapping(path = "/destroy", method = RequestMethod.DELETE)
    public boolean DestroyServer(String name) {
        return GameServerService.DestroyServer(name);
    }

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    public HttpStatus ExecuteServer(@RequestBody GameServerCommand command) {
        GameServerService.ExecuteServerCommand(command);
        return HttpStatus.ACCEPTED;
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
