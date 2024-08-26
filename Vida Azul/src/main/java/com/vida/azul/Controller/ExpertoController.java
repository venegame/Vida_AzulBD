/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Controller;

import com.vida.azul.Service.ExpertoService;
import com.vida.azul.Domain.Experto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author Me
 */
@Controller
@RequestMapping("/experto")
public class ExpertoController {
    
    @Autowired
    private ExpertoService expertoService;
    
    @GetMapping("/expertos")
    public String mostrarListado(Model model) {
        List<Experto> expertos = expertoService.obtenerExpertos();
        model.addAttribute("expertos", expertos);
        return "experto/expertos";
    }
    
}
