package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {

    private final GreetingService svc = new GreetingService();

    @Test
    void greetReturnsHelloWorldWhenNameNull() {
        assertEquals("Hello, World!", svc.greet(null));
    }

    @Test
    void greetReturnsHelloName() {
        assertEquals("Hello, Aman!", svc.greet("Aman"));
    }

    @Test
    void greetTrimsBlankName() {
        assertEquals("Hello, World!", svc.greet("   "));
    }
}