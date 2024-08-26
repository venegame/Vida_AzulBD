package com.vida.azul.Controller;

import com.vida.azul.Domain.ExpertoCrud;
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
import com.vida.azul.Service.ExpertoServiceCrud;

@Controller
@RequestMapping("/crud_experto")
public class ExpertoControllerCrud {

    @Autowired
    private ExpertoServiceCrud expertoService;

    @Autowired
    private CategoriaServiceCrud categoriaService;

    @GetMapping("/listado")
    public String obtenerExpertos(Model model) {
        List<ExpertoCrud> expertos = expertoService.obtenerExpertos();
        model.addAttribute("expertos", expertos);
        return "crud_experto/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("experto", new ExpertoCrud());
        return "crud_experto/nuevo";
    }

    @PostMapping("/nuevo")
    public String agregarExperto(
            @RequestParam Long idCategoria,
            @RequestParam String nombreExperto,
            @RequestParam String quienesSomos,
            @RequestParam String historiaExpertos,
            @RequestParam(required = false) String urlInstagram,
            @RequestParam(required = false) String urlX,
            @RequestParam(required = false) String urlYoutube,
            @RequestParam(required = false) String urlFacebook,
            Model model) {
        ExpertoCrud experto = new ExpertoCrud(idCategoria, nombreExperto, quienesSomos,
                historiaExpertos, urlInstagram, urlX,
                urlYoutube, urlFacebook);

        expertoService.agregarExperto(experto);

        return "redirect:/crud_experto/listado";
    }

    @GetMapping("/edita/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idExperto, Model model) {
        ExpertoCrud experto = expertoService.buscarPorId(idExperto);
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("experto", experto);
        return "crud_experto/edita";
    }
    
    @PostMapping("/edita/{id}")
    public String actualizarExperto(
            @RequestParam Long idExperto,
            @RequestParam Long idCategoria,
            @RequestParam String nombreExperto,
            @RequestParam String quienesSomos,
            @RequestParam String historiaExpertos,
            @RequestParam(required = false) String urlInstagram,
            @RequestParam(required = false) String urlX,
            @RequestParam(required = false) String urlYoutube,
            @RequestParam(required = false) String urlFacebook,
            Model model) {
        ExpertoCrud experto = new ExpertoCrud(idCategoria, nombreExperto, quienesSomos,
                historiaExpertos, urlInstagram, urlX,
                urlYoutube, urlFacebook);
        experto.setIdExperto(idExperto);
        expertoService.actualizarExperto(experto);

        return "redirect:/crud_experto/listado";
    }

    @GetMapping("/elimina/{id}")
    public String eliminarExperto(@PathVariable("id") Long idExperto) {
        expertoService.eliminarExperto(idExperto);
        return "redirect:/crud_experto/listado";
    }
}
