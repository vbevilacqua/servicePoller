package com.vbevilacqua.poller.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vbevilacqua.poller.model.PollerModel;
import com.vbevilacqua.poller.service.InvalidURLException;
import com.vbevilacqua.poller.service.PollerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class PollerControllerTest {

    @MockBean
    private PollerService pollerService;

    private MockMvc mockMvc;

    private final String defaultUrl = "/url";
    private final String paramName = "Google";

    @BeforeEach
    void setup() {
        PollerController pollerController = new PollerController(pollerService);
        mockMvc = standaloneSetup(pollerController).setControllerAdvice(new URLControllerExceptionHandler()).build();
    }

    @Test
    @DisplayName("Test add url")
    void shouldAddUrl() throws Exception {
        var paramUrl = "http://www.google.com";
        PollerModel model = new PollerModel().builder().id(1L).url(paramUrl).name("Google").alive(true).build();
        given(pollerService.addURL(paramUrl, paramName)).willReturn(model);
        String request =  createJsonModel(model);

        mockMvc.perform(post(defaultUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.url").isString())
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.alive").isBoolean())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.url", Matchers.is(paramUrl)))
                .andExpect(jsonPath("$.name", Matchers.is(paramName)))
                .andExpect(jsonPath("$.alive", Matchers.is(true)));
    }


    @Test
    @DisplayName("Shouldn't add wrong url")
    void shouldNotAddWrongUrl() throws Exception {
        var wrongUrl = "htp://www.google.com";
        given(pollerService.addURL(wrongUrl, paramName)).willThrow(InvalidURLException.class);
        String request =  createJsonModel(wrongUrl);

        mockMvc.perform(post(defaultUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", Matchers.is(404)))
                .andExpect(jsonPath("$.reason", Matchers.is("URL not found")));
    }

    private String createJsonModel(String paramUrl) throws JsonProcessingException {
        PollerModel model = new PollerModel().builder().id(1L).url(paramUrl).name("Google").alive(true).build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(model);
    }

    private String createJsonModel(PollerModel model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(model);
    }
}
