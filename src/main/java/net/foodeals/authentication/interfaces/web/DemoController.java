package net.foodeals.authentication.interfaces.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("hey")
    public String hey() {
        return "hey world";
    }

    @GetMapping("close")
    public String close() {
        return "close shit";
    }
}
