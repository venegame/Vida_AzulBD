package com.vida.azul.Controller;

import com.vida.azul.Domain.RolCrud;
import com.vida.azul.Service.RolServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/crud_rol")
public class RolControllerCrud {

    @Autowired
    private RolServiceCrud rolService;

    @GetMapping("/listado")
    public String obtenerRoles(Model model) {
        List<RolCrud> roles = rolService.obtenerRoles();
        model.addAttribute("roles", roles);
        return "crud_rol/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoRol(Model model) {
        model.addAttribute("rol", new RolCrud());
        return "crud_rol/nuevo";
    }

    @PostMapping("/nuevo")
    public String agregarRol(RolCrud rol) {
        rolService.agregarRol(rol);
        return "redirect:/crud_rol/listado";
    }

   
    @GetMapping("/edita/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idRol, Model model) {
        RolCrud rol = rolService.buscarPorId(idRol);
        if (rol != null) {
            model.addAttribute("rol", rol);
            return "crud_rol/edita";
        } else {
            return "redirect:/crud_rol/listado";
        }
    }

    @PostMapping("/edita/{id}")
    public String actualizarRol(@PathVariable("id") Long idRol, @ModelAttribute RolCrud rol) {
        rol.setIdRol(idRol);
        rolService.actualizarRol(rol);
        return "redirect:/crud_rol/listado";
    }

    @PostMapping("/eliminar")
    public String eliminarRol(@RequestParam("id") Long idRol) {
        rolService.eliminarRol(idRol);
        return "redirect:/crud_rol/listado";
    }
}
