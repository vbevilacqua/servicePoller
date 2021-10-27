package com.vbevilacqua.poller.service;

import com.vbevilacqua.poller.model.PollerModel;
import com.vbevilacqua.poller.repository.PollerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PollerServiceTest {

    @Autowired
    private PollerService service;

    @Autowired
    private PollerRepo repo;

    private String url;
    private final String name = "Google";

    @BeforeEach
    void setup(){
        repo.deleteAll();
    }

    @Test
    @DisplayName("Should add regular url when it's correct")
    public void addURLTest() throws IOException {
        url = "http://www.google.com";
        PollerModel result = service.addURL(url, name);
        assertTrue(result.isAlive());
    }

    @Test
    @DisplayName("Shouldn't add when url has the wrong protocol")
    public void addURLWrongProtocolTest() {
        url = "htps://www.google.com";
        Exception exception = assertThrows(MalformedURLException.class, () -> service.addURL(url, name));
        assertEquals(exception.getMessage(), "unknown protocol: htps");
    }

    @Test
    @DisplayName("Shouldn't add when url has the wrong address")
    public void addURLWrongAddressTest() {
        url = "http://foo.bar";
        Exception exception = assertThrows(UnknownHostException.class, () -> service.addURL(url, name));
        assertEquals(exception.getMessage(), "foo.bar");
    }

    @Test
    @DisplayName("Should refresh status for url")
    public void refreshStatus() throws IOException {
        assertEquals(0, repo.findAll().size());

        url = "http://www.google.com";
        var initialDate = new Timestamp(System.currentTimeMillis());
        var model = new PollerModel()
                .builder()
                .url(url)
                .name("Google")
                .alive(true)
                .createdDate(initialDate)
                .updatedDate(initialDate)
                .build();
        repo.save(model);

        var firstResult = repo.findAll().stream().findFirst();
        assertTrue(firstResult.isPresent());

        service.updateStatus(firstResult.get().getId());
        var result = repo.findAll().stream().findFirst().get();
        assertNotEquals(initialDate.getNanos(), result.getUpdatedDate().getNanos());
        assertTrue(initialDate.getNanos() < result.getUpdatedDate().getNanos());
    }
}
