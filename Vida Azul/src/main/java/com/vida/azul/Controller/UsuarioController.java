package com.vida.azul.Controller;

import com.vida.azul.domain.Usuario;
import com.vida.azul.Service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Mostrar formulario principal
    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/listado"; 
    }

    // Mostrar formulario para insertar usuario
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/nuevo";
    }

    // Mostrar formulario para eliminar usuario
    @GetMapping("/eliminar")
    public String mostrarFormularioEliminar() {
        return "usuarios/eliminar"; // Página para eliminar usuario
    }

    // Mostrar formulario para actualizar usuario
    @GetMapping("/actualizar")
    public String mostrarFormularioActualizar(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios()); // Enviar la lista de usuarios al modelo para el dropdown
        return "usuarios/actualizar"; // Página para actualizar usuarios
    }

    // Manejar la solicitud de agregar usuario
    @PostMapping("/guardar")
    public String agregarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
        usuarioService.agregarUsuario(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario agregado exitosamente");
        return "redirect:/usuarios/listado"; // Redirige al listado de usuarios
    }

    // Manejar la solicitud de eliminar usuario
    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("id_usuario") Long id_usuario, Model model) {
        String resultado = usuarioService.eliminarUsuario(id_usuario);
        model.addAttribute("resultado", resultado);
        return "usuarios/resultado"; // Página de resultado
    }

    // Manejar la solicitud de actualizar usuario
    @PostMapping("/actualizar")
    public String actualizarUsuario(@RequestParam("id_usuario") String id_usuario,
                                    @RequestParam("id_rol") Long id_rol,
                                    @RequestParam("nombre_usuario") String nombre_usuario,
                                    @RequestParam("apellido_usuario") String apellido_usuario,
                                    @RequestParam("correo") String correo,
                                    @RequestParam("contrasenia") String contrasenia,
                                    Model model) {
        Usuario usuario = new Usuario(id_rol, nombre_usuario, apellido_usuario, correo, contrasenia);
        usuario.setId_usuario(Long.parseLong(id_usuario)); // Convertir el ID de String a Long
        String resultado = usuarioService.actualizarUsuario(usuario);
        model.addAttribute("resultado", resultado);
        return "usuarios/resultado"; // Página de resultado
    }
}

