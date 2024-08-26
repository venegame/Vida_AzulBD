package com.vida.azul.Controller;

import com.vida.azul.Domain.RecursoCrud;
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
import com.vida.azul.Service.RecursoServiceCrud;

@Controller
@RequestMapping("/crud_recursos")
public class RecursoControllerCrud {

    @Autowired
    private RecursoServiceCrud recursoService;

    @Autowired
    private CategoriaServiceCrud categoriaService;

    @GetMapping("/listado")
    public String obtenerRecursos(Model model) {
        List<RecursoCrud> recursos = recursoService.obtenerRecursos();
        model.addAttribute("recursos", recursos);
        return "crud_recursos/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("recurso", new RecursoCrud());
        return "crud_recursos/nuevo";
    }

    @PostMapping("/nuevo")
    public String agregarRecurso(
            @RequestParam Long idCategoria,
            @RequestParam String nombreRecurso,
            @RequestParam String descripcion,
            @RequestParam String imagen,
            Model model) {

        RecursoCrud recurso = new RecursoCrud(idCategoria, nombreRecurso, descripcion, imagen);
        recursoService.agregarRecurso(recurso);

        return "redirect:/crud_recursos/listado";
    }

    @GetMapping("/edita/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idRecurso, Model model) {
        RecursoCrud recurso = recursoService.buscarPorId(idRecurso);
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("recurso", recurso);
        return "crud_recursos/edita";
    }

    @PostMapping("/edita/{id}")
    public String actualizarRecurso(
            @RequestParam Long idRecurso,
            @RequestParam Long idCategoria,
            @RequestParam String nombreRecurso,
            @RequestParam String descripcion,
            @RequestParam String imagen,
            Model model) {

        RecursoCrud recurso = new RecursoCrud(idCategoria, nombreRecurso, descripcion, imagen);
        recurso.setIdRecurso(idRecurso);
        recursoService.actualizarRecurso(recurso);

        return "redirect:/crud_recursos/listado";
    }

    @GetMapping("/elimina/{id}")
    public String eliminarRecurso(@PathVariable("id") Long idRecurso) {
        recursoService.eliminarRecurso(idRecurso);
        return "redirect:/crud_recursos/listado";
    }
}
