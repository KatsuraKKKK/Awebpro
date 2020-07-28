package com.tarpitout.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.containsString;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 不启动server,Mock 入口
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AIOControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Hello World")));
    }
}
