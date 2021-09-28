package com.vbevilacqua.poller.controller;

import com.vbevilacqua.poller.model.PollerModel;
import com.vbevilacqua.poller.service.PollerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/url")
public class PollerController {

    private final PollerService pollerService;

    public PollerController(PollerService pollerService) {
        this.pollerService = pollerService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<PollerModel> add(@RequestBody PollerModel pollerModel) throws IOException {
        return new ResponseEntity<>(pollerService.addURL(pollerModel.getUrl(), pollerModel.getName()), HttpStatus.CREATED);
    }
}