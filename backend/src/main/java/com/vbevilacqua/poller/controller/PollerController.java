package com.vbevilacqua.poller.controller;

import com.vbevilacqua.poller.model.PollerModel;
import com.vbevilacqua.poller.service.PollerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/url")
public class PollerController {

    private final PollerService pollerService;

    public PollerController(PollerService pollerService) {
        this.pollerService = pollerService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<PollerModel>> findAll() {
        return new ResponseEntity<>(pollerService.findAllUrls(), OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<PollerModel> add(@RequestBody PollerModel pollerModel) throws IOException {
        return new ResponseEntity<>(pollerService.addURL(pollerModel.getUrl(), pollerModel.getName()), CREATED);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<PollerModel> refreshStatus(@RequestParam(name = "id") Long id) throws IOException {
        return new ResponseEntity<PollerModel>(pollerService.refreshStatus(id), OK);
    }
}