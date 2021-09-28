package com.vbevilacqua.poller.service;

import com.vbevilacqua.poller.model.PollerModel;
import com.vbevilacqua.poller.repository.PollerRepo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class PollerService {

    private final PollerRepo repo;

    public PollerService(PollerRepo repo) {
        this.repo = repo;
    }

    public List<PollerModel> findAllUrls() {
        return repo.findAll();
    }

    public PollerModel addURL(String urlString, String name) throws IOException {
        if (validateURL(urlString)) {
            //return persistURL(urlString, name);
        }
        else throw new IOException();

        return new PollerModel();
    }

    private boolean validateURL(String urlString) throws IOException {
        URL url;
        PollerModel result = new PollerModel();
        try{
            url = new URL(urlString);
            if(checkUrlAlive(url)){
                result = repo.findByUrl(urlString);
            }
            return result == null;
        } catch (IOException e) {
            throw e;
        }
    }

    private boolean checkUrlAlive(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

}