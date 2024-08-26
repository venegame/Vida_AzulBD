package com.vida.azul.Controller;

import com.vida.azul.Domain.CategoriaCrud;
import com.vida.azul.Domain.EventoCrud;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vida.azul.Service.CategoriaServiceCrud;

@Controller
@RequestMapping("/crud_categoria")
public class CategoriaControllerCrud {

    @Autowired
    private CategoriaServiceCrud categoriaService;

    @GetMapping("/listado")
    public String obtenerCategorias(Model model) {
        List<CategoriaCrud> categorias = categoriaService.obtenerCategorias();
        model.addAttribute("categorias", categorias);
        return "crud_categoria/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("categoria", new CategoriaCrud());
        return "crud_categoria/nuevo";
    }

    @PostMapping("/nuevo")
    public String agregarCategoria(CategoriaCrud categoria) {
        categoriaService.agregarCategoria(categoria);
        return "redirect:/crud_categoria/listado";
    }

    @GetMapping("/edita/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idCategoria, Model model) {
        CategoriaCrud categoria = categoriaService.buscarPorId(idCategoria);
        model.addAttribute("categoria", categoria);
        return "crud_categoria/edita";
    }

    @PostMapping("/edita/{id}")
    public String actualizarCategoria(
            @RequestParam Long idCategoria,
            @RequestParam String nombreCategoria,
            Model model) {

        CategoriaCrud categoria = new CategoriaCrud();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombreCategoria(nombreCategoria);
        categoriaService.actualizarCategoria(categoria);
        return "redirect:/crud_categoria/listado";
    }


    @PostMapping("/elimina/{id}")
    public String eliminarCategoria(@RequestParam Long idCategoria,
            Model model) {
        categoriaService.eliminarCategoria(idCategoria);
        return "redirect:/crud_categoria/listado";
    }

}
