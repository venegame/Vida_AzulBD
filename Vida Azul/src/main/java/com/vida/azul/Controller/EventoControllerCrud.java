package com.vida.azul.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import com.vida.azul.Service.CategoriaServiceCrud;
import com.vida.azul.Service.EventoServiceCrud;

@Controller
@RequestMapping("/crud_eventos")
public class EventoControllerCrud {

    @Autowired
    private EventoServiceCrud eventoService;

    @Autowired
    private CategoriaServiceCrud categoriaService;

    @GetMapping("/listado")
    public String obtenerEventos(Model model) {
        List<EventoCrud> eventos = eventoService.obtenerEventos();
        model.addAttribute("eventos", eventos);
        return "crud_eventos/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("evento", new EventoCrud());
        return "crud_eventos/nuevo";
    }

    @PostMapping("/nuevo")
    public String agregarEvento(
            @RequestParam String nombreEvento,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaEvento,
            @RequestParam Long idCategoria,
            @RequestParam String descripcion,
            @RequestParam String imagen,
            Model model) {

        EventoCrud evento = new EventoCrud(idCategoria, nombreEvento, fechaEvento, descripcion, imagen);
        eventoService.agregarEvento(evento);
        return "redirect:/crud_evento/listado";

    }

    @GetMapping("/edita/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idEvento, Model model) {
        EventoCrud evento = eventoService.buscarPorId(idEvento);
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("evento", evento);
        return "crud_eventos/edita";
    }

    @PostMapping("/edita/{id}")
    public String actualizarEvento(
            @RequestParam Long idEvento,
            @RequestParam String nombreEvento,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaEvento,
            @RequestParam Long idCategoria,
            @RequestParam String descripcion,
            @RequestParam String imagen,
            Model model) {

        EventoCrud evento = new EventoCrud(idCategoria, nombreEvento, fechaEvento, descripcion, imagen);
        evento.setIdEvento(idEvento);
        eventoService.actualizarEvento(evento);
        return "redirect:/crud_eventos/listado";

    }

    @GetMapping("/elimina/{id}")
    public String eliminarEvento(@PathVariable("id") Long idEvento) {
        eventoService.eliminarEvento(idEvento);
        return "redirect:/crud_eventos/listado";
    }
}
