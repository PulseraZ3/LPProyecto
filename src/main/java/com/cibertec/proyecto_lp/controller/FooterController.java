package com.cibertec.proyecto_lp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;







@Controller
@RequestMapping("/plazavea")
public class FooterController {
    @GetMapping("/nosotros")
    public String mostrarFooterN() {
        return  "nosotros"; //lleva a nososotros.html
    }

    @GetMapping("/terminos")
    public String mostrarFooterC() {
        return  "terminos"; //lleva a terminos.html
    }

    @GetMapping("/privacidad")
    public String mostrarFooterP() {
        return  "privacidad"; //lleva a privacidad.html
    }
    
    @GetMapping("/notebook")
    public String mostrarFooterNN() {
        return  "notebook"; //lleva a notebook.html
    }

    @GetMapping("/camara")
    public String mostrarFooterCA() {
        return  "camara"; //lleva a camara.html
    }

    @GetMapping("/smarthphones")
    public String mostrarFooterS() {
        return  "smarthphones"; //lleva a smarthphones.html
    }

    @GetMapping("/accesorios")
    public String mostrarFooterA() {
        return  "accesorios"; //lleva a accesorios.html
    }
    
    
}
