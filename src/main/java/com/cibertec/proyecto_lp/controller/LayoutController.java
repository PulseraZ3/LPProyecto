package com.cibertec.proyecto_lp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LayoutController {
    @GetMapping("/adminPage")
    public String mostrarDashBoard() {
        return "layout";
    }
}
