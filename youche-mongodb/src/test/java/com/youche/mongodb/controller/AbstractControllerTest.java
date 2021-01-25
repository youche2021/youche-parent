package com.youche.mongodb.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

@Log4j2
@ExtendWith(value = SpringExtension.class) // 可加可不加，因为 @SpringBootTest 注释已经加了
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {


    private WebApplicationContext webApplicationContext;

    @Autowired
    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    protected void accept(MockHttpServletRequestBuilder builder) {
        // builder.accept(MediaType.APPLICATION_JSON_UTF8_VALUE); 已过期
        String jsonUTF8 = String.format("%s;charset=%s", MediaType.APPLICATION_JSON_VALUE, StandardCharsets.UTF_8.displayName());
        builder.accept(jsonUTF8);
    }
}
