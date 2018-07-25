package com.gfbdev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {

    @RequestMapping("/checkPulse")
    public String checkPulse() {
        return "It's alive! (Doctor Frankenstein - 1823)";
    }
}
