package com.lab.ghas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    // Vulnerable to XSS
    @GetMapping("/greet")
    public String greetUser(@RequestParam String name) {
        return "<h1>Hello, " + name + "!</h1>"; // Un-sanitized user input
    }
}