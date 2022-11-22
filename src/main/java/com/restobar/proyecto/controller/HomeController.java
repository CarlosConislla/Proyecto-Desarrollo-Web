package com.restobar.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(value = {"/","index"})
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

    @GetMapping("principal")
    public String ventanaAdmin_user(){
        return "Principal";
    }

    @GetMapping("sedes")
    public String irASedes(){
        return "/home/sedes";
    }

    @GetMapping("reservar")
    public String irAReservar(){
        return "/home/reserva";
    }

    @GetMapping("bebidas")
    public String irABebidas(){
        return "/home/bebidas";
    }

    @GetMapping("paraPicar")
    public String irAParaPicar(){
        return "/home/paraPicar";
    }

    @GetMapping("platosCarta")
    public String irAPlatosCarta(){
        return "/home/platosCarta";
    }
}
