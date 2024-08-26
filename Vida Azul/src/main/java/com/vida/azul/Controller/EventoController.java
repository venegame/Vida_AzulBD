package com.vida.azul.Controller;

import com.vida.azul.Service.EventoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import models.EventoModelo;

@Controller
@RequestMapping("/Eventos")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @GetMapping("/eventos")
    public String mostrarEventos(Model model) {
        List<EventoModelo> eventos = eventoService.obtenerEventos();
        model.addAttribute("eventos", eventos);
        return "Eventos/eventos";  
    }
}