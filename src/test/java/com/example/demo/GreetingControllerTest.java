package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Standalone controller test WITHOUT Mockito to avoid mocking problems on your JVM
class GreetingControllerTest {

    MockMvc mvc;
    GreetingService greetingService;
    GreetingController controller;

    @BeforeEach
    void setUp() {
        // use a tiny stub implementation instead of Mockito.mock(...)
        greetingService = new GreetingService() {
            @Override
            public String greet(String name) {
                if ("Aman".equals(name)) {
                    return "Hello, Aman!";
                }
                return super.greet(name);
            }
        };

        controller = new GreetingController(greetingService);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void greetEndpointReturnsGreeting() throws Exception {
        mvc.perform(get("/greet").param("name", "Aman"))
           .andExpect(status().isOk())
           .andExpect(content().string("Hello, Aman!"));
    }
}