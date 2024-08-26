package com.vida.azul.Controller;

import com.vida.azul.Domain.GaleriaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import com.vida.azul.Service.GaleriaServiceCrud;
import com.vida.azul.Service.UsuarioServiceCrud;

@Controller
@RequestMapping("/crud_galeria")
public class GaleriaControllerCrud {

    @Autowired
    private GaleriaServiceCrud galeriaService;

    @Autowired
    private UsuarioServiceCrud usuarioService;

    @GetMapping("/listado")
    public String obtenerGalerias(Model model) {
        List<GaleriaCrud> galerias = galeriaService.obtenerGalerias();
        model.addAttribute("galerias", galerias);
        return "crud_galeria/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        model.addAttribute("galeria", new GaleriaCrud());
        return "crud_galeria/nuevo";
    }

    @PostMapping("/nuevo")
    public String agregarGaleria(
            @RequestParam Long idUsuario,
            @RequestParam String imagen,
            @RequestParam String titulo,
            Model model) {

        GaleriaCrud galeria = new GaleriaCrud(idUsuario, imagen, titulo);

        galeriaService.agregarGaleria(galeria);

        return "redirect:/crud_galeria/listado";
    }

    @GetMapping("/edita/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idImagen, Model model) {
        GaleriaCrud galeria = galeriaService.buscarPorId(idImagen);
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        model.addAttribute("galeria", galeria);
        return "crud_galeria/edita";
    }
    @PostMapping("/edita/{id}")
    public String actualizarGaleria(
            @RequestParam Long idImagen,
            @RequestParam Long idUsuario,
            @RequestParam String imagen,
            @RequestParam String titulo,
            Model model) {

        GaleriaCrud galeria = new GaleriaCrud(idUsuario, imagen, titulo);
        galeria.setIdImagen(idImagen);
        galeriaService.agregarGaleria(galeria);

        return "redirect:/crud_galeria/listado";
    }
    @GetMapping("/elimina/{id}")
    public String eliminarGaleria(@PathVariable("id") Long idImagen) {
        galeriaService.eliminarGaleria(idImagen);
        return "redirect:/crud_galeria/listado";
    }
}
