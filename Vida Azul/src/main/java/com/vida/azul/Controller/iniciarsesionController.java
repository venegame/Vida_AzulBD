/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.vida.azul.Controller;

import com.vida.azul.Service.IniciarSesionService;
import com.vida.azul.domain.Usuario;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/iniciarsesion")
public class iniciarsesionController {

    @Autowired
    private IniciarSesionService iniciarSesionService;
    
    @Autowired
    public iniciarsesionController(IniciarSesionService iniciarSesionService) {
        this.iniciarSesionService = iniciarSesionService;
    }

    @GetMapping("/iniciodesesion")
    public String mostrarFormularioLogin() {
        return "iniciarsesion/iniciodesesion";
    }

    @PostMapping("/iniciodesesion")
    public String iniciarSesion(@RequestParam("correo") String correo, 
                                @RequestParam("contrasenia") String contrasenia, 
                                RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = iniciarSesionService.iniciarSesion(correo, contrasenia);
            if (usuario.getId_rol() == 1) {
                return "redirect:/indexes/index-cruds";
            } else {
                return "redirect:/indexes/index"; 
            }
        }catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/iniciarsesion/iniciodesesion";
        }
        
    }

}

