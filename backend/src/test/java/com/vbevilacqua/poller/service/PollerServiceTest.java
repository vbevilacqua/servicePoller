package com.vbevilacqua.poller.service;

import com.vbevilacqua.poller.model.PollerModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PollerServiceTest {

    @Autowired
    private PollerService service;

    private String url;
    private final String name = "Google";

    @Test
    @DisplayName("Should add regular url when it's correct")
    public void addURLTest() throws IOException {
        url = "http://www.google.com";
        PollerModel result = service.addURL(url, name);
        assertTrue(result.isAlive());
    }

    @Test
    @DisplayName("Shouldn't add when url has the wrong protocol")
    public void addURLWrongProtocolTest() throws MalformedURLException {
        url = "htps://www.google.com";
        Exception exception = assertThrows(MalformedURLException.class, () -> service.addURL(url, name));
        assertEquals(exception.getMessage(), "unknown protocol: htps");
    }

    @Test
    @DisplayName("Shouldn't add when url has the wrong address")
    public void addURLWrongAddressTest() throws UnknownHostException {
        url = "http://foo.bar";
        Exception exception = assertThrows(UnknownHostException.class, () -> service.addURL(url, name));
        assertEquals(exception.getMessage(), "foo.bar");
    }
}
