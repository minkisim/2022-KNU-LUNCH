package com.minkisim.kunLunch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

    @GetMapping(value = "showMenu")
    public String showMenu(Model model){
        return "menu/showMenu";
    }
}
