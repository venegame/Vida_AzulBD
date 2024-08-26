package com.vida.azul.Controller;

import com.vida.azul.Service.RecursoService;
import com.vida.azul.domain.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recurso")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @GetMapping("/recursos")
    public String mostrarListado(
            @RequestParam(value = "filtrocategoria", required = false) Integer filtrocategoria,
            Model model) {
        // Llamar al servicio para obtener recursos filtrados
        List<Recurso> recursos = recursoService.obtenerRecursos(filtrocategoria);
        model.addAttribute("recursos", recursos);
        model.addAttribute("filtrocategoria", filtrocategoria);
        return "recurso/recursos";
    }

    @GetMapping("/detallerecurso/{id}")
    public String verDetalleRecurso(@PathVariable("id") int id, Model model) {
        Recurso recurso = recursoService.obtenerRecursoPorId(id);
        model.addAttribute("recurso", recurso);
        return "recurso/detallerecurso"; // Devolver solo el nombre de la vista sin parámetros
    }
}
