package com.vida.azul.Controller;

import com.vida.azul.domain.Galeria;
import com.vida.azul.Service.GaleriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import models.GaleriaModelo;

@Controller
@RequestMapping("/Galeria")
public class GaleriaController {
    
    @Autowired
    private GaleriaService galeriaService;

    @GetMapping("/galeria")
    public String mostrarGaleria(Model model) {
        List<GaleriaModelo> imagenes = galeriaService.obtenerImagenes();
        model.addAttribute("imagenes", imagenes);
        return "Galeria/galeria";  
    }
}
