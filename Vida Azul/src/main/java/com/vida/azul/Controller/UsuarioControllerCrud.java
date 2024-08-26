package com.vida.azul.Controller;

import com.vida.azul.Domain.UsuarioCrud;
import com.vida.azul.Service.RolServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import com.vida.azul.Service.UsuarioServiceCrud;

@Controller
@RequestMapping("/crud_usuario")
public class UsuarioControllerCrud {

    @Autowired
    private UsuarioServiceCrud usuarioService;

    @Autowired
    private RolServiceCrud rolService;

    @GetMapping("/listado")
    public String obtenerUsuarios(Model model) {
        List<UsuarioCrud> usuarios = usuarioService.obtenerUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "crud_usuario/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("roles", rolService.obtenerRoles());
        model.addAttribute("usuario", new UsuarioCrud());
        return "crud_usuario/nuevo";
    }

    @PostMapping("/nuevo")
    public String agregarUsuario(
            @RequestParam Long idRol,
            @RequestParam String nombreUsuario,
            @RequestParam String apellidoUsuario,
            @RequestParam String correo,
            @RequestParam String contrasenia,
            Model model) {

        UsuarioCrud usuario = new UsuarioCrud(idRol, nombreUsuario, apellidoUsuario, correo, contrasenia);
        usuarioService.agregarUsuario(usuario);

        return "redirect:/crud_usuario/listado";
    }

    @GetMapping("/edita/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idUsuario, Model model) {
        UsuarioCrud usuario = usuarioService.buscarPorId(idUsuario);
        model.addAttribute("roles", rolService.obtenerRoles());
        model.addAttribute("usuario", usuario);
        return "crud_usuario/edita";
    }

    @PostMapping("/edita/{id}")
    public String actualizarUsuario(
            @RequestParam Long idUsuario,
            @RequestParam Long idRol,
            @RequestParam String nombreUsuario,
            @RequestParam String apellidoUsuario,
            @RequestParam String correo,
            @RequestParam String contrasenia,
            Model model) {

        UsuarioCrud usuario = new UsuarioCrud(idRol, nombreUsuario, apellidoUsuario, correo, contrasenia);
        usuario.setIdUsuario(idUsuario);
        usuarioService.actualizarUsuario(usuario);

        return "redirect:/crud_usuario/listado";
    }

    @GetMapping("/elimina/{id}")
    public String eliminarUsuario(@PathVariable("id") Long idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
        return "redirect:/crud_usuario/listado";
    }
}
