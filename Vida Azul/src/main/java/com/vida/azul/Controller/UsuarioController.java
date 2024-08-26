package com.vida.azul.Controller;

import com.vida.azul.Domain.Usuario;
import com.vida.azul.Service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Manejar la solicitud de agregar usuario
    @PostMapping("/guardar")
    public String agregarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
        usuarioService.agregarUsuario(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario agregado exitosamente");
        return "redirect:/usuarios/listado"; // Redirige al listado de usuarios
    }
    
    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam("id") Long id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuarios/editar"; // Nombre del archivo de vista
    }

    @PostMapping("/editar")
    public String actualizarUsuario(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            String resultado = usuarioService.actualizarUsuario(usuario);
            redirectAttributes.addFlashAttribute("resultado", resultado);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("resultado", "Error al actualizar usuario: " + e.getMessage());
        }
        return "redirect:/usuarios/listado";
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long id) {
        try {
            String resultado = usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al eliminar el usuario.");
        }
    }
    
}

