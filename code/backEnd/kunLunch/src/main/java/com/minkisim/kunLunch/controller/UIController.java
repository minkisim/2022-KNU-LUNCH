package com.minkisim.kunLunch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/ui")
public class UIController {

    @GetMapping(value = "/map")
    public String mapPage(){
        return "ui/map";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "ui/login";
    }
}
