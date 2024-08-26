/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vida.azul.Controller;

import com.vida.azul.Service.RecursoService;
import com.vida.azul.domain.Recurso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Me
 */
@Controller
@RequestMapping("/recurso")
public class RecursoController {
    
    @Autowired
    private RecursoService recursoService;

    @GetMapping("/recursos")
    public String mostrarListado(Model model) {
        List<Recurso> recursos = recursoService.obtenerRecursos();
        model.addAttribute("recursos", recursos);
        return "recurso/recursos";
    }
    
    @GetMapping("/detallerecurso/{id}")
    public String verDetalleRecurso(@PathVariable("id") int id, Model model) {
        Recurso recurso = recursoService.obtenerRecursoPorId(id);
        model.addAttribute("recurso", recurso);
        return "recurso/detallerecurso"; // Devolver solo el nombre de la vista sin parámetros
    }

}