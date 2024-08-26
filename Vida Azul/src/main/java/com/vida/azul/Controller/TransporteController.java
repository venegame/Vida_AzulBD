/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Controller;

import com.vida.azul.Service.TransporteService;
import com.vida.azul.Domain.Transporte;
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
@RequestMapping("/transporte")
public class TransporteController {
    
    @Autowired
    private TransporteService transporteService;
    
    @GetMapping("/transportes")
    public String mostrarListado(Model model) {
        List<Transporte> transportes = transporteService.obtenerTransportes();
        model.addAttribute("transportes", transportes);
        return "transporte/transportes";
    }
    
}
