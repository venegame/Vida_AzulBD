/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.vida.azul.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Me
 */
@Controller
@RequestMapping("/indexes")
public class IndexController {
    
    @GetMapping("/index")
    public String mostrarPaginaPrincipal() {
        return "/indexes/index";
    }
    
    @GetMapping("/index-cruds")
    public String mostrarPaginaPrincipalCruds() {
        return "/indexes/index-cruds";
    }
}
