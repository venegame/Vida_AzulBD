package com.vida.azul.Controller;

import com.vida.azul.Domain.ProyectoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import com.vida.azul.Service.CategoriaServiceCrud;
import com.vida.azul.Service.ProyectoServiceCrud;
import com.vida.azul.Service.UsuarioServiceCrud;

@Controller
@RequestMapping("/crud_proyecto")
public class ProyectoControllerCrud {

    @Autowired
    private ProyectoServiceCrud proyectoService;

    @Autowired
    private UsuarioServiceCrud usuarioService;

    @Autowired
    private CategoriaServiceCrud categoriaService;

    @GetMapping("/listado")
    public String obtenerProyectos(Model model) {
        List<ProyectoCrud> proyectos = proyectoService.obtenerProyectos();
        model.addAttribute("proyectos", proyectos);
        return "crud_proyecto/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("proyecto", new ProyectoCrud());
        return "crud_proyecto/nuevo";
    }

    @PostMapping("/nuevo")
    public String agregarProyecto(
            @RequestParam Long idUsuario,
            @RequestParam Long idCategoria,
            @RequestParam String nombreProyecto,
            @RequestParam String detalleProyecto,
            @RequestParam String estadoProyecto,
            @RequestParam String rutaImagen,
            Model model) {

        ProyectoCrud proyecto = new ProyectoCrud(idUsuario, idCategoria, nombreProyecto, detalleProyecto, estadoProyecto, rutaImagen);
        proyectoService.agregarProyecto(proyecto);

        return "redirect:/crud_proyecto/listado";
    }

    @GetMapping("/edita/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idProyecto, Model model) {
        ProyectoCrud proyecto = proyectoService.buscarPorId(idProyecto);
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("proyecto", proyecto);
        return "crud_proyecto/edita";
    }

   
     @PostMapping("/edita/{id}")
    public String actualizarProyecto(
            @RequestParam Long idProyecto,
            @RequestParam Long idUsuario,
            @RequestParam Long idCategoria,
            @RequestParam String nombreProyecto,
            @RequestParam String detalleProyecto,
            @RequestParam String estadoProyecto,
            @RequestParam String rutaImagen,
            Model model) {

        ProyectoCrud proyecto = new ProyectoCrud(idUsuario, idCategoria, nombreProyecto, detalleProyecto, estadoProyecto, rutaImagen);
        proyecto.setIdProyecto(idProyecto);
        proyectoService.agregarProyecto(proyecto);

        return "redirect:/crud_proyecto/listado";
    }


    @GetMapping("/elimina/{id}")
    public String eliminarProyecto(@PathVariable("id") Long idProyecto) {
        proyectoService.eliminarProyecto(idProyecto);
        return "redirect:/crud_proyecto/listado";
    }
}
